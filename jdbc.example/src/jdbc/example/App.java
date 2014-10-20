package jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

	public static void main(String[] args) throws ClassNotFoundException,SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection=null;
		try{
			connection=DriverManager.getConnection("jdbc:mysql://localhost/coursework","root","mint");
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery("select*from customer");
			while (resultSet.next()){
				int id = resultSet.getInt(1);
				String dateOfBirth = resultSet.getString("date_of_birth");
				String firstName =resultSet.getString(2);
				System.out.println("id: "+id);
				System.out.println("date_of_birthday "+ dateOfBirth);
				System.out.println("FirstName: "+firstName);
				System.out.println("--------------");
				
			}
		}
		finally{
			if(connection !=null){
				System.out.println("Will clso the connection object.");
				connection.close();
			}
		}

	}

}
