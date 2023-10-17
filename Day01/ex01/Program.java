package ex01;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Mike", 2000);
        User user2 = new User("John", 1000);
        System.out.println("ID User1 = " + user1.getIdentifier());
        System.out.println("ID User2 = " + user2.getIdentifier());
    }
}
