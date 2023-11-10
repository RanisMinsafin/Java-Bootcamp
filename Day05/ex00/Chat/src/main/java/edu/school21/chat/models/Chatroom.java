package edu.school21.chat.models;

import java.util.ArrayList;

public class Chatroom {
    private String chatRoomName;
    private int chatRoomOwnerId;
    private ArrayList<Message> messagesList;

    public Chatroom(String chatRoomName, int chatRoomOwnerId, ArrayList<Message> messagesList) {
        this.chatRoomName = chatRoomName;
        this.chatRoomOwnerId = chatRoomOwnerId;
        this.messagesList = messagesList;
    }
}
