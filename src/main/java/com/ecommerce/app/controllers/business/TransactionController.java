package com.ecommerce.app.controllers.business;

import com.ecommerce.app.dtos.impl.TransactionDto;
import com.ecommerce.app.dtos.impl.TransactionFilterDto;
import com.ecommerce.app.dtos.impl.TransactionStatusDto;
import com.ecommerce.app.responses.CategoryResponse;
import com.ecommerce.app.responses.base.PageResponse;
import com.ecommerce.domain.entities.business.Transaction;
import com.ecommerce.domain.services.impl.business.TransactionService;
import com.ecommerce.app.responses.DataResponse;
import com.ecommerce.app.responses.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/order")
public class TransactionController{

	@Autowired private TransactionService transactionService;

  @GetMapping("all")
  public PageResponse<TransactionResponse> getAll(Authentication authentication, Pageable pageable){
    String userEmail = authentication.getName();
    return PageResponse.createFrom(transactionService.getAll(userEmail, pageable));
  }

  @GetMapping("filter")
  public PageResponse<TransactionResponse> filter(Authentication authentication, Pageable pageable){
    String userEmail = authentication.getName();
    return PageResponse.createFrom(transactionService.getAll(userEmail, pageable));
  }

  @GetMapping("/user")
  public PageResponse<TransactionResponse> getPageTransactionByUser(Authentication authentication, Pageable pageable){
    String userEmail = authentication.getName();
    return PageResponse.createFrom(transactionService.findAllByUser(userEmail, pageable));
  }

  @PostMapping("/{id}/status")
  public TransactionResponse changeStatusTransaction(Authentication authentication, @PathVariable Long id, @Valid @RequestBody TransactionStatusDto transactionStatusDto){
    String userEmail = authentication.getName();
    return transactionService.changeStatusTransaction(userEmail, id, transactionStatusDto);
  }

  @GetMapping("/revenue")
  public DataResponse getRevenue(@RequestParam("year") int year){
    return transactionService.getRevenueByYear(year);
  }

  @PostMapping()
  public Transaction create(Authentication authentication, @Valid @RequestBody TransactionDto transactionDto){
    String userEmail = authentication.getName();
    return transactionService.create(userEmail, transactionDto);
  }

}
