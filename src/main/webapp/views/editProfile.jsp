<%@ page import="uz.doublem.delevery_for_exam.repository.UserRepository" %>
<%@ page import="java.util.Optional" %>
<%@ page import="uz.doublem.delevery_for_exam.entity.Users" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>



<html>
<head>
    <title>Edit Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        h1 {
            color: #333;
            text-align: center;
            margin-top: 30px;
        }

        .container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            margin-top: 50px;
        }

        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        label {
            font-weight: bold;
        }

        input[type="text"],
        input[type="email"],
        input[type="password"],
        input[type="date"] {
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            width: 100%;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border-radius: 5px;
            border: none;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Edit Profile</h1>
    <form action="/editProfile" method="post">
        <input type="hidden" name="id" value="${param.id}">

        <label for="name">First Name:</label>
        <input type="text" name="name" id="name" value="${param.name}" required>

        <label for="last_name">Last Name:</label>
        <input type="text" name="last_name" id="last_name" value="${param.last_name}" required>

        <label for="email">Email:</label>
        <input type="email" name="email" id="email" value="${param.email}" required>

        <label for="phone_number">Phone Number:</label>
        <input type="text" name="phone_number" id="phone_number" value="${param.phone_number}" required>

        <label for="password">Password:</label>
        <input type="password" name="password" id="password" value="${param.password}" required>

        <label for="date_of_birth">Date of Birth:</label>
        <input type="date" name="date_of_birth" id="date_of_birth" value="${param.date_of_birth}" required>

        <input type="submit" value="Update Profile">
    </form>
</div>
</body>
</html>
