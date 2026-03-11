package com.poly.controllers.admin;
/*
    Created by IntelliJ IDEA.
    User: QuocTriz
    Date: 1/6/2026
    Time: 7:54 PM
 */

import com.poly.service.CategoryService;
import com.poly.service.NewsService;
import com.poly.service.NewsletterService;
import com.poly.service.UserService;
import com.poly.service.impl.CategoryServiceImpl;
import com.poly.service.impl.NewsServiceImpl;
import com.poly.service.impl.NewsletterServiceImpl;
import com.poly.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();
    private NewsService newsService = new NewsServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();
    private NewsletterService newsletterService = new NewsletterServiceImpl();

    public AdminServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = "layout/admin/admin.jsp";

        request.setAttribute("totalUsers", userService.numberOfUsers());
        request.setAttribute("totalAdmins", userService.numberOfAdmins());
        request.setAttribute("totalReporters", userService.numberOfReporters());
        request.setAttribute("totalNews", newsService.numberOfNews());
        request.setAttribute("totalCategories", categoryService.numberOfCategories());
        request.setAttribute("totalNewletters", newsletterService.numberOfAccounts());
        request.setAttribute("title", "Trang quản trị");
        request.setAttribute("view", view);
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
