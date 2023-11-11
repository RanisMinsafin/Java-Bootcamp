package edu.school21.chat.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static Connection connection;

    public static void main(String[] args) {
        try {
            connection = getNewConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            // Получаем данные из таблицы users
            ResultSet resultSet = executeQuery("SELECT * FROM chat.users");

            // Выводим результат
            while (resultSet.next()) {
                int userId =resultSet.getInt(1);
                String login = resultSet.getString("1");
                String password = resultSet.getString("1");

                System.out.println("User ID: " + userId + ", Login: " + login + ", Password: " + password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static ResultSet executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    private static void close() throws SQLException {
        connection.close();
    }

    public static Connection getNewConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "";
        String passwd = "";
        return DriverManager.getConnection(url, user, passwd);
    }
}
