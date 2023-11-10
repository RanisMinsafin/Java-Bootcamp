package edu.school21.chat.models;

public class Message {
    private String author;
    private Long room;
    private String text;
    private String dateTime;

    public Message(String author, Long room, String text, String dateTime) {
        this.author = author;
        this.room = room;
        this.text = text;
        this.dateTime = dateTime;
    }
}
