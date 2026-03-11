package com.poly.dao;

import com.poly.model.User;

import java.util.List;

public interface UserDAO {
    List<User> findAll();
    int countUsers();
    int countAdmins();
    int countReporters();
    User findById(String id);
    User findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    void create(User u);
    void update(User u);
    boolean hasNews(int userId);
    void delete(int id);
    User findAuthorByNewsId(String newsId);
}
