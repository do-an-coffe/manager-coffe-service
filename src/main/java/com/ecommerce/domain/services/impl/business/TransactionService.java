package com.ecommerce.domain.services.impl.business;

import com.ecommerce.app.dtos.OrderDto;
import com.ecommerce.app.dtos.DTO;
import com.ecommerce.app.dtos.impl.TransactionDto;
import com.ecommerce.app.dtos.impl.TransactionStatusDto;
import com.ecommerce.app.responses.CategoryResponse;
import com.ecommerce.app.responses.CustomPage;
import com.ecommerce.app.responses.DataResponse;
import com.ecommerce.app.responses.TransactionResponse;
import com.ecommerce.domain.entities.author.User;
import com.ecommerce.domain.entities.business.Discount;
import com.ecommerce.domain.entities.business.Order;
import com.ecommerce.domain.entities.business.Product;
import com.ecommerce.domain.entities.business.Transaction;
import com.ecommerce.domain.entities.data.MonthRevenue;
import com.ecommerce.domain.entities.enums.RoleType;
import com.ecommerce.domain.entities.enums.TransactionStatus;
import com.ecommerce.domain.services.base.BaseService;
import com.ecommerce.domain.services.impl.BaseAbtractService;
import com.ecommerce.domain.utils.Constant;
import com.ecommerce.domain.utils.Helper;
import com.ecommerce.domain.utils.exception.CustomErrorMessage;
import com.ecommerce.domain.utils.exception.CustomException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
public class TransactionService extends BaseService {

  @Autowired private PaymentService paymentService;


  public Page<TransactionResponse> getAll(String email, Pageable pageable) {
    User user = findUserByEmail(email);
    Page<Transaction> transactionPage = null;
    if (user.getRole().getName().equals(RoleType.ADMIN)) {
      transactionPage = transactionStorage.findAll(pageable);
    } else {
      transactionPage = transactionStorage.findAllByUser(user.getId(), pageable);
    }

    return mapperUtil.mapEntityPageIntoDtoPage(transactionPage, TransactionResponse.class);
  }

  public Page<TransactionResponse> findAllByUser(String email, Pageable pageable) {
    User user = findUserByEmail(email);
    Page<Transaction> transactionPage = transactionStorage.findAllByUser(user.getId(), pageable);
    return mapperUtil.mapEntityPageIntoDtoPage(transactionPage, TransactionResponse.class);
  }

