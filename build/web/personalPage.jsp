<%-- 
    Document   : personalPage
    Created on : Oct 13, 2022, 11:39:20 PM
    Author     : Admin
--%>

<%@page import="dao.AccountDAO"%>
<%@page import="basicclass.Account"%>
<%@page import="dao.OrderDAO"%>
<%@page import="basicclass.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plant Shop</title>
    </head>
    <body>
        <c:if test="${name == null}">
            <c:redirect url="index.jsp"></c:redirect>
        </c:if>
        <%
            String name = (String) session.getAttribute("name");
            String email = (String) session.getAttribute("email");
            Cookie[] c = request.getCookies();
            boolean login = false;
            
            //filter status of order
            String st = request.getParameter("status");
            int filter;
            if(st == null || st.trim() == "") filter = 0;
            else filter = Integer.parseInt(st.trim());
            
            //filter date of order
            String startDay = request.getParameter("startDay");
            String endDay = request.getParameter("endDay");
            String msg = request.getParameter("msg");
            
            if (name == null) {
                String token = "";
                for (Cookie acookie : c) 
                    if (acookie.getName().equals("selector")) {
                        token = acookie.getValue();
                        Account acc = AccountDAO.getAccount(token);
                        if(acc != null) {
                            name = acc.getFullname();
                            email = acc.getEmail();
                            login = true;
                        }    
                    }
            }
            else login = true;
            
            if(login == false) {
        %>
        <p><font color="red">You must login to view personal page</font></p>
        <p></p>
        <%
        } else {
        %>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <section>
            <h3>Welcome <%= name%> comeback</h3>
            <h3><a href="MainController?action=logout">Logout</a></h3>
            <br/>
            <div class="filter-button">
                <div>
                    <a style="<%= filter == 0 ? "background: #BCE29E;" : "" %>" href="personalPage.jsp?status=0">All</a>
                    <a style="<%= filter == 1 ? "background: #BCE29E;" : "" %>" href="personalPage.jsp?status=1">Processing</a>
                    <a style="<%= filter == 2 ? "background: #BCE29E;" : "" %>" href="personalPage.jsp?status=2">Completed</a>
                    <a style="<%= filter == 3 ? "background: #BCE29E;" : "" %>" href="personalPage.jsp?status=3">Canceled</a>
                </div>
                <form action="MainController" method="post">
                    From <input type="date" name="startDay" value="<%= startDay %>">
                    to <input type="date" name="endDay" value="<%= endDay %>">
                    <input type="submit" name="action" value="Filter">
                    <%if(msg != null) {%>
                        <p style="color: red;">Start day need to less or equal than end day!</p>
                    <%}%>
                </form>
            </div>
            <br/>
            <%
                if (email != null) {
                    ArrayList<Order> list = OrderDAO.getOrders(email);
                    String[] status = {"", "processing", "completed", "canceled"};
                    if (list != null) {%>
                        <table class="order">
                        <%for (Order ord : list) 
                            if(filter == 0 || filter == ord.getStatus())
                                if(startDay == null || startDay.equals("") || startDay.compareTo(ord.getOrderDate()) <= 0)
                                    if(endDay == null || startDay.equals("") || endDay.compareTo(ord.getOrderDate()) >= 0)
                        {%>
                                <tr class="title"><td>Order ID</td><td>Order Date</td><td>Ship Date</td><td>Order's Status</td><td>Action</td></tr>
                                <tr>
                                    <td><%= ord.getOrderID() %></td>
                                    <td><%= ord.getOrderDate() %></td>
                                    <td><%= ord.getShipDate() %></td>
                                    <td>
                                        <%= status[ord.getStatus()]%>
                                        <br/>
                                        <% if (ord.getStatus() == 1) {%>
                                        <a href="ChangeStatusOrderServlet?orderid=<%= ord.getOrderID() %>&status=3">cancel order</a>
                                        <%}%>
                                        <% if (ord.getStatus() == 3) {%>
                                        <a href="ChangeStatusOrderServlet?orderid=<%= ord.getOrderID() %>&status=1">order again</a>
                                        <%}%>
                                    </td>
                                    <td><a href="MainController?action=viewOrderDetail&orderid=<%= ord.getOrderID() %>">Detail</a></td>
                                </tr>
                                <tr></tr>
                        <%}%>
                            </table>
                    <%}
                    else {%>
                        <p>You don't have any order.</p>
                    <%}
                }
            %>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <%}%>
    </body>
</html>
