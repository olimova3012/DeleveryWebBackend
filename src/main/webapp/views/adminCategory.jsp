<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Category Management</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .navbar {
            background-color: #333;
            overflow: hidden;
            padding: 1rem;
        }
        .navbar a {
            float: left;
            display: block;
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
            float: right;
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
            max-width: 800px;
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
        }
        .modal {
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
        .modal-content {
            background-color: #fff;
            margin: auto;
            padding: 20px;
            border: 1px solid #888;
            width: 50%;
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
        <%--        <p><c:out value="${user}"/></p>--%>
    </div>
</div>
<div class="content">
<div class="container">
    <h1>Category Management</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${allCategory}" var="ct">
            <tr>
                <td><c:out value="${ct.getId()}"/></td>
                <td><c:out value="${ct.getName()}"/></td>
                <td>
                    <button class="btn edit" onclick="editCategory(${ct.getId()}, '${ct.getName()}')">Edit</button>
                    <button class="btn delete" onclick="deleteCategory(${ct.getId()})">Delete</button>
                </td>
            </tr>
        </c:forEach>
    </table>
    <button class="btn" onclick="showAddCategoryModal()">Add Category</button>
</div>
</div>

<!-- Add/Edit Category Modal -->
<div id="categoryModal" class="modal">
    <div class="modal-content">
        <h2 id="modalTitle">Add Category</h2>
        <div class="form-group">
            <label for="categoryName">Category Name</label>
            <input type="text" id="categoryName" required>
            <input type="hidden" id="categoryId">
        </div>
        <button class="btn" onclick="saveCategory()">Save</button>
        <button class="btn delete" onclick="closeModal()">Cancel</button>
    </div>
</div>

<script>
    function showAddCategoryModal() {
        document.getElementById('modalTitle').innerText = 'Add Category';
        document.getElementById('categoryName').value = '';
        document.getElementById('categoryModal').style.display = 'block';
    }

    function editCategory(id, name) {
        document.getElementById('modalTitle').innerText = 'Edit Category';
        document.getElementById('categoryName').value = name;
        document.getElementById('categoryId').value = id;
        document.getElementById('categoryModal').style.display = 'block';
    }

    function saveCategory() {
        const categoryId = document.getElementById('categoryId').value; // Hidden field for category ID
        const categoryName = document.getElementById('categoryName').value;

        if (categoryName.trim() === '') {
            alert('Category name cannot be empty!');
            return;
        }

        const xhr = new XMLHttpRequest();
        xhr.open('POST', '/admin/saveCategory', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                alert('Category saved successfully!');
                location.reload(); // Reload the page to update the category list
            }
        };

        xhr.send('id=' + encodeURIComponent(categoryId) + '&name=' + encodeURIComponent(categoryName));
        closeModal();
    }

    function closeModal() {
        document.getElementById('categoryModal').style.display = 'none';
    }
    function deleteCategory(categoryId) {
        if (confirm("Are you sure you want to delete this category?")) {
            const xhr = new XMLHttpRequest();
            xhr.open('POST', '/admin/deleteCategory', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    alert('Category deleted successfully!');
                    location.reload(); // Reload the page to update the category list
                }
            };

            xhr.send('id=' + encodeURIComponent(categoryId));
        }
    }
</script>
</body>
</html>
