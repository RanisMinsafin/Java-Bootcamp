package edu.school21.chat.repository;

public enum Query {
    FIND_ROOM("select * from chat.chatroom where id  = ?"),
    FINDS_ALL_USER("WITH paginated_users AS (\n" +
            "    SELECT\n" +
            "        u.id AS user_id,\n" +
            "        u.login,\n" +
            "        u.password,\n" +
            "        c.id AS chatroom_id,\n" +
            "        c.name AS chatroom_name\n" +
            "    FROM\n" +
            "        chat.users u\n" +
            "            LEFT JOIN chat.chatroom c ON u.id = c.owner_id\n" +
            "    ORDER BY\n" +
            "        u.id, c.id\n" +
            "    LIMIT ? OFFSET ?\n" +
            ")\n" +
            "SELECT\n" +
            "    pu.user_id,\n" +
            "    pu.login,\n" +
            "    pu.password,\n" +
            "    ARRAY_AGG(DISTINCT pu.chatroom_id) AS created_chatrooms,\n" +
            "    ARRAY_AGG(DISTINCT uc.chatroom_id) AS participated_chatrooms\n" +
            "FROM\n" +
            "    paginated_users pu\n" +
            "        LEFT JOIN chat.user_chatroom uc ON pu.user_id = uc.user_id\n" +
            "GROUP BY\n" +
            "    pu.user_id, pu.login, pu.password\n" +
            "ORDER BY\n" +
            "    pu.user_id;\n");
    private final String QUERY;

    Query(String QUERY) {
        this.QUERY = QUERY;
    }

    public String getQuery() {
        return QUERY;
    }
}
