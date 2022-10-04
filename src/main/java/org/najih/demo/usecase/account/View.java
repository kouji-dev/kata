package org.najih.demo.usecase.account;

import org.najih.demo.domain.account.Account;
import org.najih.demo.domain.account.AccountRepository;
import org.najih.demo.domain.account.StatementsHistoryViewer;

import java.util.Optional;

public class View {

    private final StatementsHistoryViewer statementsHistoryViewer;
    private final AccountRepository accountRepository;

    public View(StatementsHistoryViewer statementsHistoryViewer, AccountRepository accountRepository) {
        this.statementsHistoryViewer = statementsHistoryViewer;
        this.accountRepository = accountRepository;
    }

    public void view(ViewStatementRequest viewStatementRequest) throws AccountNotFound {
        Optional<Account> account = accountRepository.findByIban(viewStatementRequest.getIban());
        if (account.isPresent()) {
            statementsHistoryViewer.print(account.get().getStatements());
        } else throw new AccountNotFound("Account with Iban: " + viewStatementRequest.getIban() + " is not found");
    }
}
