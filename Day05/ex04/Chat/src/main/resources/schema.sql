drop schema if exists chat cascade;
create schema if not exists chat;

create table chat.users
(
    id       serial primary key,
    login    varchar(16) unique not null,
    password varchar(16)        not null
);

create table chat.chatroom
(
    id       serial primary key,
    name     varchar(16) unique not null,
    owner_id int,
    foreign key (owner_id) references chat.users (id)
);

create table chat.message
(
    id        serial primary key,
    author_id int,
    room_id   int,
    text      text,
    date_time timestamp,
    foreign key (author_id) references chat.users (id),
    foreign key (room_id) references chat.chatroom (id)
);

create table chat.user_chatroom
(
    user_id     int,
    chatroom_id int,
    foreign key (user_id) references chat.users (id),
    foreign key (chatroom_id) references chat.chatroom (id)
);
