package org.najih.demo.domain.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Statement {
    private final LocalDateTime dateTime;
    private final StatementType statementType;
    private final BigDecimal balance;
    private final BigDecimal amount;
    private final boolean valid;

    public Statement(LocalDateTime dateTime, StatementType statementType, BigDecimal balance, BigDecimal amount, boolean valid) {
        this.dateTime = dateTime;
        this.statementType = statementType;
        this.balance = balance;
        this.amount = amount;
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "Statement{" +
                "dateTime=" + dateTime +
                ", statementType=" + statementType +
                ", balance=" + balance +
                ", amount=" + amount +
                ", valid=" + valid +
                '}';
    }

    public StatementType getStatementType() {
        return statementType;
    }

    public boolean isValid() {
        return valid;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
