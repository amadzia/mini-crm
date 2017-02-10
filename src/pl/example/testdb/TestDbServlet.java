package pl.example.testdb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//connection variables
		String user="testowy";
		String password = "testowy";
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/crm?useSSL=false";
		String driver = "com.mysql.jdbc.Driver";
		
		
		//connect to database
		
		try{
			PrintWriter out = response.getWriter();
			
			out.println("Connecting to: " + jdbcUrl);
			
			Class.forName(driver);
			
			Connection myConnection = DriverManager.getConnection(jdbcUrl, user, password);
			
			out.println("Connected");
			
			myConnection.close();
		}
		catch (Exception e){
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
