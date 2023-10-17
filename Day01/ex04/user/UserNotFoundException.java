package ex04.user;

public class UserNotFoundException extends IllegalArgumentException{
    public UserNotFoundException(String message){
        super(message);
    }
}
