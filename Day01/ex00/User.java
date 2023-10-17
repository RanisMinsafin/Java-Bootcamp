package ex00;

public class User {
    private Integer identifier;
    private String name;
    private Integer balance;

    public User(Integer identifier, String name, Integer balance) {
        this.setIdentifier(identifier);
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

    public void setIdentifier(Integer identifier) {
        if (identifier >= 0) {
            this.identifier = identifier;
        }
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
}
