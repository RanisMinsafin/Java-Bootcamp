package repositories.user;

import models.User;

public interface UsersRepository {
    User findByLogin(String login);
    void update(User user);
    boolean authenticate(String login, String password);
}