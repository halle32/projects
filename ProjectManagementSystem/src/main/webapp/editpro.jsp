<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.itsc.Project" %>
<%@ page import="java.util.List" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Project</title>
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

        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        label {
            display: block;
            margin-bottom: 10px;
            color: #666666;
        }

        input[type="text"],
        textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #cccccc;
            border-radius: 4px;
            margin-bottom: 10px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px 0;
            background-color: #007bff;
            color: #ffffff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        p {
            text-align: center;
            color: #666666;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Edit Project</h1>
        <form action="editpro" method="GET">
            <% int adid = (int) session.getAttribute("id"); %>
            <% List<Project> proj = (List<Project>) request.getAttribute("proj");
            if (proj != null && !proj.isEmpty()) {
                Project pro = proj.get(0); // Assuming only one project is retrieved

                // Retrieve project properties
                int id = pro.getId();
                String name = pro.getName();
                String description = pro.getDescription();
            %>
            <input type="hidden" name="projectId" value="<%= id %>">
            <input type="hidden" name="adminId" value="<%= adid %>">
            <label for="projectName">Project Name:</label>
            <input type="text" id="projectName" name="projectName" value="<%= name %>">
            <label for="projectDescription">Project Description:</label>
            <textarea id="projectDescription" name="projectDescription"><%= description %></textarea>
            <input type="submit" value="Update">
            <% } else { %>
            <p>No project found.</p>
            <% } %>
        </form>
    </div>
</body>
</html>