package edu.school21.chat.repository;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static edu.school21.chat.repository.Query.*;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final Connection connection;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    @Override
    public Optional<Message> findMessageById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_MESSAGE.QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            int authorId = result.getInt("author_id");
            int roomId = result.getInt("room_id");
            String text = result.getString("text");
            Timestamp timestamp = result.getTimestamp("date_time");
            LocalDateTime dateTime = timestamp.toLocalDateTime();
            System.out.println("Message : {\n" +
                    "  id=" + id + ",\n" +
                    "  " + findUserById(authorId) + ",\n" +
                    "  " + findRoomById(roomId) + ",\n" +
                    "  text=\"" + text + "\",\n" +
                    "  dateTime=" + dateTime + "\n" +
                    "}");
            return Optional.of(new Message(id, findUserById(authorId), findRoomById(roomId), text, dateTime));
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return Optional.empty();
    }

    public User findUserById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER.QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            int userId = result.getInt("id");
            String login = result.getString("login");
            String password = result.getString("password");
            return new User(userId, login, password, new ArrayList<>(), new ArrayList<>());
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return null;
    }

    public Chatroom findRoomById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ROOM.QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            int roomId = result.getInt("id");
            String name = result.getString("name");
            int ownerId = result.getInt("owner_id");
            return new Chatroom(roomId, name, ownerId, new ArrayList<>());
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return null;
    }

    @Override
    public void saveMessage(Message message) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE.QUERY)) {
            checkMessage(message);

            preparedStatement.setInt(1, message.getCreator().getId());
            preparedStatement.setInt(2, message.getRoom().getId());
            preparedStatement.setString(3, message.getText());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(message.getDateTime()));
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                message.setId(result.getInt(1));
            } else {
                throw new SQLException("Failed to get generated id");
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }

    private void checkMessage(Message message){
        if (message.getCreator() == null || findUserById(message.getCreator().getId()) == null) {
            throw new NotSavedSubEntityException("Non existing user id");
        }
        if (message.getRoom() == null || findRoomById(message.getRoom().getId()) == null) {
            throw new NotSavedSubEntityException("Non existing room id");
        }
        if(message.getText() == null || message.getText().length() < 1) {
            throw new NotSavedSubEntityException("Non existing text");
        }
        if(message.getDateTime() == null) {
            throw new NotSavedSubEntityException("Non existing date");
        }
    }

    @Override
    public void updateMessage(Message message) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE.QUERY)) {
            if (message.getText() != null) {
                preparedStatement.setString(1, message.getText());
            } else {
                preparedStatement.setNull(1, Types.VARCHAR);
            }

            if (message.getDateTime() != null) {
                preparedStatement.setTimestamp(2, Timestamp.valueOf(message.getDateTime()));
            } else {
                preparedStatement.setNull(2, Types.TIMESTAMP);
            }

            preparedStatement.setInt(3, message.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
    }
}
