package ex05;

import ex05.transaction.*;
import ex05.user.User;

import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private TransactionsService transactionsService = new TransactionsService();
    private static Scanner scanner = new Scanner(System.in);
    private boolean isDeveloper;

    public Menu(boolean isDeveloper) {
        this.isDeveloper = isDeveloper;
    }

    public void start() {
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        if (isDeveloper) {
            System.out.println("5. DEV – remove a transfer by ID");
            System.out.println("6. DEV – check transfer validity");
            System.out.println("7. Finish execution");
        } else {
            System.out.println("5. Finish execution");
        }
        makeOperation(scanner.nextInt());
    }

    public void makeOperation(Integer operation) {
        switch (operation) {
            case 1:
                addUser();
                break;
            case 2:
                viewBalance();
                break;
            case 3:
                performTransfer();
                break;
            case 4:
                viewAllTransactions();
                break;
            case 5:
                if (isDeveloper) {
                    removeTransferById();
                    break;
                } else {
                    finish();
                }
                break;
            case 6:
                if (isDeveloper) {
                    checkTransferValidity();
                    break;
                }
            case 7:
                if (isDeveloper) {
                    finish();
                    break;
                }
            default:
                System.out.println("Error: invalid operation. Please try again");
                start();
        }
    }

    public void addUser() {
        try {
            System.out.println("Enter a user name and a balance");
            String name = scanner.next();
            Integer balance = scanner.nextInt();
            User user = new User(name, balance);
            transactionsService.addUser(user);
            System.out.println("User with id = " + user.getIdentifier() + " is added");
        } catch (Exception exception) {
            System.out.println("Error: incorrect data of user. Please try again.");
            addUser();
        }
        restart();
    }

    public void viewBalance() {
        System.out.println("Enter a user ID");
        Integer userId = scanner.nextInt();
        try {
            Integer balance = transactionsService.getUser(userId).getBalance();
            String name = transactionsService.getUser(userId).getName();
            System.out.println(name + " - " + balance);
        } catch (Exception e) {
            System.out.println("Error: non-existent user ID. Please try again.");
        }
        restart();
    }

    public void performTransfer() {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        Integer senderId = scanner.nextInt();
        Integer recipientId = scanner.nextInt();
        Integer transferAmount = scanner.nextInt();

        try {
            transactionsService.makeTransaction(senderId, recipientId, transferAmount);
            System.out.println("The transfer is completed");
        } catch (IllegalTransactionException e) {
            System.out.println("Error: invalid transaction data. Please try again.");
        }
        restart();
    }

    public void viewAllTransactions() {
        System.out.println("Enter a user ID");
        Integer userId = scanner.nextInt();
        transactionsService.printUserTransactions(userId);
        restart();
    }

    public void removeTransferById() {
        System.out.println("Enter a user ID and a transfer ID");
        Integer userId = scanner.nextInt();
        String transferId = scanner.next();
        User user = transactionsService.getUser(userId);

        try {
            TransactionsLinkedList userTransactionsList = user.getTransactionsList();
            Transaction removedTransaction = userTransactionsList.findTransaction(UUID.fromString(transferId));
            userTransactionsList.removeTransaction(UUID.fromString(transferId));
            removedTransaction.printTransaction();
            System.out.println(" removed");
        } catch (TransactionNotFoundException e) {
            System.out.println("Error: transaction with non-existent ID");
        }
        restart();
    }

    public void checkTransferValidity() {
        System.out.println("Check results:");
        transactionsService.printUnpairedTransactions(transactionsService.getUnpairedTransactions());
        restart();
    }

    public void restart() {
        System.out.println("---------------------------------------------------------");
        start();
    }

    public void finish() {
        System.exit(0);
    }
}

