package ex03.transaction;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private Transaction first = null;
    private Transaction last = null;
    private Integer numberOfTransactions = 0;

    public TransactionsLinkedList() {
    }

    public Transaction getFirst() {
        return first;
    }

    public Transaction getLast() {
        return last;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        if (first == null) {
            addFirst(transaction);
        } else {
            last.setNext(transaction);
            transaction.setNext(null);
            transaction.setPrev(last);
            last = transaction;
        }
        numberOfTransactions++;
    }

    private void addFirst(Transaction transaction) {
        transaction.setNext(null);
        transaction.setPrev(null);
        first = transaction;
        last = transaction;
    }

    public Integer getNumberOfTransactions() {
        return numberOfTransactions;
    }

    @Override
    public void removeTransaction(UUID uuid) throws TransactionNotFoundException {
        Transaction transaction = findTransaction(uuid);
        deleteTransaction(transaction);
    }

    private Transaction findTransaction(UUID uuid) throws TransactionNotFoundException {
        Transaction current = first;
        while (current != null) {
            if (current.getIdentifier().equals(uuid)) {
                return current;
            }
            current = current.getNext();
        }
        throw new TransactionNotFoundException("Error: transaction with non-existent ID");
    }

    private void deleteTransaction(Transaction transaction) {
        if (transaction.getNext() == null) {
            last = transaction.getPrev();
        } else if (transaction.getPrev() == null) {
            first = transaction.getNext();
        } else {
            transaction.getPrev().setNext(transaction.getNext());
            transaction.getNext().setPrev(transaction.getPrev());
        }
        numberOfTransactions--;
    }

    @Override
    public Transaction[] toArray() {
        Transaction current = first;
        Transaction[] array = new Transaction[numberOfTransactions];
        for (int i = 0; i < numberOfTransactions; i++) {
            array[i] = current;
            current = current.getNext();
        }
        return array;
    }
}
