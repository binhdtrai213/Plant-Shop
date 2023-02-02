<%-- 
    Document   : AdminPage
    Created on : Oct 30, 2022, 4:50:25 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plant Shop</title>
        <link rel="stylesheet" href="style.css?version=51" type="text/css" />
    </head>
    <body>
        <c:if test="${role != 'admin'}">
            <c:redirect url="index.jsp"></c:redirect>
        </c:if>
        <c:import url="header_loginedAdmin.jsp"></c:import>
        <section class="admin-page">
            
        </section>
        <footer>
            <c:import url="footer.jsp" />
        </footer>
    </body>
</html>
