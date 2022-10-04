package org.najih.demo.domain.account;

import java.util.Optional;

public interface AccountRepository {
    public Optional<Account> findByIban(String iban);
}
