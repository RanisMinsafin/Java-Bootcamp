package ex02;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("User1", 100);
        User user2 = new User("User2", 100);
        User user3 = new User("User3", 100);

        UsersArrayList users = new UsersArrayList();
        System.out.println("users.addUser(user1):");
        users.addUser(user1);
        System.out.println("\tsize = " + users.getNumberOfUsers());

        users.addUser(user2);
        users.addUser(user3);

        System.out.println();

        System.out.println("getUserByIndex():");
        for (int i = 0; i < users.getNumberOfUsers(); i++) {
            System.out.println("\tName of user " + users.getUserByIndex(i).getName());
        }

        System.out.println();
        System.out.println("getUserById():");
        for (int i = 0; i < users.getNumberOfUsers(); i++) {
            System.out.println("\tName of user " + users.getUserById(users.getUserByIndex(i).getIdentifier()).getName());
        }
        System.out.println();
    }
}
