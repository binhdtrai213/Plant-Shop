<%-- 
    Document   : OrderDetail
    Created on : Oct 18, 2022, 9:04:47 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dao.OrderDAO"%>
<%@page import="basicclass.OrderDetail"%>
<%@page import="java.util.ArrayList"%>
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
            <h3>Welcome ${name} comeback!</h3>
            <h3><a href="MainController?action=logout">Logout</a></h3>
            <a href="personalPage.jsp">View all orders</a>
            <c:if test="${list != null && !list.isEmpty()}">
                <c:set var="total" value="${0}"/>
                <c:forEach var="detail" items="${list}">
                    <table class="order">
                        <tr class="title">
                            <td>Order ID</td>
                            <td>Plant ID</td>
                            <td>Plant Name</td>
                            <td>Image</td>
                            <td>Price</td>
                            <td>Quantity</td>
                        </tr>
                        <tr>
                            <td>${detail.orderID}</td>
                            <td>${detail.plantID}</td>
                            <td>${detail.plantName}</td>
                            <td><img src="${detail.imgPath}" class='plantimg' /></td>
                            <td>${detail.price}</td>
                            <td>${detail.quantity}</td>
                            <c:set var="total" value="${total + detail.price * detail.quantity}"/>
                        </tr>
                    </table>
                </c:forEach>
                <h3>Total money: ${total}</h3>                
            </c:if>
            <c:if test="${list == null || list.isEmpty()}">
                <p>You don't have any order.</p>
            </c:if>
        </section>
        <footer>
            <c:import url="footer.jsp" />
        </footer>
    </body>
</html>
