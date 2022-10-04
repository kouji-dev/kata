package org.najih.demo.usecase.account;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.najih.demo.domain.account.Account;
import org.najih.demo.domain.account.AccountRepository;
import org.najih.demo.domain.account.exceptions.InvalidOperation;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class WithdrawTests {

    public final String ibn = "1234";

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    public final AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
    public final Withdraw withdraw = new Withdraw(accountRepository);
    public final Account account = Mockito.mock(Account.class);

    @Test
    public void should_return_account_with_new_balance_equal_to_old_balance_plus_amount() throws AccountNotFound, InvalidOperation {
        when(accountRepository.findByIban(ibn)).thenReturn(Optional.of(account));

        BigDecimal amount = BigDecimal.valueOf(123.45);

        withdraw.withdraw(new WithdrawRequest(ibn, amount));
        verify(account).withdraw(amount);
    }

    @Test(expected = AccountNotFound.class)
    public void should_throw_not_found_account_exception_if__account_doesnt_exist() throws AccountNotFound, InvalidOperation {
        when(accountRepository.findByIban(ibn)).thenReturn(Optional.empty());
        withdraw.withdraw(new WithdrawRequest(ibn, BigDecimal.ONE));
    }

    @Test(expected = InvalidOperation.class)
    public void should_throw_invalid_operation_exception_if_amount_is_invalid() throws AccountNotFound, InvalidOperation {
        when(accountRepository.findByIban(ibn)).thenReturn(Optional.of(account));
        doThrow(new InvalidOperation("")).when(account).withdraw(any());
        withdraw.withdraw(new WithdrawRequest(ibn, BigDecimal.ZERO));
    }

}