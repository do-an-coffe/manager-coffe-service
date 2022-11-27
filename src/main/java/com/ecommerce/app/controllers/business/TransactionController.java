package com.ecommerce.app.controllers.business;

import com.ecommerce.app.dtos.impl.TransactionFilterDto;
import com.ecommerce.app.dtos.impl.TransactionStatusDto;
import com.ecommerce.domain.entities.business.Transaction;
import com.ecommerce.domain.services.impl.business.TransactionService;
import com.ecommerce.app.controllers.BaseController;
import com.ecommerce.app.responses.DataResponse;
import com.ecommerce.app.responses.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/order")
public class TransactionController extends BaseController<Transaction, Long, TransactionResponse, TransactionFilterDto> {

	@Autowired
  TransactionService transactionService;

    public TransactionController() {
        super(TransactionResponse.class, TransactionFilterDto.class);
    }

    @GetMapping("/user")
    List<Transaction> getPageTransactionByUser(){
        return transactionService.findAllByUser();
    }

    @PostMapping("/{id}/status")
    TransactionResponse changeStatusTransaction(@PathVariable Long id, @Valid @RequestBody TransactionStatusDto transactionStatusDto){
        return transactionService.changeStatusTransaction(id, transactionStatusDto);
    }

    @GetMapping("/revenue")
    DataResponse getRevenue(@RequestParam("year") int year){
        return transactionService.getRevenueByYear(year);
    }

}
