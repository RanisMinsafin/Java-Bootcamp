CREATE TABLE users (
    user_id INT PRIMARY KEY,
    login VARCHAR NOT NULL,
    password VARCHAR NOT NULL
);

CREATE TABLE chatrooms (
    chatroom_id INT PRIMARY KEY,
    chatroom_name VARCHAR NOT NULL,
    owner_id INT NOT NULL,
    FOREIGN KEY (owner_id) REFERENCES users (user_id)
);

CREATE TABLE message (
    message_id INT PRIMARY KEY,
    message_author VARCHAR NOT NULL,
    chatroom_id INT NOT NULL,
    message_text VARCHAR NOT NULL,
    message_datetime date NOT NULL,
    FOREIGN KEY (chatroom_id) REFERENCES chatrooms (chatroom_id)
);

CREATE TABLE user_chatrooms (
    user_id INT NOT NULL,
    chatroom_id INT NOT NULL,
    PRIMARY KEY (user_id, chatroom_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (chatroom_id) REFERENCES chatrooms (chatroom_id)
);

CREATE TABLE user_socializes (
    chatroom_id INT NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (chatroom_id, user_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (chatroom_id) REFERENCES chatrooms (chatroom_id)
);

CREATE TABLE chatroom_messages (
    chatroom_id INT NOT NULL,
    message_id INT NOT NULL,
    PRIMARY KEY (chatroom_id, message_id),
    FOREIGN KEY (chatroom_id) REFERENCES chatrooms (chatroom_id),
    FOREIGN KEY (message_id) REFERENCES message (message_id)
);

