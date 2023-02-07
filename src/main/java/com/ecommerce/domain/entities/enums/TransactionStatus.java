package com.ecommerce.domain.entities.enums;


public enum TransactionStatus {
    WAIT_FOR_APPROVE("Chờ xác nhận"),
    APPROVED("Đã xác nhận"),
    SUCCESSFUL("Thành công"),
    CANCEL("Đã hủy")
    ;

    public final String val;

    private TransactionStatus(String label) {
        val = label;
    }
}