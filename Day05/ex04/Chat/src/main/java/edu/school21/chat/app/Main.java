package edu.school21.chat.app;
import edu.school21.chat.models.User;
import edu.school21.chat.repository.UsersRepositoryJdbcImpl;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            UsersRepositoryJdbcImpl usersRepositoryJdbc = new UsersRepositoryJdbcImpl(DatabaseConnector.start());
            List<User> usersList = usersRepositoryJdbc.findAll(0,5);
            System.out.println(usersList.toString());
        } catch (SQLException exception) {
            System.out.println(exception);;
        }

    }
}
