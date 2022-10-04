package org.najih.demo.account;

import org.junit.Before;
import org.junit.Test;
import org.najih.demo.domain.account.Account;
import org.najih.demo.domain.account.StatementType;
import org.najih.demo.domain.account.exceptions.InvalidOperation;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AccountTests {

    private Account account;

    @Before
    public void instantiateAccount() {
        account = new Account("123");
    }

    @Test
    public void should_deposit_amount() throws InvalidOperation {
        BigDecimal amount = BigDecimal.valueOf(123.45);
        BigDecimal oldBalance = account.getBalance();

        account.deposit(amount);

        assertAll(
                () -> assertEquals(account.getBalance(), oldBalance.add(amount)),
                () -> assertEquals(StatementType.DEPOSIT, account.getStatements().get(0).getStatementType()),
                () -> assertEquals(amount, account.getStatements().get(0).getAmount()),
                () -> assertTrue(account.getStatements().get(0).isValid())
        );
    }

    @Test
    public void should_withdraw_amount() throws InvalidOperation {
        BigDecimal amount = BigDecimal.valueOf(123.45);
        BigDecimal oldBalance = account.getBalance();

        account.withdraw(amount);

        assertAll(
                () -> assertEquals(account.getBalance(), oldBalance.add(amount)),
                () -> assertEquals(StatementType.WITHDRAW, account.getStatements().get(0).getStatementType()),
                () -> assertEquals(amount, account.getStatements().get(0).getAmount()),
                () -> assertTrue(account.getStatements().get(0).isValid())
        );
    }

    @Test(expected = InvalidOperation.class)
    public void should_throw_exception_if_amount_is_equal_to_zero_when_depositing() throws InvalidOperation {
        BigDecimal amount = BigDecimal.ZERO;

        account.deposit(amount);
    }

    @Test(expected = InvalidOperation.class)
    public void should_throw_exception_if_amount_equal_to_zero_when_withdrawing() throws InvalidOperation {
        BigDecimal amount = BigDecimal.ZERO;

        account.withdraw(amount);
    }

    @Test(expected = InvalidOperation.class)
    public void should_throw_exception_if_amount_is_negative_when_depositing() throws InvalidOperation {
        BigDecimal amount = BigDecimal.valueOf(-10);

        account.deposit(amount);
    }

    @Test(expected = InvalidOperation.class)
    public void should_throw_exception_if_amount_negative_when_withdrawing() throws InvalidOperation {
        BigDecimal amount = BigDecimal.valueOf(-10);

        account.withdraw(amount);
    }

    @Test()
    public void depositing_statement_should_be_invalid_when_amount_is_not_valid() {
        BigDecimal amount = BigDecimal.valueOf(-10);

        try {
            account.deposit(amount);
        } catch (InvalidOperation ignored) {}

        assertFalse(account.getStatements().get(0).isValid());
    }

    @Test
    public void withdrawing_statement_should_be_invalid_when_amount_is_not_valid() {
        BigDecimal amount = BigDecimal.valueOf(-10);

        try {
            account.withdraw(amount);
        } catch (InvalidOperation ignored) {
        }

        assertFalse(account.getStatements().get(0).isValid());
    }
}
