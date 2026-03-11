package com.poly.controllers;
/*
    Created by IntelliJ IDEA.
    User: QuocTriz
    Date: 12/31/2025
    Time: 1:21 AM
 */

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/not-found")
public class NotFoundServlet extends HttpServlet {

	public NotFoundServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        request.setAttribute("title", "404 - Not Found");
		request.getRequestDispatcher("/views/error/404.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
