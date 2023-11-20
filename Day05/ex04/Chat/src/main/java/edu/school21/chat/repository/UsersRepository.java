package edu.school21.chat.repository;

import edu.school21.chat.models.User;

import java.util.List;

public interface UsersRepository {
    List<User> findAll(int page, int size);
}
