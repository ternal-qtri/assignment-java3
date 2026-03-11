package com.poly.service;

import com.poly.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    int numberOfUsers();
    int numberOfAdmins();
    int numberOfReporters();
    User findId(int id);
    User login(String username, String password);
    void create(User user);
    void delete(int id);
    void update(User user);
    User getAuthorByNewsId(String newsId);
}
