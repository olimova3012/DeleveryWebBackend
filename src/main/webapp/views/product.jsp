<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 8/3/24
  Time: 4:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            flex-direction: column;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        form {
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }

        div {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }

        button {
            width: 100%;
            padding: 10px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #218838;
        }

        table {
            width: 80%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #ccc;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>
<div>add product</div>
<%--<h1><c:out value="${product}"> </c:out></h1>--%>


<table>
    <tr>
        <th>name</th>
        <th>description</th>
        <th>price</th>
        <th>delete</th>
    </tr>
    <c:forEach items="${products}" var="pr" >
        <tr>
            <th>${pr.getName()}</th>
            <th>${pr.getDescription()}</th>
            <th>${pr.getPrice()}</th>
            <th><form action="/delete"><input type="hidden" name="id" value="${pr.getId()}" > <button type="submit" > Delete <c:out value="${pr.getId()}"></c:out></button></form> </th>
        </tr>
    </c:forEach>
</table>
<form action="/product" method="post">
    <div>
        <input type="text" name="name" placeholder="enter name">
    </div>
    <div>
        <input type="text" name="description" placeholder="enter description">
    </div>
    <div>
        <input type="number" name="price" placeholder="enter price">
    </div>
    <div>
        <button type="submit">save product</button>
    </div>
</form>
</body>
</html>
