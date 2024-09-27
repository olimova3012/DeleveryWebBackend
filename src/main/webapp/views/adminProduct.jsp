<%@ page import="uz.doublem.delevery_for_exam.repository.CategoryRepository" %>
<%@ page import="uz.doublem.delevery_for_exam.entity.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.doublem.delevery_for_exam.service.ProductService" %>
<%@ page import="uz.doublem.delevery_for_exam.entity.Product" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<html>
<head>
    <title>Product</title>
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
    <div class="links">
    <a href="/admin/adminMainController?value=category" >Category</a>
    <a href="/admin/adminMainController?value=product">Product</a>
    <a href="/admin/adminMainController?value=users">Show Users</a>
    <a href="/admin/adminMainController?value=orders">Show Orders</a>
    </div>
    <div class="profile">
        <img src="/views/attachments/userImage.png" alt="Profile" onclick="location.href='profile.jsp'"/>
    </div>
</div>
<div class="content">

<div class="container">
    <h1>Product Management</h1>
    <table>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>description</th>
            <th>price</th>
            <th>category</th>
            <th>created At</th>
            <th>isActive</th>
            <th>doBlock</th>
            <th>Action</th>
            <th>Image</th>
            <th>change Image</th>
        </tr>
        <c:forEach items="${product}" var="pr">
            <tr>
                <td><c:out value="${pr.getId()}"/></td>
                <td><c:out value="${pr.getName()}"/></td>
                <td><c:out value="${pr.getDescription()}"/></td>
                <td><c:out value="${pr.getPrice()}"/></td>
                <td><c:out value="${pr.getCategory().getName()}"/></td>
                <td><c:out value="${pr.getCreatedAt()}"/></td>
                <td><c:out value="${pr.isActive()}"/></td>
                <td>
                    <button
                            class="<c:if test="${pr.isActive()}">button-confirmed</c:if><c:if test="${!pr.isActive()}">button-unconfirmed</c:if>"
                            onclick="toggleConfirmed('${pr.getId()}', '${pr.isActive()}')">
                        <c:if test="${pr.isActive()}">Confirmed</c:if>
                        <c:if test="${!pr.isActive()}">Unconfirmed</c:if>
                    </button>
                </td>
                <td>
                    <button class="btn edit" onclick="editProduct(${pr.getId()}, '${pr.getName()}','${pr.getDescription()}',${pr.getPrice()},${pr.getCategory().getId()},'${pr.getCategory().getName()}','product')">Edit</button>
                    <button class="btn delete" onclick="deleteProduct(${pr.getId()})">Delete</button>
                </td>
                <td class="img-row">
                    <div class="card-image">
                        <div class="gallery">
                            <c:choose>
                                <c:when test="${pr.getProductImages().getId() eq null}">
                                    <img class=" myImg thumbnail" src="/views/attachments/defaultImage.jpg" alt="imageProduct">
                                </c:when>
                                <c:otherwise>
                                    <img class=" myImg thumbnail" src="/download?id=${pr.getProductImages().getId()}" alt="${pr.getName()}">
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="edit-model-content-image">
                        <button class="btn" onclick="showChangeImageModel(${pr.getId()})">Change Image</button>
                    </div>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div style="display: none" id="save-model-content-image" class="modal2">
        <div class="modal-content2">
            <span class="close2" onclick="hideChangeImageModel()">&times;</span>
            <form id="change-image-form" enctype="multipart/form-data" action="/upload" method="post" onsubmit="submitChangeImageForm(Event)">
                <div>
                    <input id="changeImagePrId" type="hidden" name="productId" required>
                    <label for="file">Insert Image:</label>
                    <input type="file" id="file" name="file" required>
                </div>
                <button type="submit">Import img</button>
            </form>
        </div>
    </div>
    <button class="btn" onclick="showAddCProductModal('product')">Add Product</button>
