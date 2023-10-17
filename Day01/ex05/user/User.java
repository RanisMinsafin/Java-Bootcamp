package ex05.user;

import ex05.transaction.Transaction;
import ex05.transaction.TransactionsLinkedList;

import java.util.UUID;

public class User {
    private Integer identifier;
    private String name;
    private Integer balance;

    private TransactionsLinkedList transactionsList = new TransactionsLinkedList();

    public User(String name, Integer balance) {
        this.identifier = UserIdsGenerator.getInstance().generateId();
        this.setName(name);
        this.setBalance(balance);
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public void setBalance(Integer balance) {
        if (balance < 0) {
            System.out.println("Error: incorrect balance of user");
            System.exit(-1);
        }
        this.balance = balance;
    }

    public TransactionsLinkedList getTransactionsList() {
        return transactionsList;
    }

    public void addTransaction(Transaction transaction) {
        transactionsList.addTransaction(transaction);
    }

    public void deleteTransaction(UUID identifier) {
        transactionsList.removeTransaction(identifier);
    }
}
