    package com.poly.controllers;
    /*
        Created by IntelliJ IDEA.
        User: QuocTriz
        Date: 1/6/2026
        Time: 3:11 AM
    */

    import java.io.IOException;

    import com.poly.model.Newsletter;
    import com.poly.service.CategoryService;
    import com.poly.service.NewsService;
    import com.poly.service.NewsletterService;
    import com.poly.service.impl.CategoryServiceImpl;
    import com.poly.service.impl.NewsServiceImpl;
    import com.poly.service.impl.NewsletterServiceImpl;

    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;

    @WebServlet({"/home", "/newsletter"})
    public class HomeServlet extends HttpServlet {

        private final NewsService newsService = new NewsServiceImpl();
        private final NewsletterService newsletterService = new NewsletterServiceImpl();
        private final CategoryService categoryService = new CategoryServiceImpl();

        public HomeServlet() {
            super();
        }

        @Override
        public void init() throws ServletException {
            super.init();
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            String path = request.getServletPath();

            if (path.equals("/home")) {
                request.setAttribute("categories", categoryService.getAll());
                request.setAttribute("homeNews", newsService.getHomeNews());
                request.setAttribute("topViews", newsService.getTop5View());
                request.setAttribute("latestNews", newsService.getTop5Latest());
                request.setAttribute("view", "layout/home.jsp");
                request.setAttribute("title", "Trang chủ");

                request.getRequestDispatcher("/views/layout.jsp").forward(request, response);
            }

        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String path = request.getServletPath();

            try {
                if (path.equals("/newsletter")) {
                    Newsletter n = buildFromRequest(request);
                    newsletterService.create(n);
                    response.sendRedirect(request.getContextPath() + "/home");
                }
            } catch (IllegalArgumentException e) {
                request.setAttribute("error", e.getMessage());
            }
        }

        private Newsletter buildFromRequest(HttpServletRequest request) {
            Newsletter n = new Newsletter();
            n.setEmail(request.getParameter("email"));
            n.setFullname(request.getParameter("fullname"));
            n.setEnabled(request.getParameter("enabled") != null);
            return n;
        }
    }
