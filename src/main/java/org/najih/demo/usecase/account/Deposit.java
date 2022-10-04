package org.najih.demo.usecase.account;

import org.najih.demo.domain.account.Account;
import org.najih.demo.domain.account.AccountRepository;
import org.najih.demo.domain.account.exceptions.InvalidOperation;

import java.util.Optional;

public class Deposit {
    public final AccountRepository accountRepository;

    public Deposit(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    Account deposit(DepositRequest depositRequest) throws AccountNotFound, InvalidOperation {
        Optional<Account> account = accountRepository.findByIban(depositRequest.getIban());
        if (account.isPresent()) {
            account.get().deposit(depositRequest.getAmount());
        } else throw new AccountNotFound("Account with Iban: " + depositRequest.getIban() + " is not found");
        return account.get();
    }
}
