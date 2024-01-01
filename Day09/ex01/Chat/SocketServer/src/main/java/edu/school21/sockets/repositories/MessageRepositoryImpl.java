package edu.school21.sockets.repositories;

import edu.school21.sockets.models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Component("messageRepository")
public class MessageRepositoryImpl implements MessageRepository {
    private JdbcTemplate template;

    @Autowired
    public MessageRepositoryImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Message> findById(Long id) {
        List<Message> messages = template.query(
                "select * from service.messages where id = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Message.class));
        return messages.isEmpty() ? Optional.empty() : Optional.ofNullable(messages.get(0));
    }

    @Override
    public List<Message> findAll() {
        List<Message> messages = template.query(
                "select * from service.messages",
                new BeanPropertyRowMapper<>(Message.class));
        return messages;
    }

    @Override
    public void save(Message message) {
        int result = template.update(
                "insert into service.messages(sender_id, message_text, sending_time) values (?, ?, ?)",
                message.getSenderId(), message.getText(), Timestamp.valueOf(message.getDateTime()));

        if (result == 0) {
            System.err.println("Message hasn't saved");
        }
    }

    @Override
    public void update(Message message) {
        int result = template.update(
                "update service.messages set sender_id = ?, message_text = ?, sending_time = ? where id = ?",
                message.getSenderId(), message.getText(), Timestamp.valueOf(message.getDateTime()), message.getId());

        if (result == 0) {
            System.err.println("Message hasn't updated");
        }
    }

    @Override
    public void delete(Long id) {
        int result = template.update(
                "delete from service.messages where id = ?", id);

        if (result == 0) {
            System.err.println("Message hasn't deleted");
        }
    }
}
