package org.najih.demo.usecase.account;

import org.najih.demo.domain.account.Account;
import org.najih.demo.domain.account.AccountRepository;
import org.najih.demo.domain.account.exceptions.InvalidOperation;

import java.util.Optional;

public class Withdraw {

    public final AccountRepository accountRepository;

    public Withdraw(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    Account withdraw(WithdrawRequest withdrawRequest) throws AccountNotFound, InvalidOperation {
        Optional<Account> account = accountRepository.findByIban(withdrawRequest.getIban());
        if (account.isPresent()) {
            account.get().withdraw(withdrawRequest.getAmount());
        } else throw new AccountNotFound("Account with Iban: " + withdrawRequest.getIban() + " is not found");
        return account.get();
    }
}
