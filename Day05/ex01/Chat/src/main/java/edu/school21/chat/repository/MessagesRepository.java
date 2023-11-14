package edu.school21.chat.repository;

import edu.school21.chat.models.Message;

import java.util.List;
import java.util.Optional;

public interface MessagesRepository {
    Optional<Message> findById(Integer id);

    void delete(Message message);

    void save(Message message);

    void update(Message message);

    List<Message> findAll();
}
