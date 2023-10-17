package ex03;

import ex03.user.User;
import ex03.transaction.Transaction;
import ex03.transaction.TransactionsLinkedList;

import java.util.Arrays;

public class Program {
    public static void main(String[] args) {
        User john = new User("John", 1000);
        User mike = new User("Mike", 1000);
        Transaction outcome = new Transaction(john, mike, "credits", -200);
        Transaction income = new Transaction(mike, john, "debits", 500);
        Transaction outcome1 = new Transaction(mike, john, "credits", -500);

        john.addTransaction(outcome);
        john.addTransaction(income);
        john.addTransaction(outcome1);

        System.out.println("\nAdded 3 transactions -> number of transactions = " + john.getTransactionsList().getNumberOfTransactions());
        System.out.println("First transaction: " + john.getTransactionsList().getFirst().getSender().getName() + " -> " +
                john.getTransactionsList().getFirst().getRecipient().getName() + " " + john.getTransactionsList().getFirst().getTransferAmount());
        System.out.println("Last transaction: " + john.getTransactionsList().getLast().getSender().getName() + " -> " +
                john.getTransactionsList().getLast().getRecipient().getName() + " " + john.getTransactionsList().getLast().getTransferAmount());

        john.getTransactionsList().removeTransaction(john.getTransactionsList().getFirst().getIdentifier());
        System.out.println("After the first transaction was deleted -> number of transactions = " + john.getTransactionsList().getNumberOfTransactions());
        System.out.println("First transaction: " + john.getTransactionsList().getFirst().getSender().getName() + " -> " +
                john.getTransactionsList().getFirst().getRecipient().getName() + " " + john.getTransactionsList().getFirst().getTransferAmount());
        System.out.println("Last transaction: " + john.getTransactionsList().getLast().getSender().getName() + " -> " +
                john.getTransactionsList().getLast().getRecipient().getName() + " " + john.getTransactionsList().getLast().getTransferAmount());

        Transaction[] array = john.getTransactionsList().toArray();
        System.out.println("Transactions array: " + Arrays.toString(array));
        System.out.println();
    }
}
