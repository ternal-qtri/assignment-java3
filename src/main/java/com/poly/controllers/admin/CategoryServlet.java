package com.poly.controllers.admin;


import com.poly.model.Category;
import com.poly.service.CategoryService;
import com.poly.service.impl.CategoryServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/admin/category", "/admin/category/create", "/admin/category/delete", "/admin/category/update"})
public class CategoryServlet extends HttpServlet {

    private final CategoryService categoryService = new CategoryServiceImpl();

    public CategoryServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if ("/admin/category".equals(path)) {
            String view = "layout/admin/category/table.jsp";

            request.setAttribute("categories", categoryService.getAll());
            request.setAttribute("title", "Quản lý loại tin tức");
            request.setAttribute("view", view);
            request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        Category c = new Category();

        if ("/admin/category/create".equals(path)) {
            c.setId(request.getParameter("id"));
            c.setName(request.getParameter("name"));

            try {
                categoryService.create(c);
                response.sendRedirect(request.getContextPath() + "/admin/category");
                return;
            } catch (IllegalArgumentException e) {
                request.setAttribute("error", e.getMessage());
            }
        } else if ("/admin/category/delete".equals(path)) {
            String id = request.getParameter("id");
            try {
                categoryService.delete(id);
                response.sendRedirect(request.getContextPath() + "/admin/category");
                return;
            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
            }
        } else if ("/admin/category/update".equals(path)) {
            c.setId(request.getParameter("id"));
            c.setName(request.getParameter("name"));

            try {
                categoryService.update(c);
                response.sendRedirect(request.getContextPath() + "/admin/category");
                return;
            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
            }
        }

        request.setAttribute("categories", categoryService.getAll());
        request.setAttribute("view", "layout/admin/category/table.jsp");
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }
}
