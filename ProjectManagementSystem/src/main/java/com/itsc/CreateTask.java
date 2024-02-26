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


@WebServlet("/createtask")
public class CreateTask extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		private static final String DB_URL = "jdbc:mysql://localhost:3306/pro";
	    private static final String DB_USER = "root";
	    private static final String DB_PASSWORD = "baba11@";

	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        // Retrieve form data
	    	int id = Integer.parseInt(req.getParameter("id"));
	    	
	        String name = req.getParameter("tname");
	     
	        String desc = req.getParameter("tdesc");

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	            String query = "INSERT INTO tas(name, description,project) VALUES ( ?, ?,?)";
	            PreparedStatement pstmt = conn.prepareStatement(query);
	            pstmt.setString(1, name);
	
	            pstmt.setString(2, desc);
	            pstmt.setInt(3,id);
	            pstmt.executeUpdate();

	            conn.close();

	            resp.sendRedirect("projects");
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

