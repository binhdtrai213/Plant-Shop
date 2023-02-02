<%-- 
    Document   : registration
    Created on : Oct 13, 2022, 11:26:36 PM
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
            <c:import url="header.jsp"></c:import>
        </header>
        <section>
            <form action="MainController" method="post" class="form-register">
                <h1>Register</h1>
                <h3>Email</h3> 
                <input type="email" name="txtemail" required="" value="${txtemail}" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$">
                <h3>Full name</h3> 
                <input type="text" name="txtfullname" required="" value="${txtname}"></td></tr>
                <h3>Password</h3> 
                <input type="password" name="txtpassword" required="">
                <h3>Phone</h3> 
                <input type="text" name="txtphone" value="${txtphone}" required="">
                <br/>
                <c:if test="${error != null && !error.equals('')}">
                    <span style="color: red;">${error}</span>
                    <br/>
                </c:if>
                <input type="submit" value="register" name="action">
            </form>
        </section>
        <footer>
            <c:import url="footer.jsp"/>
        </footer>
    </body>
</html>