</div>
<div class="container">
        <h1>Combo</h1>
        <table>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>description</th>
                <th>price</th>
                <th>category</th>
                <th>created At</th>
                <th>isActive</th>
                <th>doBlock</th>
                <th>Action</th>
                <th>Image</th>
                <th>change Image</th>
            </tr>
            <c:forEach items="${combos}" var="cm">
                <tr>
                    <td><c:out value="${cm.getId()}"/></td>
                    <td><c:out value="${cm.getName()}"/></td>
                    <td><c:out value="${cm.getDescription()}"/></td>
                    <td><c:out value="${cm.getPrice()}"/></td>
                    <td><c:out value="${cm.getCategory().getName()}"/></td>
                    <td><c:out value="${cm.getCreatedAt()}"/></td>
                    <td><c:out value="${cm.isActive()}"/></td>
                    <td>
                        <button
                                class="<c:if test="${cm.isActive()}">button-confirmed</c:if><c:if test="${!cm.isActive()}">button-unconfirmed</c:if>"
                                onclick="toggleConfirmed('${cm.getId()}', '${cm.isActive()}')">
                            <c:if test="${cm.isActive()}">Confirmed</c:if>
                            <c:if test="${!cm.isActive()}">Unconfirmed</c:if>
                        </button>
                    </td>

                    <td>
                        <button class="btn edit" onclick="editProduct(${cm.getId()}, '${cm.getName()}','${cm.getDescription()}',${cm.getPrice()},${cm.getCategory().getId()},'${cm.getCategory().getName()}','combo')">Edit</button>
                        <button class="btn delete" onclick="deleteProduct(${cm.getId()})">Delete</button>
                    </td>
                    <td class="img-row">
                        <div class="card-image">
                            <div class="gallery">
                                <c:choose>
                                    <c:when test="${cm.getProductImages().getId() eq null}">
                                        <img class=" myImg thumbnail" src="/views/attachments/defaultImage.jpg" alt="imageProduct">
                                    </c:when>
                                    <c:otherwise>
                                        <img class=" myImg thumbnail" src="/download?id=${cm.getProductImages().getId()}" alt="${cm.getName()}">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="edit-model-content-image">
                            <button class="btn" onclick="showChangeImageModel(${cm.getId()})">Change Image</button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    <button class="btn" onclick="showAddCProductModal('combo')">Add Combo</button>
</div>
    <div id="myModal" class="modal">
        <span class="close">&times;</span>
        <img class="modal-content" id="img01" alt="kaku" src="">
    </div>
    <div id="categoryModal" class="modal">
        <div class="modal-content">
            <h2 id="modalTitle">Add Product</h2>
            <div class="form-group">
                <label for="productName">Product Name</label>
                <input type="text" id="productName" required>
                <label for="productDescription">Product Description</label>
                <input type="text" id="productDescription" required>
                <label for="productPrice">Product Price</label>
                <input type="text" id="productPrice" required>
                <%
                    CategoryRepository categoryRepository = CategoryRepository.getInstance();
                    List<Category> all = categoryRepository.getAll();
                    ProductService productService = ProductService.getInstance();
                    HashMap<Category, List<Product>> productsByCategory = productService.getProductsByCategory();
                %>
                <div class="form-group">
                    <input type="hidden" id="typeForPC">
                    <label for="productCategory">Product Category</label>
                    <div class="select-wrapper">
                        <select id="productCategory" required>
                            <option class="productCategory" value="" selected disabled></option>
                            <% for (Category category : all) { %>
                            <option value="<%= category.getId() %>"><%= category.getName() + " >> id:" + category.getId() %></option>
                            <% } %>
                        </select>
                    </div>
                </div>
                <button id="comboOptionProduct" style="display: none" type="button" onclick="showComboProducts()" >Add Product in Combo</button>
                <div id="productComboDiv" style="display: none;" class="checkboxes product_for_combo">
                    <% for (Map.Entry<Category, List<Product>> entry : productsByCategory.entrySet()) { %>
                    <h3><%= entry.getKey().getName() %></h3>
                    <ul class="list-group">
                        <% for (Product product : entry.getValue()) { %>
                        <li class="list-group-item" id="ulComboProduct">
                            <input class="form-check-input me-1" type="checkbox" value="<%= product.getId() %>" id="productCheckbox<%= product.getId() %>">
                            <label class="form-check-label" for="productCheckbox<%= product.getId() %>"><%= product.getName() %></label>
                        </li>
                        <% } %>
                    </ul>
                    <% } %>
                    <button type="button" onclick="document.getElementById('productComboDiv').style.display= 'none' ">OK</button>
                </div>
                <input type="hidden" id="chekboxesProductId">
                <input type="hidden" id="productId">
            </div>
            <button class="btn" onclick="saveCategory()">Save</button>
            <button class="btn delete" onclick="closeModal()">Cancel</button>
        </div>
    </div>
