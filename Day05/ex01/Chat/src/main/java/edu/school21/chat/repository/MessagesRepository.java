package edu.school21.chat.repository;

import edu.school21.chat.models.Message;

import java.util.ArrayList;
import java.util.Optional;

public interface MessagesRepository {
    Optional<Message> findById(Long id);
    void delete(Message message);
    void save(Message message);
    void update(Message message);
    ArrayList<Message> findAll();
}
