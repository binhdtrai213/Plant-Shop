<%-- 
    Document   : ManageCategories
    Created on : Oct 30, 2022, 10:47:34 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plant Shop</title>
        <link href="style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <c:if test="${role != 'admin'}">
            <c:redirect url="index.jsp"></c:redirect>
        </c:if>
        <c:import url="header_loginedAdmin.jsp" />
        <section>
            <form action="MainController" method="post" class="search-form">
                <input type="text" name="txtSearch" value="${search}"/>
                <input type="submit" name="action" value="searchCategory"/>
            </form>
            <h1></h1>
            <table class="order">
                <tr class="title">
                    <td>Category ID</td>
                    <td>Category Name</td>
                </tr>
                <c:forEach var="cate" items="${listCategory}">
                    <c:if test="${search == null || cate.name.toLowerCase().contains(search.toLowerCase())}">
                        <tr>
                            <td><c:out value="${cate.id}"></c:out></td>
                            <td><c:out value="${cate.name}"></c:out></td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </section>
        <footer>
            <c:import url="footer.jsp" />
        </footer> 
    </body>
</html>
