<%-- 
    Document   : ManagePlants
    Created on : Oct 30, 2022, 10:47:13 PM
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
                <input type="submit" name="action" value="searchPlant"/>
            </form>
            <h1></h1>
            <table class="order">
                <tr class="title">
                    <td>ID</td>
                    <td>Name</td>
                    <td>Price</td>
                    <td>Image</td>
                    <td>Description</td>
                    <td>Status</td>
                    <td>Category</td>
                    <td>Action</td>
                </tr>
                <c:forEach var="plant" items="${listPlant}">
                    <c:if test="${search == null || plant.name.toLowerCase().contains(search.toLowerCase())}">
                        <tr>
                            <td><c:out value="${plant.id}"></c:out></td>
                            <td><c:out value="${plant.name}"></c:out></td>
                            <td><c:out value="${plant.price}"></c:out></td>
                            <td><img src="${plant.imgpath}" alt="plant-photo" class="plantimg"></td>
                            <td style="width: 40%;"><c:out value="${plant.description}"></c:out></td>
                            <td>
                                <c:choose>
                                    <c:when test="${plant.status eq 1}">Active</c:when>
                                    <c:otherwise>Inactive</c:otherwise>
                                </c:choose>
                            </td>
                            <td><c:out value="${plant.catename}"></c:out></td>
                            <td>
                                <c:url var="mylink" value="MainController">
                                    <c:param name="id" value="${plant.id}"></c:param>
                                    <c:param name="status" value="${plant.status}"></c:param>
                                    <c:param name="action" value="updateStatusPlant"></c:param>
                                </c:url>
                                <a href="${mylink}">Block/Unblock</a>
                            </td>
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
