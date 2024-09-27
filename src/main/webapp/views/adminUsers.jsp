<%@ page import="uz.doublem.delevery_for_exam.entity.Users" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<html>

<head>
    <title>Users</title>
    <style>
        /* Existing Styles */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .navbar {
            display: flex;
            justify-content: space-between; /* Ensures space between links and profile */
            align-items: center; /* Align items vertically in the center */
            background-color: #333;
            padding: 1rem;
        }
        .navbar .links {
            display: flex;
            gap: 1rem; /* Adds space between the links */
        }

        .navbar a {
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .navbar a:hover {
            background-color: #ddd;
            color: black;
        }

        .profile {
            margin-right: 15px;
        }

        .profile img {
            border-radius: 50%;
            width: 40px;
            height: 40px;
        }

        .content {
            padding: 20px;
        }
        h1 {
            color: #333;
        }
        .profile:hover {
            cursor: pointer;
        }
        .container {
            max-width: 1300px;
            margin: auto;
            background: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }
        .img-row{
            padding: 0;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            background-color: #5cb85c;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none;
        }
        .btn.edit {
            background-color: #0275d8;
        }
        .btn.delete {
            background-color: #d9534f;
        }
        .btn:hover {
            opacity: 0.8;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .form-group input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .form-group input:focus {
            border-color: #0275d8;
            outline: none;
        }
        .modal,.modal2 {
            display: none;
            position: fixed;
            z-index: 1;
            padding-top: 60px;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.5);
        }
        .modal-content,.modal-content2 {
            background-color: #fff;
            margin: auto;
            padding: 20px;
            border: 1px solid #888;
            border-radius: 8px;
            width: 50%;
            box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.3);
        }
        .button-confirmed {
            background-color: #28a745;
            color: white;
            padding: 5px 10px;
            border-radius: 5px;
            border: none;
        }
        .button-unconfirmed {
            background-color: #fa2020;
            color: white;
            padding: 5px 10px;
            border-radius: 5px;
            border: none;
        }
        button {
            cursor: pointer;
            margin-bottom: 10px;
        }
        /* Dropdown Styling */
        .select-wrapper {
            position: relative;
            display: inline-block;
            width: 100%;
        }

        .select-wrapper select {
            width: 100%;
            padding: 10px;
            border-radius: 4px;
            background-color: #f2f2f2;
            border: 1px solid #ddd;
            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
            cursor: pointer;
        }

        .select-wrapper::after {
            content: '\25BC'; /* Down arrow */
            position: absolute;
            top: 50%;
            right: 10px;
            transform: translateY(-50%);
            pointer-events: none;
            font-size: 14px;
            color: #333;
        }

        .select-wrapper select:focus {
            border-color: #0275d8;
            outline: none;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        }

        .select-wrapper select:hover {
            background-color: #e9ecef;
        }
        .card-image {
            flex: 1;
            display: flex;
            align-items: center;
            justify-content: center;
            overflow: hidden;
            /*padding: 10px;*/
        }
        .card-image img {
            max-width: 100%;
            max-height: 400px;
            object-fit: cover;
        }
        .thumbnail {
            width: 150px;
            height: auto;
            cursor: pointer;
        }

        .modal,.modal2 {
            display: none;
            position: fixed;
            z-index: 1;
            padding-top: 100px;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.8);
        }

        /* Modal kontenti (rasm) */
        .modal-content,.modal-content2 {
            margin: auto;
            display: block;
            width: 80%;
            max-width: 700px;
        }

        /* Close tugmasi */
        .close,.close2 {
            position: absolute;
            top: 15px;
            right: 35px;
            color: #fff;
            font-size: 40px;
            font-weight: bold;
            transition: 0.3s;
            cursor: pointer;
        }

        .close2:hover,
        .close2:focus {
            color: #bbb;
            text-decoration: none;
            cursor: pointer;
        }
        .close:hover,
        .close:focus {
            color: #bbb;
            text-decoration: none;
            cursor: pointer;
        }
        td img {
            max-width: 200px; /* Tasvir kengligini tartibga soladi */
            max-height: 200px; /* Tasvir balandligini tartibga soladi */
            display: block; /* Rasmlarni blok darajasida ko'rsatadi */
            margin: auto; /* Rasmlarni hujayra markaziga joylashtiradi */
            object-fit: cover; /* Tasvir hajmini tartibga soladi va kesib tashlashga imkon beradi */
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            table-layout: fixed; /* Jadval hujayralarining kengligini to'g'ri tartibga soladi */
        }
    </style>
</head>
<body>

<div class="navbar">
    <a href="/admin/adminMainController?value=category" >Category</a>
    <a href="/admin/adminMainController?value=product">Product</a>
    <a href="/admin/adminMainController?value=users">Show Users</a>
    <a href="/admin/adminMainController?value=orders">Show Orders</a>
    <div class="profile">
        <img src="/views/attachments/userImage.png" alt="Profile" onclick="location.href='profile.jsp'"/>
    </div>
</div>

<div class="content">

    <div class="container">
        <h1>Users</h1>
        <table>
            <tr>
                <th>id</th>
                <th>email</th>
                <th>phone-number</th>
                <th>first name</th>
                <th>last name</th>
                <th>password</th>
                <th>date of birth</th>
                <th>created At</th>
                <th>isActive</th>
                <th>role</th>
                <th>is deleted</th>
                <th>doBlock</th>
            </tr>
            <c:forEach items="${users}" var="us">
                <tr>
                    <td><c:out value="${us.getId()}"/></td>
                    <td><c:out value="${us.getEmail()}"/></td>
                    <td><c:out value="${us.getPhone_number()}"/></td>
                    <td><c:out value="${us.getFirstName()}"/></td>
                    <td><c:out value="${us.getLastName()}"/></td>
                    <td><c:out value="${us.getPassword()}"/></td>
                    <td><c:out value="${us.getDateOfBirth()}"/></td>
                    <td><c:out value="${us.getCreatedAt()}"/></td>
                    <td><c:out value="${us.isActive()}"/></td>
                    <td><c:out value="${us.getRole()}"/></td>
                    <td><c:out value="${us.isDeleted()}"/></td>
                    <td>
                        <button
                                class="<c:if test="${us.isActive()}">button-confirmed</c:if><c:if test="${!us.isActive()}">button-unconfirmed</c:if>"
                                onclick="toggleConfirmed('${us.getId()}', '${us.isActive()}')">
                            <c:if test="${us.isActive()}">Confirmed</c:if>
                            <c:if test="${!us.isActive()}">Unconfirmed</c:if>
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<script>
    function toggleConfirmed(productId, currentStatus) {
        const newStatus = currentStatus === 'true' ? 'false' : 'true';
        $.ajax({
            type: 'POST',
            url: '/UserToggleConfirmedServlet',
            data: {
                id: productId,
                newStatus: newStatus
            },
            success: function() {
                location.reload(); // Reload the page to reflect changes
            },
            error: function(xhr, status, error) {
                console.error('Error updating confirmation status:', xhr.status, xhr.statusText);
                alert('Failed to update confirmation status.');
            }
        });
    }
</script>
</body>
</html>
