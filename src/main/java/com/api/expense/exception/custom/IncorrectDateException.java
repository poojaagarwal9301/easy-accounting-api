package com.api.expense.exception.custom;

public class IncorrectDateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IncorrectDateException(String msg) {
        super(msg);
    }
}