</div>




<script>

    function showChangeImageModel(id) {
        var modal = document.getElementById('save-model-content-image');
        modal.style.display = 'block';
        document.getElementById('changeImagePrId').value = id;


        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = 'none';
            }
        }
    }


    function hideChangeImageModel() {
        document.getElementById('save-model-content-image').style.display = 'none';
    }


    function submitChangeImageForm(event) {
        event.preventDefault();
        var form = document.getElementById('change-image-form');

        var formData = new FormData(form);

        var xhr = new XMLHttpRequest();
        xhr.open("POST", form.action, true);

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                hideChangeImageModel();
                location.reload();
            }
        };

        xhr.send(formData);
    }

    function showAddCProductModal(typeProduct) {
        document.getElementById('modalTitle').innerText = 'Add Product';
        document.getElementById('productName').value = '';
        document.getElementById('productDescription').value = '';
        document.getElementById('productPrice').value = '';
        document.getElementById('productCategory').value = '';
        document.getElementById('productId').value = '';
        document.getElementsByClassName('productCategory').innerText = 'choose category: ';
        document.getElementById('typeForPC').value =typeProduct;
        if (typeProduct === "combo") {
            // showComboProducts();
            document.getElementById('comboOptionProduct').style.display = 'block';
        } else {
            document.getElementById('comboOptionProduct').style.display = 'none';
            document.getElementById('productComboDiv').style.display = 'none'; // combo bo'lmasa yashirish
        }

        document.getElementById('categoryModal').style.display = 'block';
    }

    function editProduct(id, name, description, price, categoryId, categoryName, typeOfProduct) {

        document.getElementById('modalTitle').innerText = 'Edit Product';
        document.getElementById('productName').value = name;
        document.getElementById('productDescription').value = description;
        document.getElementById('productPrice').value = price;
        document.getElementById('productCategory').value = categoryId;
        document.getElementsByClassName('productCategory').innerText = categoryName;
        document.getElementById('productId').value = id;
        document.getElementById('typeForPC').value = '';


        if(typeOfProduct === 'combo'){
            console.log("type:",typeOfProduct);
            let selectedProducts = '2,5';
        // Agar selectedProducts string shaklida kelsa, uni arrayga aylantirish
        if (typeof selectedProducts === 'string') {
            selectedProducts = selectedProducts.split(',').map(Number);
        }

        // Combo mahsulotlar uchun checkboxlarni to'ldirish
        if (selectedProducts && selectedProducts.length > 0) {
            document.getElementById('typeForPC').style.display = 'block'; // checkboxlarni ko'rsatish

            // Barcha checkboxlarni tekshirish
            document.querySelectorAll('.product_for_combo input[type="checkbox"]').forEach(checkbox => {
                checkbox.checked = false;  // oldindan belgilarni tozalash
            });

            // Tanlangan mahsulotlarni belgili qilish
            selectedProducts.forEach(productId => {
                const checkbox = document.getElementById('productCheckbox' + productId);
                if (checkbox) {
                    checkbox.checked = true;
                }
            });
            document.getElementById('typeForPC').style.display = 'block'
            document.getElementById('productComboDiv').style.display = 'block';
        }
        } else {
            document.getElementById('typeForPC').style.display = 'none'
            document.getElementById('productComboDiv').style.display = 'none'; // combo bo'lmasa yashirish
        }

        document.getElementById('categoryModal').style.display = 'block';
    }

    function saveCategory() {
        const productId = document.getElementById('productId').value; // Hidden field for product ID
        const productName = document.getElementById('productName').value;
        const productDescription = document.getElementById('productDescription').value;
        const productPrice = document.getElementById('productPrice').value;
        const productCategory = document.getElementById('productCategory').value;
        const productType = document.getElementById('typeForPC').value;

        if (productName.trim() === '') {
            alert('Product name cannot be empty!');
            return;
        }

        document.querySelectorAll('.product_for_combo input[type="checkbox"]').forEach(checkbox => {
            checkbox.checked = false;  // oldindan belgilarni tozalash
        });
        // Tanlangan mahsulotlarni yig'ish
        const selectedProducts = [];
        const checkboxes = document.querySelectorAll('.product_for_combo input[type="checkbox"]:checked');
        checkboxes.forEach((checkbox) => {
            selectedProducts.push(checkbox.value);
        });

        const xhr = new XMLHttpRequest();
        if (productType === 'combo') {
            const isCombo = selectedProducts.length > 0;
            if(isCombo) {
                xhr.open('POST', '/admin/saveCombo', true);
            }else {
                alert('Combos Product cannot be empty!');
                return;
            }
        } else {
            xhr.open('POST', '/admin/saveProduct', true); // Oddiy mahsulot uchun
        }
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                alert('Product saved successfully!');
                location.reload(); // Reload the page to update the product list
            }
        };

        xhr.send('id=' + encodeURIComponent(productId) +
            '&name=' + encodeURIComponent(productName) +
            '&description=' + encodeURIComponent(productDescription) +
            '&price=' + encodeURIComponent(productPrice) +
            '&category=' + encodeURIComponent(productCategory) +
            '&selectedProducts=' + encodeURIComponent(selectedProducts.join(','))
        );

        closeModal();
    }

    function closeModal() {
        document.querySelectorAll('.product_for_combo input[type="checkbox"]').forEach(checkbox => {
            checkbox.checked = false;  // oldindan belgilarni tozalash
        });
        document.getElementById('typeForPC').style.display = 'none'
        document.getElementById('productComboDiv').style.display = 'none';
        document.getElementById('categoryModal').style.display = 'none';
    }

    function deleteProduct(productId) {
        if (confirm("Are you sure you want to delete this product?")) {
            const xhr = new XMLHttpRequest();
            xhr.open('POST', '/admin/deleteProduct', true);
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    alert('Product deleted successfully!');
                    location.reload(); // Reload the page to update the category list
                }
            };

            xhr.send('id=' + encodeURIComponent(productId));
        }
    }
    function toggleConfirmed(productId, currentStatus) {
        const newStatus = currentStatus === 'true' ? 'false' : 'true';
        $.ajax({
            type: 'POST',
            url: '/ToggleConfirmedServlet',
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

    $(document).ready(function() {
        $('#productCategory').select2({
            placeholder: "Choose a category",
            allowClear: true
        });
    });


</script>
<script>
    // Modalga tegishli elementlarni olish
    var modal = document.getElementById("myModal");
    var modalImg = document.getElementById("img01");

    // Har bir kichik rasmga (thumbnail) 'click' hodisasini qo'shish
    var thumbnails = document.getElementsByClassName("myImg");
    for (let i = 0; i < thumbnails.length; i++) {
        thumbnails[i].onclick = function () {
            modal.style.display = "block";
            modalImg.src = this.src; // Bosilgan rasmning URL manzilini modalga joylashtirish
        }
    }

    // Modalni yopish uchun 'close' elementiga bosilganda
    var span = document.getElementsByClassName("close")[0];
    span.onclick = function () {
        modal.style.display = "none";
    }
    function showComboProducts() {
        document.getElementById('productComboDiv').style.display = 'block';
        document.getElementById('ulComboProduct').style.display = 'block';
    }

</script>
</body>
</html>
