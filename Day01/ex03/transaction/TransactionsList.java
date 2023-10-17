package ex03.transaction;

import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction);
    void removeTransaction(UUID uuid);
    Transaction[] toArray();
}
