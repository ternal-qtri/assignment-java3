package com.poly.controllers;
/*
    Created by IntelliJ IDEA.
    User: QuocTriz
    Date: 12/31/2025
    Time: 1:04 AM
 */

import com.poly.model.User;
import com.poly.service.UserService;
import com.poly.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Trang đăng nhập");
        request.setAttribute("view", "layout/login.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("title", "Trang đăng nhập");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = userService.login(username, password);

            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/home");
        } catch (IllegalStateException e) {
            request.setAttribute("error", e.getMessage());
            request.setAttribute("view", "layout/login.jsp");
            request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.setAttribute("view", "layout/login.jsp");
            request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
        }

    }
}
