package org.najih.demo.usecase.account;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.najih.demo.domain.account.Account;
import org.najih.demo.domain.account.AccountRepository;
import org.najih.demo.domain.account.StatementsHistoryViewer;
import org.najih.demo.domain.account.exceptions.InvalidOperation;
import java.util.Optional;
import static org.mockito.Mockito.*;

public class ViewTests {
    public final String ibn = "1234";
    public final AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
    public final StatementsHistoryViewer statementsHistoryViewer = Mockito.mock(StatementsHistoryViewer.class);

    public final View view = new View(statementsHistoryViewer, accountRepository);
    public final Account account = Mockito.mock(Account.class);

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_execute() throws AccountNotFound {
        when(accountRepository.findByIban(ibn)).thenReturn(Optional.of(account));

        view.view(new ViewStatementRequest(ibn));
        verify(account).getStatements();
    }

    @Test(expected = AccountNotFound.class)
    public void should_throw_not_found_account_exception_if__account_doesnt_exist() throws AccountNotFound, InvalidOperation {
        when(accountRepository.findByIban(ibn)).thenReturn(Optional.empty());
        view.view(new ViewStatementRequest(ibn));
    }
}