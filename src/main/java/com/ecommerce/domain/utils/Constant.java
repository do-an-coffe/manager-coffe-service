package com.ecommerce.domain.utils;

import com.ecommerce.domain.entities.enums.TransactionStatus;

import java.util.HashMap;
import java.util.Map;

public class Constant {

    public static final String OFFLINE = "OFFLINE";
    public static final String ONLINE = "ONLINE";

    public static final Map mapStatusUser = new HashMap<String, String>() {{
        put(TransactionStatus.WAIT_FOR_APPROVE.toString(), TransactionStatus.CANCEL.toString());
        put(TransactionStatus.APPROVED.toString(), TransactionStatus.CANCEL.toString());
    }};

    public static final Map mapStatusAdmin = new HashMap<String, String>() {{
        put(TransactionStatus.WAIT_FOR_APPROVE.toString(), TransactionStatus.APPROVED.toString());
        put(TransactionStatus.APPROVED.toString(), TransactionStatus.SUCCESSFUL.toString());
    }};

}