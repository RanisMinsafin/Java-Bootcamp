package ex04.transaction;

import ex04.user.User;
import ex04.user.UsersArrayList;
import ex04.user.UsersList;

public class TransactionsService {
    private UsersList usersList = new UsersArrayList();

    public void addUser(User user) {
        usersList.addUser(user);
    }

    public Integer getUserBalance(User user) {
        return user.getBalance();
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
}
