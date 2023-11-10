drop schema if exists chat cascade;
create schema if not exists chat;

create table chat.users
(
    user_id  serial primary key,
    user_login    varchar(16) unique not null,
    user_password varchar(16)        not null
);

create table chat.chatroom
(
    chatroom_id    serial primary key,
    chatroom_name           varchar(16) unique not null,
    chatroom_owner int,
    foreign key (chatroom_owner) references chat.users (user_id)
);

create table chat.message
(
    message_id        serial primary key,
    message_author    int,
    message_room      int,
    message_text      text      not null,
    message_date_time timestamp not null,
    foreign key (message_author) references chat.users (user_id),
    foreign key (message_room) references chat.chatroom (chatroom_id)
);

create table chat.user_chatrooms
(
    user_id int,
    chatroom_id int,
    foreign key (user_id) references chat.users(user_id),
    foreign key (chatroom_id) references chat.chatroom(chatroom_id)
);
