package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.repository.MessagesRepository;
import edu.school21.chat.repository.MessagesRepositoryJdbcImpl;
import java.sql.*;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        try {
            MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(DatabaseConnector.start());
            Optional<Message> messageOptional = messagesRepository.findMessageById(2);
            if(messageOptional.isPresent()){
                Message message = messageOptional.get();
                message.setText("Bye");
                message.setDateTime(null);
                messagesRepository.updateMessage(message);
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }
}
