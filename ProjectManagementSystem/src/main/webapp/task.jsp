<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.itsc.Task" %>
<%@ page import="java.util.List" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tasks</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
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

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 8px;
            border-bottom: 1px solid #cccccc;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
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

        .no-tasks {
            text-align: center;
            color: #666666;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Tasks</h1>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <% List<Task> tasks = (List<Task>) request.getAttribute("tasks");
                   if (tasks != null && !tasks.isEmpty()) {
                       for (Task task : tasks) {
                %>
                <tr>
                    <td><%= task.getId() %></td>
                    <td><%= task.getName() %></td>
                    <td><%= task.getDescription() %></td>
                    <td><a href="delt?id=<%= task.getId() %>">Delete</a></td>
                </tr>
                <% } } else { %>
                <tr>
                    <td colspan="4" class="no-tasks">No tasks found.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <a href="createtask.jsp?id=<%= request.getParameter("id") %>">Create Task</a>
    </div>
</body>
</html>