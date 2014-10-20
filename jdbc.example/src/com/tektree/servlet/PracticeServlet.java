package com.tektree.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PracticeServlet
 */
@WebServlet("/PracticeServlet")
public class PracticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PracticeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		writer.println("<html>\n" + 
				"<head>\n" + 
				"<title>Comments</title>\n" + 
				"</head>\n" + 
				"<body>\n" + 
				"<form method=\"post\" action=\"#\">\n" + 
				"<label>Comment:</label>&nbsp;&nbsp;&nbsp;<textarea name=\"comment\"></textarea>\n" + 
				"<br>\n" + 
				"<input type=\"submit\" value=\"Submit\">\n" + 
				"</form>\n" + 
				"</body>\n" + 
				"</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		
		// store the comment in database here
		// Save and get a request-scoped value
		//request.setAttribute("com.tektree.servlet", "comment");
		//Object comment = request.getAttribute("com.tektree.servlet");
		HttpSession session = request.getSession();
		Object comment = null;
		session.setAttribute("comment", comment);
		
		writer.print("You have posted " + request.getParameter("comment"));
	}

}
