package ex05.user;

public class UsersArrayList implements UsersList {
    private Integer numberOfUsers = 0;
    private Integer capacityOfUsers = 10;
    private User[] users = new User[capacityOfUsers];

    @Override
    public void addUser(User user) {
        if (numberOfUsers.equals(capacityOfUsers)) {
            ensureCapacity();
        }
        users[numberOfUsers++] = user;
    }

    private void ensureCapacity() {
        capacityOfUsers *= 2;
        User[] tmp = new User[capacityOfUsers];
        int i = 0;
        for (User num : users) {
            tmp[i++] = num;
        }
        users = tmp;
    }

    @Override
    public User getUserById(Integer userId) throws UserNotFoundException {
        if (userId == null) {
            throw new UserNotFoundException("User with a non-existent ID");
        }
        for (User user : users) {
            if (user.getIdentifier().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getUserByIndex(Integer index) {
        if (index >= 0 && index < numberOfUsers) {
            return users[index];
        }
        return null;
    }

    @Override
    public Integer getNumberOfUsers() {
        return numberOfUsers;
    }
}
