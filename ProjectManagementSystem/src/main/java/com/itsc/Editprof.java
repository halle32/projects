package com.itsc;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/editp")
public class Editprof extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	int id = Integer.parseInt(request.getParameter("id"));

        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pro", "root", "baba11@");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            List<Users> use = new ArrayList<>();


            // Iterate over the result set and populate the tasks list
            while (resultSet.next()) {
                Users user = new Users();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("username"));
                user.setpwd(resultSet.getString("password"));
               


                use.add(user);
            }

            // Close the database connection
            connection.close();

            // Set the tasks list as an attribute in the request object
            request.setAttribute("user", use);
            
            RequestDispatcher rd = request.getRequestDispatcher("editprof.jsp");
            rd.forward(request, response);
  
        
        

            

            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{}}}