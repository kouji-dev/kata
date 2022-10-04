package org.najih.demo.domain.account;

import org.najih.demo.domain.account.exceptions.InvalidOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {
    private final String iban;

    private final LocalDateTime createdOn = LocalDateTime.now();

    private final List<Statement> statements = new ArrayList<>();

    private final Currency currency = Currency.EUR;
    private BigDecimal balance = BigDecimal.ZERO;

    public Account(String iban) {
        this.iban = iban;
    }

    public void withdraw(BigDecimal amount) throws InvalidOperation {
        this.validateWithdraw(amount);
        balance = balance.subtract(amount);
    }

    public void deposit(BigDecimal amount) throws InvalidOperation {
        this.validateDeposit(amount);
        balance = balance.add(amount);
    }

    private void validateWithdraw(BigDecimal amount) throws InvalidOperation {
        StatementType statementType = StatementType.WITHDRAW;

        amountShouldBeStrictlyPositive(amount, statementType);
        amountShouldNotExceedBalance(amount, statementType);

        statements.add(new Statement(LocalDateTime.now(), statementType, balance.subtract(amount), amount, true));
    }

    private void validateDeposit(BigDecimal amount) throws InvalidOperation {
        StatementType statementType = StatementType.DEPOSIT;

        amountShouldBeStrictlyPositive(amount, statementType);
        statements.add(new Statement(LocalDateTime.now(), statementType, balance.subtract(amount), amount, true));
    }

    private void amountShouldNotExceedBalance(BigDecimal amount, StatementType statementType) throws InvalidOperation {
        if (balance.subtract(amount).compareTo(BigDecimal.ZERO) >= 0) {
            statements.add(new Statement(LocalDateTime.now(), statementType, balance, amount, false));
            throw new InvalidOperation("The amount exceeds the available balance");
        }
    }

    private void amountShouldBeStrictlyPositive(BigDecimal amount, StatementType statementType) throws InvalidOperation {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            statements.add(new Statement(LocalDateTime.now(), statementType, balance, amount, false));
            throw new InvalidOperation("The amount value should be superior strictly to zero");
        }
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Statement> getStatements() {
        return Collections.unmodifiableList(statements);
    }
}
