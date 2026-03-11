package com.poly.controllers.admin;

import com.poly.model.Newsletter;
import com.poly.model.User;
import com.poly.model.enums.Role;
import com.poly.service.NewsletterService;
import com.poly.service.impl.NewsletterServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({
        "/admin/newsletter",
        "/admin/newsletter/create",
        "/admin/newsletter/save",
        "/admin/newsletter/delete"
})
public class NewsletterServlet extends HttpServlet {

    private final NewsletterService newsletterService = new NewsletterServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("newsletters", newsletterService.getAll());
        request.setAttribute("title", "Quản lý tài khoản nhận tin");
        request.setAttribute("view", "layout/admin/newsletter/table.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        try {
            switch (path) {

                case "/admin/newsletter/create" -> {
                    Newsletter n = buildFromRequest(request);
                    newsletterService.create(n);
                }

                case "/admin/newsletter/save" -> {
                    Newsletter n = buildFromRequest(request);
                    newsletterService.update(n);
                }

                case "/admin/newsletter/delete" -> {
                    String email = request.getParameter("email");
                    System.out.println(email);
                    newsletterService.deleteByEmail(email);
                }
            }

            response.sendRedirect(request.getContextPath() + "/admin/newsletter");
            return;

        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
        }

        request.setAttribute("newsletters", newsletterService.getAll());
        request.setAttribute("view", "layout/admin/newsletter/table.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    private Newsletter buildFromRequest(HttpServletRequest request) {
        Newsletter n = new Newsletter();
        n.setEmail(request.getParameter("email"));
        n.setFullname(request.getParameter("fullname"));
        n.setEnabled(request.getParameter("enabled") != null);
        return n;
    }
}