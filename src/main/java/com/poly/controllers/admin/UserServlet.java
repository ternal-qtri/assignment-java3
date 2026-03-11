package com.poly.controllers.admin;

import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.beanutils.BeanUtils;

import com.poly.model.User;
import com.poly.model.enums.Role;
import com.poly.model.enums.Status;
import com.poly.service.UserService;
import com.poly.service.impl.UserServiceImpl;
import com.poly.utils.FileUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 5 * 1024 * 1024,
        maxRequestSize = 10 * 1024 * 1024
)
@WebServlet({"/admin/user", "/admin/user/create", "/admin/user/save", "/admin/user/edit", "/admin/user/update", "/admin/user/delete"})
public class UserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();
    Role[] roles = Role.values();
    Status[] statuses = Status.values();

    public UserServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        String view = "";

        switch (path) {
            case "/admin/user" ->
                view = "layout/admin/user/table.jsp";
            case "/admin/user/create" ->
                view = "layout/admin/user/edit.jsp";
            case "/admin/user/edit" -> {
                int id = Integer.parseInt(request.getParameter("id"));
                User user = userService.findId(id);
                request.setAttribute("user", user);
                view = "layout/admin/user/edit.jsp";
            }
        }

        request.setAttribute("statuses", statuses);
        request.setAttribute("roles", roles);
        request.setAttribute("users", userService.getAll());
        request.setAttribute("title", "Quản lý người dùng");
        request.setAttribute("view", view);
        request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        User user = getForm(request);
        if (path.equals("/admin/user/save")) {

            boolean roleValue = Boolean.parseBoolean(request.getParameter("role"));
            user.setRole(Role.fromDb(roleValue));

            boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
            user.setGender(gender);

            Part avatarPart = request.getPart("avatar");
            String avatarName = FileUtil.saveFile(avatarPart, request.getServletContext(), "/static/img/uploads");
            if (avatarName != null) {
                user.setAvatar(avatarName);
            }
            user.setStatus(true);

            try {
                userService.create(user);
                response.sendRedirect(request.getContextPath() + "/admin/user");
            } catch (IllegalArgumentException e) {
                request.setAttribute("error", e.getMessage());
                request.setAttribute("roles", roles);
                request.setAttribute("user", user);
                request.setAttribute("view", "layout/admin/user/edit.jsp");
                request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
            }
        } else if (path.equals("/admin/user/update")) {
            user.setId(Integer.parseInt(request.getParameter("id")));
            boolean roleValue = Boolean.parseBoolean(request.getParameter("role"));
            user.setRole(Role.fromDb(roleValue));
            boolean statusValue = Boolean.parseBoolean(request.getParameter("status"));
            user.setStatus(statusValue);
            boolean genderValue = Boolean.parseBoolean(request.getParameter("gender"));
            user.setGender(genderValue);
            String birthdayStr = request.getParameter("birthday");
            if (birthdayStr != null && !birthdayStr.isEmpty()) {
                user.setBirthday(java.sql.Date.valueOf(birthdayStr));
            }

            User oldUser = userService.findId(user.getId());
            Part avatarPart = request.getPart("avatar");
            String avatarName = FileUtil.saveFile(
                    avatarPart,
                    request.getServletContext(),
                    "/static/img/uploads"
            );
            if (avatarName != null) {
                user.setAvatar(avatarName);
            } else {
                user.setAvatar(oldUser.getAvatar());
            }

            userService.update(user);
            response.sendRedirect(request.getContextPath() + "/admin/user");
        } else if (path.equals("/admin/user/delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                userService.delete(id);
                response.sendRedirect(request.getContextPath() + "/admin/user");
            } catch (IllegalStateException e) {
                request.setAttribute("statuses", statuses);
                request.setAttribute("roles", roles);
                request.setAttribute("users", userService.getAll());
                request.setAttribute("error", e.getMessage());
                request.setAttribute("view", "layout/admin/user/table.jsp");
                request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
            }
        }

        request.setAttribute("title", "Quản lý người dùng");
    }

    private User getForm(HttpServletRequest request) {
        User user = new User();
        try {
            var params = new HashMap<>(request.getParameterMap());
            params.remove("role");
            params.remove("birthday");
            BeanUtils.populate(user, params);
            String birthdayStr = request.getParameter("birthday");
            if (birthdayStr != null && !birthdayStr.isEmpty()) {
                user.setBirthday(java.sql.Date.valueOf(birthdayStr));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
