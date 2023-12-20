package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static school21.spring.service.repositories.Query.*;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private Connection connection;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Error: Unable to get a connection. Cause: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID.getQuery())) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error: Unable to find user by id. Cause: " + e.getMessage(), e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        try (PreparedStatement statement = connection.prepareStatement(FIND_ALL.getQuery())) {
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User tempUser = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email")
                );
                users.add(tempUser);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException("Error: Unable to find all users. Cause: " + e.getMessage(), e);
        }
    }

    @Override
    public void save(User user) {
        try (PreparedStatement statement = connection.prepareStatement(SAVE_USER.getQuery())) {
            statement.setString(1, user.getEmail());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error: Unable to save user. Cause: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(User user) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER.getQuery())) {
            statement.setString(1, user.getEmail());
            statement.setLong(2, user.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error: Unable to update user. Cause: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER.getQuery())) {
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error: Unable to delete user. Cause: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL.getQuery())) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("email")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error: Unable to find user by id. Cause: " + e.getMessage(), e);
        }
        return Optional.empty();
    }
}
