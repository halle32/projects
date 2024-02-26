<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.itsc.Project" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Projects</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
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

        form {
            margin-bottom: 20px;
        }

        input[type="text"] {
            padding: 8px;
            border: 1px solid #cccccc;
            border-radius: 4px;
            margin-right: 10px;
            width: 300px;
        }

        input[type="submit"] {
            padding: 8px 12px;
            background-color: #007bff;
            color: #ffffff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
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
        <div style="text-align:right;">
            <a href="profile">Profile</a>
        </div>
        <h1>Projects</h1>
        <div style="text-align: right;">
            <a href="createproject.jsp">Create Project?</a>
        </div>
        <form action="searchservlet" method="GET">
            <input type="text" name="searchQuery" placeholder="Search by project name">
            <input type="submit" value="Search">
        </form>
        <% List<Project> projects = (List<Project>) request.getAttribute("projects");
        if (projects != null && !projects.isEmpty()) {
            for (Project project : projects) {
        %>
        <div style="background-color: #f9f9f9; padding: 10px; border-radius: 4px;">
            <p><strong>Project ID:</strong> <%= project.getId() %></p>
            <p><strong>Project Name:</strong> <%= project.getName() %></p>
            <p><strong>Project Description:</strong> <%= project.getDescription() %></p>
            <div style="margin-top: 10px;">
                <a href="editservlet?id=<%= project.getId() %>">Edit</a>
                <a href="delpro?id=<%= project.getId() %>">Delete</a>
                <a href="tasks?id=<%= project.getId() %>">Tasks</a>
            </div>
        </div>
        <hr>
        <% } } else { %>
        <p>No projects found.</p>
        <% } %>
    </div>
</body>
</html>