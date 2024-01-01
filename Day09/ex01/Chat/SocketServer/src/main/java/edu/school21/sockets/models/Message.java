package edu.school21.sockets.models;

import edu.school21.sockets.services.MessageService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@ToString
public class Message {
    private Long id;
    private Long senderId;
    private String text;
    private LocalDateTime dateTime;

    public Message() {}
}
