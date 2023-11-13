package edu.school21.chat.app;


import java.sql.*;

public class Main {
    final private static String PROPERTIES_PATH = "src/main/resources/application.properties";
    final private static Application application = new Application(PROPERTIES_PATH);
    private static final String DB_URL = application.getPropertyValue("db.url");
    private static final String DB_USER = application.getPropertyValue("db.login");
    private static final String DB_PASSWORD = application.getPropertyValue("db.password");

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "select * from chat.users";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String login = resultSet.getString("login");
                        String password = resultSet.getString("password");
                        System.out.println("ID: " + id + ", Login: " + login + ", Password: " + password);
                    }
                }
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }
}
