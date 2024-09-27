<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login and register Form</title>
<style>
    *{
        margin: 0;
        padding: 0;
        font-family: sans-serif;
    }

    .hero{
        height: 100%;
        width: 100%;
        background: url(/views/assets/back.jpg) center;
        background-size: cover;
        position: absolute;
    }
    .form-box{
        height: 480px;
        width: 380px;
        position: relative;
        margin: 6% auto;
        background-color: #fff;
        padding: 5px;
        border-radius: 10px;
        overflow: hidden;
    }
    .button-box{
        width: 220px;
        margin: 35px auto;
        position: relative;
        box-shadow: 0 0 20px 9px #ff61241f;
        border-radius: 30px;
    }
    .toggle-btn{
        padding: 10px 30px;
        cursor: pointer;
        background: transparent;
        border: 0;
        position: relative;
    }
    #btn{
        top: 0;
        left: 0;
        position: absolute;
        width: 110px;
        height: 100%;
        background: linear-gradient(to right, #ff105f,#ffad06);
        border-radius: 30px;
        transition: .5s;

    }
    .social-icon{
        margin: 3px auto;
        text-align: center;
    }
    .social-icon img{
        margin: 3px 5px;
        text-align: center;

    }
    .input-group{
        top: 180px;
        position: absolute;
        width: 280px;
        transition: .5s;
    }
    #email_exist_box {
        position: fixed;
        top: 20px;
        right: 20px;
        padding: 20px;
        background: red;
        color: white;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        z-index: 1000;
    }

    .close-btn {
        background: transparent;
        border: none;
        color: white;
        font-size: 16px;
        cursor: pointer;
        position: absolute;
        top: 5px;
        right: 10px;
        border-radius: 50%;
        width: 24px;
        height: 24px;
        display: flex;
        align-items: center;
        justify-content: center;
        transition: background 0.3s, color 0.3s;
    }

    .close-btn:hover {
        background: rgba(255, 255, 255, 0.2);
        color: #ff105f;
    }

    .email_exist p {
        margin: 0;
    }
    .input-field{
        width: 100%;
        padding: 10px 0;
        margin: 5px 20px;
        border-left: 0;
        border-right: 0;
        border-top: 0;
        border-bottom: 1px solid #999;
        outline: none;
        background: transparent;
    }
    .submit-btn{
        width: 85%;
        padding: 10px 30px;
        cursor: pointer;
        display: block;
        margin: auto;
        background: linear-gradient(to right, #ff105f,#ffad06);
        border: 0;
        outline: none;
        border-radius: 30px;
    }
    .check-box{
        margin: 30px 10px 30px 20px;

    }
    span{
        color: #777;
        font-size: 12px;
        bottom: 68px;
        position: absolute;
    }
    #login{
        left: 50px;

    }
    #register{
        left: 450px;
    }
</style>
</head>
<body>
<div class="hero">
    <div class="form-box">
        <div class="button-box">
            <div id="btn"></div>
            <button type="button" class="toggle-btn" onclick="login()">Log in</button>
            <button type="button" class="toggle-btn" onclick="register()">Register</button>

        </div>
        <div class="social-icon">
            <img  src="/views/assets/fac.png" width="38" height="38" alt="facebook">
            <img src="/views/assets/gog.png"  width="38" height="38" alt="google">
            <img src="/views/assets/x.png"  width="38" height="38" alt="x">

        </div>


        <form action="/auth/sign-in" id="login" class="input-group" method="post">
            <input type="text" class="input-field" name="email" placeholder="User Email" required>
            <input type="text" class="input-field" name="password" placeholder="Enter Password" required>
            <input type="checkbox" class="check-box"><span>Remember Password</span>
            <button type="submit" class="submit-btn">Log in</button>
        </form>

        <form action="/auth/sign-up" id="register" class="input-group" method="post">
            <input type="text" class="input-field" name="name" placeholder="User Name" required>
            <input type="text" class="input-field" name="email" placeholder="Email " required>
            <input type="text" class="input-field" name="password" placeholder="Enter Password" required>
            <input type="checkbox" class="check-box"><span>Send me updates</span>
            <button type="submit" class="submit-btn">Register</button>
        </form>

        <div class="email_exist" id="email_exist_box" style="display: none;position: fixed;
    top: 20px;
    right: 20px;
    padding: 20px;
    background: red;
    color: white;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
    z-index: 1000;">
            <p id="email_exist"><c:out value="${message}" /></p>
            <button onclick="closeAlert()" class="close-btn" style=" background: transparent;
    border: none;
    color: white;
    font-size: 16px;
    cursor: pointer;
    position: absolute;
    top: 5px;
    right: 10px;
    border-radius: 50%;
    width: 24px;
    height: 24px;
    display: flex;
    align-items: center;
    justify-content: center;
    transition: background 0.3s, color 0.3s;">x</button>
        </div>
    </div>

</div>

<script>
    var x = document.getElementById("login");
    var y = document.getElementById("register");
    var z = document.getElementById("btn");

    function register() {
        x.style.left = "-400px";
        y.style.left = "50px";
        z.style.left = "110px";
    }
    function login() {
        x.style.left = "50px";
        y.style.left = "450px";
        z.style.left = "0px";
    }


    function closeAlert() {
        document.getElementById("email_exist_box").style.display = "none";
    }

    // Show the alert box if exists is true
    <c:if test="${exists}">
    document.getElementById("email_exist_box").style.display = "block";
    document.getElementById("email_exist_box").style.background = "lawngreen";
    </c:if>
    document.getElementById("email_exist_box").style.display = "block";
</script>
</body>
</html>