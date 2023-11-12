drop schema if exists chat cascade;
create schema if not exists chat;

create table chat.users
(
    id  serial primary key,
    login    varchar(16) unique not null,
    password varchar(16)        not null
);

create table chat.chatroom
(
    id    serial primary key,
    name           varchar(16) unique not null,
    owner int,
    foreign key (owner) references chat.users (id)
);

create table chat.message
(
    id        serial primary key,
    author    int,
    room      int,
    text      text      not null,
    date_time timestamp not null,
    foreign key (author) references chat.users (id),
    foreign key (room) references chat.chatroom (id)
);

create table chat.user_chatrooms
(
    user_id int,
    chatroom_id int,
    foreign key (user_id) references chat.users(id),
    foreign key (chatroom_id) references chat.chatroom(id)
);
