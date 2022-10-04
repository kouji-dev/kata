package org.najih.demo.domain.account;

import java.util.List;

public interface StatementsHistoryViewer {
    void print(List<Statement> statements);
}
