<%-- 
    Document   : header_loginedAdmin.jsp
    Created on : Oct 30, 2022, 4:55:18 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plant Shop</title>
        <link rel="stylesheet" href="style.css?version=51" type="text/css" />
    </head>
    <body>
        <header class="header">
            <a href="index.jsp"><img src="images/logo.png" id="logo"></a>
            <a href="MainController?action=manageAcc">Manage Accounts</a>
            <a href="MainController?action=manageOrder">Manage Orders</a>
            <a href="MainController?action=managePlant">Manage Plants</a>
            <a href="MainController?action=manageCategory">Manage Categories</a>
            <div>Welcome ${sessionScope.name} | <a href="MainController?action=logout" style="color: #EB1D36;">Logout</a></div>
        </header>
    </body>
</html>
