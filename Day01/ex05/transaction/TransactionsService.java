package ex05.transaction;

import ex05.user.User;
import ex05.user.UsersArrayList;
import ex05.user.UsersList;

import java.util.UUID;

public class TransactionsService {
    private UsersList usersList = new UsersArrayList();

    public boolean isUserListEmpty() {
        return usersList.getNumberOfUsers() == 0;
    }

    public User getUser(Integer userId) {
        return usersList.getUserById(userId);
    }

    public void addUser(User user) {
        usersList.addUser(user);
    }

    public void makeTransaction(Integer senderId, Integer recipientId, Integer transferAmount) {
        User sender = usersList.getUserById(senderId);
        User recipient = usersList.getUserById(recipientId);

        if (sender.equals(recipient) || transferAmount > sender.getBalance() || transferAmount < 0) {
            throw new IllegalTransactionException("Error: invalid transaction data");
        }

        Transaction debit = new Transaction(sender, recipient, "debits", transferAmount);
        Transaction credit = new Transaction(sender, recipient, "credits", -transferAmount);
        debit.setIdentifier(credit.getIdentifier());
        credit.setIdentifier(debit.getIdentifier());

        sender.addTransaction(credit);
        recipient.addTransaction(debit);

        sender.setBalance(sender.getBalance() - transferAmount);
        recipient.setBalance(recipient.getBalance() + transferAmount);
    }

    public Transaction[] getUnpairedTransactions() {
        if (usersList.getNumberOfUsers() == 0) {
            System.out.println("User's list is empty");
            return null;
        }

        TransactionsLinkedList unpairedTransactions = new TransactionsLinkedList();
        Transaction[] allTransactions = getAllTransactions();

        if (allTransactions.length == 1) {
            unpairedTransactions.addTransaction(allTransactions[0]);
        } else {
            for (int i = 0; i < allTransactions.length; i++) {
                boolean isPaired = false;
                for (int j = 0; j < allTransactions.length; j++) {
                    if (i != j && allTransactions[i].getIdentifier() == allTransactions[j].getIdentifier()) {
                        isPaired = true;
                        break;
                    }
                }
                if (!isPaired) {
                    unpairedTransactions.addTransaction(allTransactions[i]);
                }
            }
        }

        return unpairedTransactions.toArray();
    }

    public Transaction[] getAllTransactions() {
        if (usersList.getNumberOfUsers() == 0) {
            System.out.println("User's list is empty");
            return null;
        }

        TransactionsLinkedList allTransactions = new TransactionsLinkedList();
        for (int i = 0; i < usersList.getNumberOfUsers(); i++) {
            User user = usersList.getUserByIndex(i);
            TransactionsLinkedList userTransactionsList = user.getTransactionsList();
            if (userTransactionsList.getNumberOfTransactions() != 0) {
                for (Transaction transaction : user.getTransactionsList().toArray()) {
                    allTransactions.addTransaction(transaction);
                }
            }
        }
        return allTransactions.toArray();
    }

    public void printUserTransactions(Integer userId) {
        User user = getUser(userId);
        Transaction[] userTransactions = user.getTransactionsList().toArray();

        for (Transaction transaction : userTransactions) {
            Integer transferAmount = transaction.getTransferAmount();
            String name = transaction.getRecipient().getName();
            Integer id = transaction.getRecipient().getIdentifier();

            if (transferAmount < 0) {
                System.out.print("To ");
            } else {
                System.out.print("From ");
                name = transaction.getSender().getName();
                id = transaction.getSender().getIdentifier();
            }

            System.out.println(name + "(id = " + id
                    + ")" + " " + transferAmount
                    + " with id = " + transaction.getIdentifier());
        }
    }

    public void printUnpairedTransactions(Transaction[] unpairedTransactions) {
        for (Transaction transaction : unpairedTransactions) {
            String name = transaction.getSender().getName();
            Integer id = transaction.getSender().getIdentifier();
            UUID transactionId = transaction.getIdentifier();

            System.out.println(name + "(id = " + id + ") has an unacknowledged transfer" +
                    " id = " + transactionId);
        }
    }
}
