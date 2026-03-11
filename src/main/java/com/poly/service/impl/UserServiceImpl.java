package com.poly.service.impl;

import com.poly.dao.UserDAO;
import com.poly.dao.impl.UserDAOImpl;
import com.poly.model.User;
import com.poly.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public List<User> getAll() {
        return userDAO.findAll();
    }

    @Override
    public int numberOfUsers() {
        return userDAO.countUsers();
    }

    @Override
    public int numberOfAdmins() {
        return userDAO.countAdmins();
    }

    @Override
    public int numberOfReporters() {
        return userDAO.countReporters();
    }

    @Override
    public User findId(int id) {
        return userDAO.findById(String.valueOf(id));
    }

    @Override
    public User login(String username, String password) {
        User u = userDAO.findByUsername(username);
        if (u == null)
            throw new IllegalArgumentException("Sai tên đăng nhập hoặc mật khẩu");

        if (!u.getPassword().equals(password))
            throw new IllegalArgumentException("Sai tên đăng nhập hoặc mật khẩu");

        if (!u.isStatus())
            throw new IllegalStateException("Tài khoản đã bị vô hiệu");

        return u;
    }

    @Override
    public void create(User user) {
        if (userDAO.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }

        if (userDAO.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Tên đăng nhập đã tồn tại");
        }
        userDAO.create(user);
    }

    @Override
    public void delete(int id) {
        if (userDAO.hasNews(id)) {
            throw new IllegalStateException("Không thể xóa người dùng đã có bài viết");
        } else {
            userDAO.delete(id);
        }
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public User getAuthorByNewsId(String newsId) {
        return userDAO.findAuthorByNewsId(newsId);
    }
}
