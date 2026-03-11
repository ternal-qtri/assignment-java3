package com.poly.controllers;
/*
    Created by IntelliJ IDEA.
    User: QuocTriz
    Date: 1/7/2026
    Time: 9:38 AM
 */

import com.poly.model.News;
import com.poly.model.User;
import com.poly.service.CategoryService;
import com.poly.service.NewsService;
import com.poly.service.impl.CategoryServiceImpl;
import com.poly.service.impl.NewsServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 5 * 1024 * 1024
)
@WebServlet({"/create-post", "/create-post/create"})
public class CreatePostServlet extends HttpServlet {

    private final CategoryService categoryService = new CategoryServiceImpl();
    private final NewsService newsService = new NewsServiceImpl();

    public CreatePostServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.setAttribute("categories", categoryService.getAll());
        request.setAttribute("title", "Đăng bài");
        request.setAttribute("view", "layout/createPost.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String categoryId = request.getParameter("categoryId");

        Part filePart = request.getPart("avatar");
        String fileName = filePart.getSubmittedFileName();

        String uploadDir = request.getServletContext().getRealPath("/static/img/uploads");
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        String savedName = fileName;
        filePart.write(uploadDir + "/" + savedName);

        News news = new News();
        news.setId("N" + generateCode());
        news.setTitle(title);
        news.setContent(content);
        news.setImage(savedName);
        news.setPostedDate(new Date());
        news.setAuthorId(user.getId());
        news.setCategoryId(categoryId);

        try {
            newsService.create(news);
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
        }

        request.setAttribute("categories", categoryService.getAll());
        request.setAttribute("view", "layout/createPost.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    public static String generateCode() {
        int random = ThreadLocalRandom.current().nextInt(1000, 10000);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        String time = LocalTime.now().format(formatter);
        return random + time;
    }

}
