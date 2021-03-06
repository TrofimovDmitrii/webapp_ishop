package com.dmitriitrofimov.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = -313385363290634165L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		Cookie c = new Cookie("author", "study");
		c.setMaxAge(1800);
		c.setPath("/");
		c.setHttpOnly(true);
		resp.addCookie(c);
		PrintWriter out = resp.getWriter();
		out.println("<html><body>");
		out.println("Hello world!");
		out.println("</body></html>");

		out.close();
	}
}