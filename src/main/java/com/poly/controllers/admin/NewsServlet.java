package com.poly.controllers.admin;

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

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,   // 1MB
        maxFileSize = 5 * 1024 * 1024,      // 5MB
        maxRequestSize = 10 * 1024 * 1024   // 10MB
)
@WebServlet({"/admin/news", "/admin/news/delete", "/admin/news/edit", "/admin/news/update"})
public class NewsServlet extends HttpServlet {

    private final NewsService newsService = new NewsServiceImpl();
    private final CategoryService categoryService = new CategoryServiceImpl();

    public NewsServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        String view = "";

        if (path.equals("/admin/news")) {
            view = "layout/admin/news/table.jsp";
            request.setAttribute("newsList", newsService.getAll());
            request.setAttribute("title", "Quản lý tin tức");
            request.setAttribute("view", view);
            request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
        } else if (path.equals("/admin/news/edit")) {
            String id = request.getParameter("id");
            News news = newsService.getNewsById(id);
            view = "layout/admin/news/edit.jsp";
            request.setAttribute("categories", categoryService.getAll());
            request.setAttribute("news", news);
            request.setAttribute("title", "Quản lý tin tức");
            request.setAttribute("view", view);
            request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        String view = "";

        if ("/admin/news/delete".equals(path)) {
            view = "layout/admin/news/table.jsp";
            User currentUser = (User) request.getSession().getAttribute("user");
            if (currentUser == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
            String id = request.getParameter("id");
            try {
                newsService.delete(id, currentUser);
                response.sendRedirect(request.getContextPath() + "/admin/news");
                return;

            } catch (IllegalArgumentException e) {
                request.setAttribute("error", e.getMessage());
                request.setAttribute("newsList", newsService.getAll());
                request.setAttribute("view", view);
                request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
            }
        } else if ("/admin/news/update".equals(path)) {
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            News n = new News();
            n.setId(request.getParameter("id"));
            n.setTitle(request.getParameter("title"));
            n.setContent(request.getParameter("content"));
            n.setCategoryId(request.getParameter("categoryId"));
            n.setHome(request.getParameter("isHome") != null);

            Part imagePart = request.getPart("image");
            String imageFileName = null;

            if (imagePart != null && imagePart.getSize() > 0) {
                String uploadDir = request.getServletContext()
                        .getRealPath("/static/img/uploads");
                File dir = new File(uploadDir);
                if (!dir.exists()) dir.mkdirs();

                imageFileName = imagePart.getSubmittedFileName();
                imagePart.write(uploadDir + File.separator + imageFileName);
            }

            News old = newsService.getNewsById(n.getId());
            n.setImage(imageFileName != null ? imageFileName : old.getImage());

            newsService.update(n, user);
            response.sendRedirect(request.getContextPath() + "/admin/news");
        }
    }
}
