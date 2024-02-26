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


@WebServlet("/adminpage")
public class AdminPage extends HttpServlet {

    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pro", "root", "baba11@");
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users")) {
      

            try (ResultSet resultSet = statement.executeQuery()) {
                List<Users> users = new ArrayList<>();

                while (resultSet.next()) {
                	int userId = resultSet.getInt("id");
                    String userName = resultSet.getString("username");
                    String pwd= resultSet.getString("password");
                    

                    

                    Users user = new Users(userId, userName,pwd);
                    users.add(user);
                }

                request.setAttribute("user", users);
                RequestDispatcher rd = request.getRequestDispatcher("user.jsp");
                rd.forward(request, response);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}