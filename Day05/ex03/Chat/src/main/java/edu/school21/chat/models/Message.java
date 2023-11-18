package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private Integer id;
    private User creator;
    private Chatroom room;
    private String text;
    private LocalDateTime dateTime;

    public Message(Integer id, User creator, Chatroom room, String text, LocalDateTime dateTime) {
        this.id = id;
        this.creator = creator;
        this.room = room;
        this.text = text;
        this.dateTime = dateTime;
    }

    public Integer getId() {
        return id;
    }

    public User getCreator() {
        return creator;
    }

    public Chatroom getRoom() {
        return room;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public int hashCode() {
        int result = creator != null ? creator.hashCode() : 0;
        result = 31 * result + (room != null ? room.hashCode() : 0);
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
        return Objects.equals(creator, message.creator) &&
                Objects.equals(room, message.room) &&
                Objects.equals(text, message.text) &&
                Objects.equals(dateTime, message.dateTime);
    }

    @Override
    public String toString() {
        return "Message {" + creator.toString() + "," +
                room.toString() + "," +
                " text = " + (text != null ? text : "null") + "," +
                " dateTime = " + (dateTime != null ? dateTime.toString() : "null")
                + "}";
    }
}

