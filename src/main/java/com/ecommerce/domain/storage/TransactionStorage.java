package com.ecommerce.domain.storage;

import com.ecommerce.domain.entities.business.Transaction;
import com.ecommerce.domain.entities.data.MonthRevenue;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class TransactionStorage extends BaseStorage{
  public Transaction save(Transaction transaction){
    return transactionRepository.save(transaction);
  }

  public Transaction findTransactionById(Long id){
    return transactionRepository.findTransactionById(id);
  }

  public List<MonthRevenue> getRevenueInYear(int year){
    return transactionRepository.getRevenueInYear(year);
  }

  public List<Transaction> findAllByUserId(Long userId){
    return transactionRepository.findAllByUserId(userId);
  }

  public Page<Transaction> findAllByUser(Long userId, Pageable pageable){
    return transactionRepository.findAllByUser(userId, pageable);
  }

  public Page<Transaction> findAll(Pageable pageable){
    return transactionRepository.findAll(pageable);
  }
}
