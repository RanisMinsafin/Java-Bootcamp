package ex05.user;

public class UserIdsGenerator {
    private static UserIdsGenerator instance;
    private static Integer lastGeneratedId;

    private UserIdsGenerator() {
        lastGeneratedId = 0;
    }

    public static UserIdsGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdsGenerator();
        }
        return instance;
    }

    public Integer generateId() {
        lastGeneratedId += 1;
        return lastGeneratedId;
    }
}

