package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    private Integer id;
    private String login;
    private String password;
    private ArrayList<Chatroom> userChatRooms;
    private ArrayList<Chatroom> rooms;


    public User(Integer id, String login, String password,
                ArrayList<Chatroom> userChatRooms, ArrayList<Chatroom> rooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.userChatRooms = userChatRooms;
        this.rooms = rooms;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (userChatRooms != null ? userChatRooms.hashCode() : 0);
        result = 31 * result + (rooms != null ? rooms.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        User user = (User) obj;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(userChatRooms, user.userChatRooms) &&
                Objects.equals(rooms, user.rooms);
    }

    @Override
    public String toString() {
        return "author={login = " + (login != null ? login : "null") + "," +
                " password = " + (password != null ? password : "null") + "," +
                " userChatRooms = " + (userChatRooms != null ? userChatRooms.toString() : "null") + "," +
                " rooms = " + (rooms != null ? rooms.toString() : "null")
                + "}";
    }
}
    