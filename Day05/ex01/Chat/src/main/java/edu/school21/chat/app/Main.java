package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.repository.MessagesRepositoryJdbcImpl;

import java.sql.*;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Main {
    final private static String PROPERTIES_PATH = "src/main/resources/application.properties";
    final private static Application application = new Application(PROPERTIES_PATH);
    private static final String DB_URL = application.getPropertyValue("db.url");
    private static final String DB_USER = application.getPropertyValue("db.login");
    private static final String DB_PASSWORD = application.getPropertyValue("db.password");

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Integer id = 2;
            MessagesRepositoryJdbcImpl messagesRepositoryJdbc = new MessagesRepositoryJdbcImpl(connection);
            Optional<Message> message = messagesRepositoryJdbc.findById(id);
            Message actualMessage = message.orElseThrow(() -> new NoSuchElementException("Message not found"));

            System.out.println(actualMessage.toString());
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }
}