  public Transaction create(String email, TransactionDto dto) {
    User user = findUserByEmail(email);
    List<Long> productIds = dto.getOrders().parallelStream().map(OrderDto::getProductID).collect(Collectors.toList());
    List<Product> products = productStorage.findProductsByIdIn(productIds);
    if (products.size() != productIds.size()) {
      throw new CustomException(HttpStatus.BAD_REQUEST, CustomErrorMessage.INVALID_PRODUCT_ID);
    }

    List<Long> discountIds = dto.getOrders().
            parallelStream().filter(ele -> ele.getDiscountId() != null).map(OrderDto::getDiscountId).collect(Collectors.toList());
    List<Discount> discounts = discountIds.isEmpty() ? new ArrayList<>() : discountStorage.findDiscountsByIdIn(discountIds);

    Map<Long, Product> mapProduct = new HashMap<>();
    Map<Long, Discount> mapDiscount = new HashMap<>();
    products.forEach(ele -> {
      mapProduct.put(ele.getId(), ele);
    });
    discounts.forEach(ele -> {
      mapDiscount.put(ele.getId(), ele);
    });

    Transaction transaction = new Transaction();
    transaction.setPayment(dto.getPayment());
    transaction.setUserEmail(dto.getEmail());
    transaction.setUserPhone(dto.getPhone());
    transaction.setAddress(dto.getAddress());
    transaction.setPaymentInfo(dto.getPaymentInfo());
    transaction.setUser(user);
    List<Order> orders = new ArrayList<>();
    dto.getOrders().forEach(ele -> {
      Order order = new Order();
      Product product = (mapProduct.get(ele.getProductID()));
      if (ele.getQuantity() > product.getQuantity()) {
        throw new CustomException(HttpStatus.BAD_REQUEST, CustomErrorMessage.INVENTORY_NOT_ENOUGH);
      }
      order.setProduct(product);
      order.setQuantity(ele.getQuantity());
      product.setQuantity(product.getQuantity() - ele.getQuantity());
      double amount;
      if (ele.getDiscountId() != null) {
        Discount discount = mapDiscount.get(ele.getDiscountId());
        if (discount.getProduct().getId().longValue() != ele.getProductID().longValue()) {
          throw new CustomException(HttpStatus.BAD_REQUEST, CustomErrorMessage.PRODUCT_INVALID);
        }
        Long now = (new Date()).getTime();
        if (discount.getStartDate().getTime() <= now && discount.getEndDate().getTime() >= now) {
          amount = (double)product.getPrice() * ele.getQuantity() * (100 - discount.getDiscount()) / 100d;
          order.setAmount(amount);
        } else {
          throw new CustomException(HttpStatus.BAD_REQUEST, CustomErrorMessage.DISCOUNT_EXPIRED_TIME);
        }
      } else {
        amount = product.getPrice() * ele.getQuantity();
      }
      order.setAmount(amount);
      orders.add(order);
    });
    transaction.setAmount(orders.parallelStream().map(Order::getAmount).reduce(0d, Double::sum));
    double sumAmount = transaction.getAmount();
    transaction.setAmount(Helper.roundTwoDecimal(sumAmount));
    transaction.setOrderSelf(orders);
    transaction.setPayment(dto.getPayment());

    transaction.setStatus(TransactionStatus.WAIT_FOR_APPROVE.toString());
    productStorage.saveAll((List<Product>) mapProduct.values());
    transaction = transactionStorage.save(transaction);
    return transaction;
  }
  public TransactionResponse changeStatusTransaction(String email, Long id, TransactionStatusDto dto) {
    User user = findUserByEmail(email);
    Transaction transaction = findTransactionById(id);
    if ((user.getRole().getName().equals(RoleType.USER) && Constant.mapStatusUser.get(transaction.getStatus()).equals(dto.getStatus())) ||
            (user.getRole().getName().equals(RoleType.ADMIN) && Constant.mapStatusAdmin.get(transaction.getStatus()).equals(dto.getStatus()))) {
      transaction.setStatus(dto.getStatus());

    } else if (user.getRole().getName().equals(RoleType.ADMIN) && dto.getStatus().equals(TransactionStatus.CANCEL.toString()) &&
            (transaction.getStatus().equals(TransactionStatus.WAIT_FOR_APPROVE.toString()) || transaction.getStatus().equals(TransactionStatus.APPROVED.toString()))) {
      transaction.setStatus(TransactionStatus.CANCEL.toString());
    } else {
      throw new CustomException(HttpStatus.BAD_REQUEST, CustomErrorMessage.TRANSACTION_STATUS_INCORRECT);
    }
    return modelMapper.toTransactionResponse(transactionStorage.save(transaction));
  }

  public DataResponse getRevenueByYear(int year) {
    List<MonthRevenue> listResponse = new ArrayList<>();
    List<MonthRevenue> result = transactionStorage.getRevenueInYear(year);
    Map<Integer, MonthRevenue> mapMonthRevenue = new HashMap<>();
    result.forEach(item -> {
      mapMonthRevenue.put(item.getMonth(), item);
    });
    for (int i = 1; i <= 12; i++) {
      if (mapMonthRevenue.get(i) != null) {
        listResponse.add(mapMonthRevenue.get(i));
      } else {
        listResponse.add(new MonthRevenue(i, 0d));
      }
    }
    return new DataResponse(listResponse);
  }
}