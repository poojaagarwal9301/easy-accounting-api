package com.api.expense.exception.custom;

public class UniqueKeyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UniqueKeyException(String msg) {
        super(msg);
    }
}
