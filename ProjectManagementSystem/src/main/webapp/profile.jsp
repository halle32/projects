<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.itsc.Users" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 450px;
            margin: 50px auto;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333333;
            margin-bottom: 20px;
        }

        p {
            margin-bottom: 10px;
            color: #666666;
        }

        a {
            display: inline-block;
            margin-bottom: 10px;
            color: #007bff;
            text-decoration: none;
            margin-right: 10px;
        }

        a:hover {
            text-decoration: underline;
        }

        hr {
            border: none;
            border-top: 1px solid #cccccc;
            margin: 20px 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <a href="projects">Projects</a>
        <h1>Profile</h1>
        <% List<Users> users = (List<Users>) request.getAttribute("user");
        if (users != null && !users.isEmpty()) {
            for (Users user : users) {
        %>
        <p>User ID: <%= user.getId() %></p>
        <p>User Name: <%= user.getName() %></p>
        <p>Password: <%= user.getpwd() %></p>
        <a href="editp?id=<%= user.getId() %>">Edit</a>
        <a href="deluser?id=<%= user.getId() %>">Delete Account</a>
        <hr>
        <% } } else { %>
        <p>No users.</p>
        <% } %>
    </div>
</body>
</html>