<%-- 
    Document   : editAccount
    Created on : Oct 25, 2022, 2:11:49 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="basicclass.Account"%>
<%@page import="dao.AccountDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plant Shop</title>
    </head>
    <body>
        <header>
            <c:import url="header.jsp" />
        </header>
        <section>
            <c:if test="${name == null || phone == null}">
                <c:redirect url="index.jsp"></c:redirect>
            </c:if>
            <form action="MainController" method="post" class="form-login">
                <h3>Full name:</h3>
                <input type="text" name="name" value="${name}" />
                <h3>Phone:</h3>
                <input type="text" name="phone" value="${phone}" />
                <input type="submit" name="action" value="edit" />
            </form>
        </section>
        <footer>
            <c:import url="footer.jsp" />
        </footer>
    </body>
</html>
