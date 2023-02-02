<%-- 
    Document   : login
    Created on : Oct 13, 2022, 11:21:44 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plant Shop</title>
    </head>
    <body>
        <header>
            <c:import url="header.jsp"/>
        </header>
        <section>
            <c:if test="${role != null}">
                <c:if test="${role == 'user'}">
                    <c:redirect url="personalPage.jsp"></c:redirect>
                </c:if>
                <c:if test="${role != 'user'}">
                    <c:redirect url="AdminPage.jsp"></c:redirect>
                </c:if>
            </c:if>
            <form action="MainController" method="post" class="form-login">
                <h1>Login</h1>
                <h3>Email</h3>
                <input type="text" name="txtEmail" value="${email}" reqired>
                <h3>Password</h3>
                <input type="password" name="txtPassword" reqired>
                <br/>
                <c:if test="${WARNING != null}">
                    <font style='color: red;'>${WARNING}</font>
                    <br/>
                </c:if>
                
                <input type="submit" value="login" name="action">
                <input type="checkbox" value="savelogin" name="savelogin" style="width: 15px;">
                <span>Stayed signed in</span>
            </form>
        </section>
        <footer>
            <c:import url="footer.jsp"/>
        </footer>
    </body>
</html>
