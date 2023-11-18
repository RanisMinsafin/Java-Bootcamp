package edu.school21.chat.repository;

import edu.school21.chat.models.Message;

import java.util.Optional;

public interface MessagesRepository {
    Optional<Message> findMessageById(int id);
    void saveMessage(Message message);
}
