package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    private String login;
    private String password;
    private ArrayList<Chatroom> userChatRooms;

    public User(String login, String password, ArrayList<Chatroom> userChatRooms) {
        this.login = login;
        this.password = password;
        this.userChatRooms = userChatRooms;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Chatroom> getUserChatRooms() {
        return userChatRooms;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (userChatRooms != null ? userChatRooms.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null && obj.getClass() != getClass()) {
            return false;
        }
        User user = (User) obj;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(userChatRooms, user.userChatRooms);
    }

    @Override
    public String toString() {
        return "User {login = " + (login != null ? login : "null") + "," +
                " password = " + (password != null ? password : "null") + "," +
                " userChatRooms = " + (userChatRooms != null ? userChatRooms.toString() : "null")
                + "}";
    }
}
    