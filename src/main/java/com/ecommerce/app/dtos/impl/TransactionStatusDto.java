package com.ecommerce.app.dtos.impl;

import com.ecommerce.app.dtos.DTO;
import com.ecommerce.domain.entities.business.Transaction;
import com.ecommerce.domain.entities.enums.TransactionStatus;
import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Data
public class TransactionStatusDto implements DTO<Transaction> {


    @NotNull(message = "status not null")
    private String status;

    @AssertTrue(message = "status is incorrect")
    protected boolean isValidStatus() {
        return status.equals(TransactionStatus.WAIT_FOR_APPROVE.toString())
                || status.equals(TransactionStatus.APPROVED.toString())
                || status.equals(TransactionStatus.CANCEL.toString())
                || status.equals(TransactionStatus.SUCCESSFUL.toString());
    }

}
