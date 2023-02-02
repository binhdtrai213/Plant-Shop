<%-- 
    Document   : ManageOrders
    Created on : Oct 30, 2022, 10:46:50 PM
    Author     : Admin
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="basicclass.Order"%>
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
                <input type="submit" name="action" value="searchOrder"/>
            </form>
            <h1></h1>
            <table class="order">
                <tr class="title">
                    <td>Order ID</td>
                    <td>Order Date</td>
                    <td>Ship Date</td>
                    <td>Status</td>
                    <td>Account ID</td>
                    <td>Action</td>
                </tr>
                <c:forEach var="ord" items="${listOrder}">
                    ${filter}
                    <c:if test="${search == null || Integer.toString(ord.accID).toLowerCase().contains(search.toLowerCase())}">
                        <tr>
                            <td><c:out value="${ord.orderID}"></c:out></td>
                            <td><c:out value="${ord.orderDate}"></c:out></td>
                            <td><c:out value="${ord.shipDate}"></c:out></td>
                            <td>
                                <c:choose>
                                    <c:when test="${ord.status eq 1}">Processing</c:when>
                                    <c:when test="${ord.status eq 2}">Completed</c:when>
                                    <c:otherwise>Canceled</c:otherwise>
                                </c:choose>
                            </td>
                            <td><c:out value="${ord.accID}"></c:out></td>
                            <td>
                                <c:url var="mylink" value="MainController">
                                    <c:param name="orderid" value="${ord.orderID}"></c:param>
                                    <c:param name="status" value="${ord.status}"></c:param>
                                    <c:param name="action" value="updateStatusOrder"></c:param>
                                </c:url>
                                <a href="${mylink}">Next status</a>
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
