package com.tektree.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Comment() {
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
		String comment= request.getParameter("comment");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection connection=null;
		try{
			connection=DriverManager.getConnection("jdbc:mysql://localhost/coursework","root","mint");
			Statement statement=connection.createStatement();
			ResultSet rs= statement.executeQuery("insert into comments"); //insert the comment to a table
			
			PrintWriter out = null;
			out.println("<HTML>");
            // Start on the body
            out.println("<BODY>");
            out.println("<CENTER>");
            out.println("<table BORDER=1 CELLPADDING=0 CELLSPACING=0 WIDTH=50% >");
            while (rs.next()){

            out.println("<tr>");
            out.print("<td>"+rs.getString("Name")+ "</td>");
            out.print("<td>"+rs.getString("Comments")+ "</td>");
            out.println("</tr>");
            
            }
		}
			
		 catch (SQLException e) {
			 out = null;
			out.println("</table>");
             out.println("</CENTER>");
             out.println("</BODY></HTML>");
		}
		finally{
			if(connection !=null){
				System.out.println("Will close the connection object.");
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		
		writer.print("You have posted " + request.getParameter("comment"));
	}

}
