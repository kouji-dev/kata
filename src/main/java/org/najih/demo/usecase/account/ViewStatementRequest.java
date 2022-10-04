package org.najih.demo.usecase.account;

public class ViewStatementRequest {
    private final String iban;

    public ViewStatementRequest(String iban) {
        this.iban = iban;
    }

    public String getIban() {
        return iban;
    }
}
