package edu.school21.chat.repository;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static edu.school21.chat.repository.Query.*;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private final Connection connection;

    public UsersRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        this.connection = dataSource.getConnection();
    }

    @Override
    public List<User> findAll(int page, int size) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FINDS_ALL_USER.getQuery())) {
            preparedStatement.setInt(1, size);
            int offset = page * size;
            preparedStatement.setInt(2, offset);
            ResultSet result = preparedStatement.executeQuery();
            List<User> allUsers = new ArrayList<>();
            while (result.next()) {
                Integer userId = result.getInt("user_id");
                String login = result.getString("login");
                String password = result.getString("password");
                ArrayList<Chatroom> createdRooms = getRoomsList("created_chatrooms", result);
                ArrayList<Chatroom> participatedRooms = getRoomsList("participated_chatrooms", result);
                User user = new User(userId, login, password, createdRooms, participatedRooms);
                allUsers.add(user);
            }
            return allUsers;
        } catch (
                SQLException exception) {
            System.out.println(exception);
        }
        return null;
    }

    private ArrayList<Chatroom> getRoomsList(String columnLabel, ResultSet resultSet) throws SQLException {
        String createdChatroomsString = resultSet.getString(columnLabel);
        ArrayList<Chatroom> roomsList = new ArrayList<>();
        if (!createdChatroomsString.isEmpty() && createdChatroomsString != null) {
            createdChatroomsString = createdChatroomsString.replaceAll("\\{", "").replaceAll("\\}", "");
            String[] chatroomIdsArray = createdChatroomsString.split(",");
            for (String chatroomId : chatroomIdsArray) {
                if (!chatroomId.equals("NULL") && chatroomId != null) {
                    Integer roomId = Integer.valueOf(chatroomId.trim());
                    roomsList.add(findRoomById(roomId));
                }
            }
        }
        return roomsList;
    }

    public Chatroom findRoomById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ROOM.getQuery())) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int roomId = result.getInt("id");
                String name = result.getString("name");
                int ownerId = result.getInt("owner_id");
                return new Chatroom(roomId, name, ownerId, new ArrayList<>());
            }
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return null;
    }
}
