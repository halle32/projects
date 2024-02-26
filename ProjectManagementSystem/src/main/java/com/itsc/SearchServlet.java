package com.itsc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itsc.Project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/searchservlet")
public class SearchServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// JDBC database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pro";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "baba11@";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("id");

        // Create a list to store the search results
        List<Project> searchResults = new ArrayList<>();

        // Database connection and query
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT id, name, description, admin FROM projects WHERE name LIKE ? and admin = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + searchQuery + "%");
            statement.setInt(2,userId);

            ResultSet resultSet = statement.executeQuery();

            // Iterate through the result set and create Project objects
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int admin = userId;

                Project project = new Project(id, name, description, admin);
                searchResults.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Set the search results as a request attribute and forward to searchresults.jsp
        request.setAttribute("searchResults", searchResults);
        request.getRequestDispatcher("searchresults.jsp").forward(request, response);
    }
}