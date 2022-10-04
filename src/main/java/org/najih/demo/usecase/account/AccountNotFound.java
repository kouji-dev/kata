package org.najih.demo.usecase.account;

public class AccountNotFound extends Exception {
    AccountNotFound(String msg) {
        super(msg);
    }
}
