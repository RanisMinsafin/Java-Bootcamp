package edu.school21;

import edu.school21.database.connector.DatabaseConnector;
import edu.school21.manager.OrmManager;
import edu.school21.model.User;

public class Main {
    public static void main(String[] args) {
        OrmManager ormManager = new OrmManager(DatabaseConnector.start());
        ormManager.createTable(User.class);

        User user1 = new User(1L, "Lebron", "James", 38);
        User user2 = new User(2L, "James", "Harden", 34);

        ormManager.save(user1);
        ormManager.save(user2);

        User user3 = ormManager.findById(1L, User.class);
        System.out.println("Found user: " + user3);

        User user4 = new User(1L, "Stephan", "Curry", 35);
        ormManager.update(user4);

        ormManager.dropTable(User.class);
    }
}