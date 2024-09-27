<%@ page import="uz.doublem.delevery_for_exam.entity.Users" %>
<%@ page import="uz.doublem.delevery_for_exam.repository.UserRepository" %><%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 10.08.2024
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <style>
        .profile-container {
            max-width: 600px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            font-family: Arial, sans-serif;
            margin-top: 50px;
        }

        .profile-container h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        .profile-info {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        .profile-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #f4f4f4;
            padding: 10px;
            border-radius: 5px;
        }

        .profile-item label {
            font-weight: bold;
            color: #555;
            flex-basis: 30%;
        }

        .profile-item p {
            margin: 0;
            color: #333;
            flex-basis: 70%;
        }

        .edit-button-container {
            text-align: center;
            margin-top: 20px;
        }

        .edit-button {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            border: none;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        .edit-button:hover {
            background-color: #0056b3;
        }
    </style>

</head>
<body>
<div class="profile-container">
    <h1>Your Profile</h1>
    <div class="profile-info">
        <div class="profile-item">
            <label>Email:</label>
            <p><c:out value="${user.getEmail()}" /></p>
        </div>
        <div class="profile-item">
            <label>Phone Number:</label>
            <p><c:out value="${user.getPhone_number()}" /></p>
        </div>
        <div class="profile-item">
            <label>Password:</label>
            <p><c:out value="${user.getPassword()}" /></p>
        </div>
        <div class="profile-item">
            <label>First Name:</label>
            <p><c:out value="${user.getFirstName()}" /></p>
        </div>
        <div class="profile-item">
            <label>Last Name:</label>
            <p><c:out value="${user.getLastName()}" /></p>
        </div>
        <div class="profile-item">
            <label>Date of Birth:</label>
            <p><c:out value="${user.getDateOfBirth()}" /></p>
        </div>
        <div class="profile-item">
            <label>Account Created:</label>
            <p><c:out value="${user.getCreatedAt()}" /></p>
        </div>
    </div>
    <div class="edit-button-container">
        <form action="/views/editProfile.jsp" method="post">
            <input type="hidden" name="id" value="${user.id}">
            <input type="hidden" name="name" value="${user.getFirstName()}" />
            <input type="hidden" name="email" value="${user.getEmail()}" />
            <input type="hidden" name="phone_number" value="${user.getPhone_number()}" />
            <input type="hidden" name="password" value="${user.getPassword()}" />
            <input type="hidden" name="last_name" value="${user.getLastName()}" />
            <input type="hidden" name="date_of_birth" value="${user.getDateOfBirth()}" />
            <input type="submit" value="Edit Profile" class="edit-button" />
        </form>
    </div>
</div>

</body>
</html>
