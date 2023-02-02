<%-- 
    Document   : ViewCart
    Created on : Oct 26, 2022, 10:12:47 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dao.PlantDAO"%>
<%@page import="basicclass.Plant"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
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
            <c:if test="${name != null}">
                <h3>Welcome <%= session.getAttribute("name") %> come back</h3>
                <h3><a href='MainController?action=logout'>Logout</a></h3>
                <h3><a href='personalPage.jsp'>Personal Page</a></h3>
            </c:if>
            <font style='color: red;'>
                ${WARNING == null ? "" : WARNING}
            </font>
            <table width="100%" class="cart">
                <tr><td>Product ID</td><td>Image</td><td>Price</td><td>Quantity</td><td>Action</td></tr>
                <%
                HashMap<String, Integer> cart = (HashMap<String, Integer>)session.getAttribute("cart");
                String imgUrl = "";
                int price = 0, total = 0;
                if(cart != null) {
                    Set<String> pids = cart.keySet();
                    for (String pid : pids) {
                        int quantity = cart.get(pid);
                        Plant plant = PlantDAO.getPlants(pid);
                        if(plant != null) {
                            imgUrl = plant.getImgpath();
                            price = plant.getPrice();
                            total += price * quantity;
                        }
                    %>
                    <form action="MainController" method="post">
                        <tr>
                            <td><input type="hidden" value="<%= pid %>" name="pid"><a href="ViewDetailPlant.jsp?pid=<%= pid %>"><%= pid %></a></td>
                            <td><img src="<%= imgUrl %>" class="plantimg" alt="plant"></td>
                            <td><p><%= price * quantity %></p></td>
                            <td><input type="number" value="<%= quantity %>" name="quantity" style="text-align: center;"></td>
                            <td>
                                <input type="submit" value="update" name="action">
                                <input type="submit" value="delete" name="action">
                            </td>
                        </tr>
                    </form>
                    <%}
                }
                else {%>
                    <tr><td>Your cart is empty.</td></tr>
                <%}%>
                <tr><td>Total money: <%= total %></td></tr>
            </table>
            <form action="MainController" method="post">
                <input type="submit" value="saveOrder" name="action" class="submit-order">
            </form>
        </section>
        <footer>
            <c:import url="footer.jsp" />
        </footer>
    </body>
</html>
