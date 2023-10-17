package ex03.transaction;

import ex03.user.User;

import java.util.UUID;

public class Transaction {
    public enum Category {
        DEBIT, CREDIT
    }

    private UUID identifier;
    private User recipient;
    private User sender;
    private Category transferCategory;
    private Integer transferAmount;

    private Transaction next;
    private Transaction prev;

    public Transaction(User sender, User recipient, String category, Integer transferAmount) {
        if (isNotValidParameters(sender, recipient, category, transferAmount)) {
            System.out.println("Error: invalid transaction data");
            System.exit(-1);
        }

        identifier = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.transferAmount = transferAmount;
        transferHandler(category);
    }

    private boolean isNotValidParameters(User sender, User recipient, String category, Integer transferAmount) {
        if (sender.equals(recipient)) {
            return true;
        }
        if (transferAmount == 0) {
            return true;
        }
        if (category.equals("credits") && transferAmount >= 0) {
            return true;
        }
        if (category.equals("debits") && transferAmount <= 1) {
            return true;
        }
        return false;
    }

    private void transferHandler(String category) {
        if (category.equals("debits")) {
            transferCategory = Category.DEBIT;
        } else {
            transferCategory = Category.CREDIT;
        }

        Integer tmp = transferAmount;
        if (transferAmount < 0) {
            tmp *= -1;
        }
        sender.setBalance(sender.getBalance() - tmp);
        recipient.setBalance(recipient.getBalance() + tmp);
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public Category getTransferCategory() {
        return transferCategory;
    }

    public Integer getTransferAmount() {
        return transferAmount;
    }

    public Transaction getNext() {
        return next;
    }

    public Transaction getPrev() {
        return prev;
    }

    public void setNext(Transaction next) {
        this.next = next;
    }

    public void setPrev(Transaction prev) {
        this.prev = prev;
    }
}

