package com.dmitriitrofimov.servlet;

import com.dmitriitrofimov.model.Model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/s11")
public class Servlet11 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("model", new Model());
		req.getRequestDispatcher("/WEB-INF/model.jsp").forward(req, resp);
	}
}