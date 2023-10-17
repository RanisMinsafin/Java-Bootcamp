package ex00;

public class Program {
    public static void main(String[] args) {
        User john = new User(1, "John", 1000);
        User mike = new User(2, "Mike", 1000);

        Transaction outcome = new Transaction(john, mike, "credits", -500);
        System.out.println(john.getName() + " -> " + mike.getName() + ", " +
                outcome.getTransferAmount() + ", OUTCOME, transaction " + outcome.getIdentifier());
        Transaction income = new Transaction(mike, john,"debits", 500);
        System.out.println(mike.getName() + " -> " + john.getName() + ", +" +
                income.getTransferAmount() + ", INCOME, transaction " + income.getIdentifier());
    }
}
