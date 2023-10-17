package ex02;

public interface UsersList {
    void addUser(User user);

    User getUserById(Integer userId) throws UserNotFoundException;

    User getUserByIndex(Integer index);

    Integer getNumberOfUsers();
}
