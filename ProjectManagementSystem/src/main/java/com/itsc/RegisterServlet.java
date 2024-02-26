package com.itsc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		private static final String DB_URL = "jdbc:mysql://localhost:3306/pro";
	    private static final String DB_USER = "root";
	    private static final String DB_PASSWORD = "baba11@";

	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        // Retrieve form data
	        String name = req.getParameter("username");
	     
	        String password = req.getParameter("password");

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	            String query = "INSERT INTO users (username, password) VALUES ( ?, ?)";
	            PreparedStatement pstmt = conn.prepareStatement(query);
	            pstmt.setString(1, name);
	
	            pstmt.setString(2, password);
	            pstmt.executeUpdate();
	            conn.close();

	            resp.sendRedirect("login.jsp");
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

