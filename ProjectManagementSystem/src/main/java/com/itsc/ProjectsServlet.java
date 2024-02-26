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
import jakarta.servlet.http.HttpSession;

@WebServlet("/projects")
public class ProjectsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("id");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pro", "root", "baba11@");
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM projects WHERE admin = ?")) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                List<Project> projects = new ArrayList<>();

                while (resultSet.next()) {
                	int projectId = resultSet.getInt("id");
                    String projectName = resultSet.getString("name");
                    String projectDescription = resultSet.getString("description");
                    
                    int adminId = resultSet.getInt("admin");
                    Project project = new Project(projectId, projectName, projectDescription, adminId);
                    projects.add(project);
                }

                request.setAttribute("projects", projects);
                RequestDispatcher rd = request.getRequestDispatcher("project.jsp");
                rd.forward(request, response);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}