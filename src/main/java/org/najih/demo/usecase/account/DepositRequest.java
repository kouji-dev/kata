package org.najih.demo.usecase.account;

import java.math.BigDecimal;

public class DepositRequest {
    private final String iban;
    private final BigDecimal amount;

    public DepositRequest(String iban, BigDecimal amount) {
        this.iban = iban;
        this.amount = amount;
    }

    public String getIban() {
        return iban;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
