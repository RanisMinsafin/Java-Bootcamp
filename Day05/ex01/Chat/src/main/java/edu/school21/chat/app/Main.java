package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.repository.MessagesRepositoryJdbcImpl;

import edu.school21.chat.repository.MessagesRepositoryJdbcImpl;

import java.sql.*;
<<<<<<< HEAD
import java.util.Scanner;
=======
import java.util.NoSuchElementException;
import java.util.Optional;
>>>>>>> origin

public class Main {
    public static void main(String[] args) {
<<<<<<< HEAD
        try (Scanner scanner = new Scanner(System.in)) {
            MessagesRepositoryJdbcImpl repository = new MessagesRepositoryJdbcImpl(DatabaseConnector.start());
            while(true){
                System.out.println("Enter a message ID");
                String line = scanner.next();
                if(line.equals("exit")){
                    break;
                }
                int id = Integer.parseInt(line);
                repository.findById(id);
            }
=======
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Integer id = 2;
            MessagesRepositoryJdbcImpl messagesRepositoryJdbc = new MessagesRepositoryJdbcImpl(connection);
            Optional<Message> message = messagesRepositoryJdbc.findById(id);
            Message actualMessage = message.orElseThrow(() -> new NoSuchElementException("Message not found"));

            System.out.println(actualMessage.toString());
>>>>>>> origin
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }
}
