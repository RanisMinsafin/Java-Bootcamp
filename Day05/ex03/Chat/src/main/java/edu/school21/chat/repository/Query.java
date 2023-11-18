package edu.school21.chat.repository;

public enum Query {
    FIND_MESSAGE("select * from chat.message where id  = ?"),
    FIND_USER("select * from chat.users where id  = ?"),
    FIND_ROOM("select * from chat.chatroom where id  = ?"),
    UPDATE("update chat.message set text = ?, date_time = ? where id = ?"),
    SAVE("insert into chat.message (author_id, room_id, text, date_time) VALUES (?, ?, ?, ?) returning id");

    private final String QUERY;

    Query(String QUERY) {
        this.QUERY = QUERY;
    }

    public String getQuery() {
        return QUERY;
    }
}
