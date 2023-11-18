package edu.school21.chat.app;


import edu.school21.chat.models.Message;
import edu.school21.chat.repository.MessagesRepositoryJdbcImpl;

import java.sql.*;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        try {
            MessagesRepositoryJdbcImpl repository = new MessagesRepositoryJdbcImpl(DatabaseConnector.start());
            Message message = new Message(null, repository.findUserById(1),
                    repository.findRoomById(2), "Hello world!", LocalDateTime.now());
            repository.saveMessage(message);

            System.out.println("id = " + message.getId());
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }
}
