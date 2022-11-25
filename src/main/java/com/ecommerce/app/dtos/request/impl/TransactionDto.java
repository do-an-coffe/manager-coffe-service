package com.ecommerce.app.dtos.request.impl;

import com.ecommerce.app.dtos.request.DTO;
import com.ecommerce.domain.entities.business.Transaction;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class TransactionDto implements DTO<Transaction> {

    @NotNull(message = "orders not null")
    @Size(min = 1, message = "orders require at least 1")
    @Valid private List<OrderDto> orders;

    @NotNull(message = "email not null")
    private String email;

    @NotNull(message = "phone not null")
    private String phone;

    @NotNull(message = "address not null")
    private String address;

    @NotNull(message = "payment_info not null")
    @JsonProperty("payment_info")
    private String paymentInfo;

    @NotNull(message = "payment not null")
    private String payment;

    @AssertTrue(message = "Email or phone not null")
    protected boolean isValidUserEmailOrPhone() {
        return this.email != null || this.phone != null;
    }


}
