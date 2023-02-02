<%-- 
    Document   : header
    Created on : Oct 13, 2022, 11:05:29 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <header>
            <nav class="header">
                <a href="index.jsp"><img src="images/logo.png" id="logo"></a>
                <a href="index.jsp">Home</a>
                <a href="registration.jsp">Register</a>
                <a href="login.jsp">Login</a>
                <a href="MainController?action=viewcart">View Cart</a>
                <form action="MainController" method="post" class="formsearch">
                    <input type="text" name="txtsearch" value="<%= request.getParameter("txtsearch") != null ? request.getParameter("txtsearch") : "" %>">
                    <select name="searchby">
                        <option value="byname">By name</option>
                        <option value="bycate">By category</option>
                    </select>
                    <input type="submit" value="Search" name="action" >
                </form>
            </nav>
        </header>
    </body>
</html>
