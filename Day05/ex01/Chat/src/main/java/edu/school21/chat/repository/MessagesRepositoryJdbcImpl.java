package edu.school21.chat.repository;

import edu.school21.chat.models.Message;

import java.sql.*;
import java.util.List;
import java.util.Optional;

//public class MessagesRepositoryJdbcImpl implements MessagesRepository {
public class MessagesRepositoryJdbcImpl  {
    private Connection connection;

    public MessagesRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    public Optional<Message> findById(Integer id) {
        Optional<Message> message = Optional.empty();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("select * from chat.message where id = ?");) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            String authorId = resultSet.getString("author");
            Integer roomId = resultSet.getInt("room");
            String text = resultSet.getString("text");
            String date = resultSet.getString("date_time");
             message= Optional.of(new Message(authorId, roomId, text, date));
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return message;
    }

//    public void delete(Message message);
//
//    public void save(Message message);
//
//    public void update(Message message);
//
//    public List<Message> findAll();
}
