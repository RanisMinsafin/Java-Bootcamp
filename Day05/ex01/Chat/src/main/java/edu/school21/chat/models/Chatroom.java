package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.Objects;

public class Chatroom {
    private Integer id;
    private String chatRoomName;
    private Integer chatRoomOwnerId;
    private ArrayList<Message> chatRoomMessages;

    public Chatroom(Integer id, String chatRoomName,
                    int chatRoomOwnerId, ArrayList<Message> chatRoomMessages) {
        this.id = id;
        this.chatRoomName = chatRoomName;
        this.chatRoomOwnerId = chatRoomOwnerId;
        this.chatRoomMessages = chatRoomMessages;
    }

    public Integer getId() {
        return id;
    }

    public String getChatRoomName() {
        return chatRoomName;
    }

    public void setChatRoomName(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }

    public int getChatRoomOwnerId() {
        return chatRoomOwnerId;
    }

    public void setChatRoomOwnerId(Integer chatRoomOwnerId) {
        this.chatRoomOwnerId = chatRoomOwnerId;
    }

    public ArrayList<Message> getChatRoomMessages() {
        return chatRoomMessages;
    }

    public void setChatRoomMessages(ArrayList<Message> chatRoomMessages) {
        this.chatRoomMessages = chatRoomMessages;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (chatRoomName != null ? chatRoomName.hashCode() : 0);
        result = 31 * result + (chatRoomOwnerId != null ? chatRoomOwnerId.hashCode() : 0);
        result = 31 * result + (chatRoomMessages != null ? chatRoomMessages.hashCode() : 0);
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
        Chatroom chatroom = (Chatroom) obj;
        return Objects.equals(id, chatroom.id) &&
                Objects.equals(chatRoomName, chatroom.chatRoomName) &&
                Objects.equals(chatRoomOwnerId, chatroom.chatRoomOwnerId) &&
                Objects.equals(chatRoomMessages, chatroom.chatRoomMessages);
    }

    @Override
    public String toString() {
        return "room={id = " + (id != null ? id : "null") +
                " chatRoomName = " + (chatRoomName != null ? chatRoomName : "null") + "," +
                " creator = " + (chatRoomOwnerId != null ? chatRoomOwnerId : "null") + "," +
                " messages = " + (chatRoomMessages != null ? chatRoomMessages.toString() : "null")
                + "}";
    }

}
