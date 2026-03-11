package com.poly.controllers;
/*
    Created by IntelliJ IDEA.
    User: QuocTriz
    Date: 1/6/2026
    Time: 11:45 PM
 */

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
import java.util.Map;

@WebServlet("/post-detail")
public class PostDetailServlet extends HttpServlet {

    NewsService newsService = new NewsServiceImpl();
    UserService userService = new UserServiceImpl();

    public PostDetailServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id == null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
        News news = newsService.getNewsById(id);
        User author = userService.getAuthorByNewsId(id);

        List<News> relatedNews = newsService.getRelatedNews(
                news.getCategoryId(),
                news.getId()
        );

        request.setAttribute("news", news);
        request.setAttribute("author", author);
        request.setAttribute("relatedNews", relatedNews);
        request.setAttribute("view", "layout/postDetail.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
