package ex04.transaction;

import ex04.user.User;

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

        if (category.equals("debits")) {
            transferCategory = Transaction.Category.DEBIT;
        } else {
            transferCategory = Transaction.Category.CREDIT;
        }

        identifier = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.transferAmount = transferAmount;
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

    public UUID getIdentifier() {
        return identifier;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
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

