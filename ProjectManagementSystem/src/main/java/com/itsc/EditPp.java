package com.itsc;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/editprof")
public class EditPp extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pId = Integer.parseInt(request.getParameter("Id"));

        String name = request.getParameter("Name");
        String pwd = request.getParameter("pwd");



    
 

        try {
        	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pro", "root", "baba11@");
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET username = ?, password = ? WHERE id = ?");
            statement.setString(1, name);
            statement.setString(2, pwd);
            statement.setInt(3, pId);

            statement.executeUpdate();

            request.setAttribute("message", "profile updated successfully.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("profile");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to update profile. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/editprof.jsp");
            dispatcher.forward(request, response);
        }
    }
}
