package edu.school21.chat.repository;
import edu.school21.chat.models.Message;

import java.util.ArrayList;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    @Override
    public Optional<Message> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public ArrayList<Message> findAll() {
        return null;
    }

    @Override
    public void delete(Message message) {

    }

    @Override
    public void save(Message message) {

    }

}
