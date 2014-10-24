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

import com.mysql.jdbc.ResultSetMetaData;

/**
 * Servlet implementation class PracticeServlet
 */
@WebServlet("/PracticeServlet")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String querySesult;
	

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
		writer.println("<html>\n"
				+ "<head>\n"
				+ "<title>Comments</title>\n"
				+ "</head>\n"
				+ "<body>\n"
				+ "<form method=\"post\" action=\"#\">\n"
				+ "<label>Comment:</label>&nbsp;&nbsp;&nbsp;<textarea name=\"comment\"></textarea>\n"
				+ "<br>\n" + "<input type=\"submit\" value=\"Submit\">\n"
				+ "</form>\n" + "</body>\n" + "</html>");
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
		String comment = request.getParameter("comment");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/coursework", "root", "mint");
			Statement statement = connection.createStatement();
			String sql="insert into comments values('"+request.getParameter("comment")+"')";
			System.out.println(sql);
			statement.executeUpdate(sql); 
			
			ResultSet resultSet=statement.executeQuery("select*from comments");
		
			//Statement statement = connection.createStatement();
			ResultSet resulSet = statement.executeQuery(sql);
			java.sql.ResultSetMetaData rsmd = resulSet.getMetaData();
			//ResultSetMetaData rsmd = resultSet.getMetaData();
			int columnsNumber = rsmd.getColumnCount();                     

			// Iterate through the data in the result set and display it. 

			while (resulSet.next()) {
			//Print one row          
			for(int i = 1 ; i <= columnsNumber; i++){

			      System.out.print(resulSet.getString(i) + " "); //Print one element of a row

			}

			  System.out.println();//Move to the next line to print the next row.           

			    }

			int columnsNumber1 = rsmd.getColumnCount();

			
			
			
			
			
			
			
			
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
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
