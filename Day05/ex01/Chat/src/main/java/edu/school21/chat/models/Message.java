package edu.school21.chat.models;

import java.util.Objects;

public class Message {
    private Integer authorId;
    private Integer roomId;
    private String text;
    private String dateTime;

    public Message(Integer authorId, Integer chatRoomId, String text, String dateTime) {
        this.authorId = authorId;
        this.roomId = roomId;
        this.text = text;
        this.dateTime = dateTime;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public Integer getChatRoomId() {
        return roomId;
    }

    public String getText() {
        return text;
    }

    public String getDateTime() {
        return dateTime;
    }

    @Override
    public int hashCode() {
        int result = authorId != null ? authorId.hashCode() : 0;
        result = 31 * result + (roomId != null ? roomId.hashCode() : 0);
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
        return Objects.equals(authorId, message.authorId) &&
                Objects.equals(roomId, message.roomId) &&
                Objects.equals(text, message.text) &&
                Objects.equals(dateTime, message.dateTime);
    }

    @Override
    public String toString() {
        return "Message {authorId = " + (authorId != null ? authorId : "null") + "," +
                " roomId = " + roomId + "," +
                " text = " + (text != null ? text : "null") + "," +
                " dateTime = " + (dateTime != null ? dateTime : "null")
                + "}";
    }
}

