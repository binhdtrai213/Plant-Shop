<%-- 
    Document   : header_loginedUser
    Created on : Oct 13, 2022, 11:46:51 PM
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
        <nav class="header">
            <a href="index.jsp"><img src="images/logo.png" id="logo"></a>
            <a href="index.jsp">Home</a>
            <a href="editAccount.jsp">Change profile</a>
            <a href="MainController?action=logout" style="color: #EB1D36;">Logout</a>
<!--            <a href="">Completed orders</a>
            <a href="">Canceled orders</a>
            <a href="">Processing orders</a>-->
<!--            <li>from<input type="date" name="from"> to <input type="date" class="to">
                <input type="submit" value="search">
            </li>-->
        </nav>
    </body>
</html>
