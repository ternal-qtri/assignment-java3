package com.poly.controllers;

import com.poly.model.News;
import com.poly.model.User;
import com.poly.service.NewsService;
import com.poly.service.UserService;
import com.poly.service.impl.NewsServiceImpl;
import com.poly.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/author"})
public class AuthorServlet extends HttpServlet {

    private final NewsService newsService = new NewsServiceImpl();
    private final UserService userService = new UserServiceImpl();

    public AuthorServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        int authorId = Integer.parseInt(idParam);

        User author = userService.findId(authorId);
        List<News> posts = newsService.getByAuthor(authorId);

        request.setAttribute("author", author);
        request.setAttribute("posts", posts);
        request.setAttribute("title", "Trang tác giả");
        request.setAttribute("view", "layout/author.jsp");

        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
