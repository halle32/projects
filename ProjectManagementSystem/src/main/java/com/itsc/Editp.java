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



@WebServlet("/editpro")
public class Editp extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pId = Integer.parseInt(request.getParameter("projectId"));
        int adId = Integer.parseInt(request.getParameter("adminId"));
        String pTitle = request.getParameter("projectName");
        String pDescription = request.getParameter("projectDescription");



    
 

        try {
        	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pro", "root", "baba11@");
            PreparedStatement statement = connection.prepareStatement("UPDATE projects SET name = ?, description = ? WHERE id = ?");
            statement.setString(1, pTitle);
            statement.setString(2, pDescription);
            statement.setInt(3, pId);

            statement.executeUpdate();

            request.setAttribute("message", "Task updated successfully.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("projects");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to update task. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/editpro.jsp");
            dispatcher.forward(request, response);
        }
    }
}
