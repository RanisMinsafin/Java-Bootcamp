package edu.school21.chat.repository;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private Connection connection;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    @Override
    public Optional<Message> findById(int id) {
        String query = "select * from chat.message where id  = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            int authorId = result.getInt("author_id");
            int roomId = result.getInt("room_id");
            String text = result.getString("text");
            String dateTime = result.getString("date_time").replace('-', '/');

            System.out.println("Message : {\n" +
                    "  id=" + authorId + ",\n" +
                    "  " + findUserById(authorId) + ",\n" +
                    "  " + findRoomById(roomId) + ",\n" +
                    "  text=\"" + text + "\",\n" +
                    "  dateTime=" + dateTime + "\n" +
                    "}");
            return Optional.of(new Message(authorId, roomId, text, dateTime));
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return Optional.empty();
    }

    public User findUserById(int id) {
        String query = "select * from chat.users where id  = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
        String query = "select * from chat.chatroom where id  = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
}
