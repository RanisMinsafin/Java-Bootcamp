package edu.school21.chat.models;

import java.util.Objects;

public class Message {
    private String author;
    private Integer chatRoomId;
    private String text;
    private String dateTime;

    public Message(String author, Integer chatRoomId, String text, String dateTime) {
        this.author = author;
        this.chatRoomId = chatRoomId;
        this.text = text;
        this.dateTime = dateTime;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getChatRoomId() {
        return chatRoomId;
    }

    public String getText() {
        return text;
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public int hashCode() {
        int result = author != null ? author.hashCode() : 0;
        result = 31 * result + (chatRoomId != null ? chatRoomId.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
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
        Message message = (Message) obj;
        return Objects.equals(author, message.author) &&
                Objects.equals(chatRoomId, message.chatRoomId) &&
                Objects.equals(text, message.text) &&
                Objects.equals(dateTime, message.dateTime);
    }

    @Override
    public String toString() {
        return "Message {author = " + (author != null ? author : "null") + "," +
                " chatRoomId = " + chatRoomId + "," +
                " text = " + (text != null ? text : "null") + "," +
                " dateTime = " + (dateTime != null ? dateTime : "null")
                + "}";
    }
}

