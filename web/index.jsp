<%-- 
    Document   : index
    Created on : Oct 13, 2022, 11:19:13 PM
    Author     : Admin
--%>

<%@page import="dao.PlantDAO"%>
<%@page import="basicclass.Plant"%>
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
            <%@include file="header.jsp" %>
        </header>
        <section>
            <%
                String msg = request.getParameter("msg");
                if(msg != null) {
            %>
            <script>
                alert("Add to cart successfully!");
            </script>
            <%}%>
            <%
                String keyword = request.getParameter("txtsearch");
                String searchby = request.getParameter("searchby");
                ArrayList<Plant> list; 
                String [] tmp = {"out of stock", "available"};
                if(keyword == null && searchby == null)
                    list = PlantDAO.getPlants("", "");
                else 
                    list = PlantDAO.getPlants(keyword, searchby);
                if(list != null && !list.isEmpty()) {%>
                    <table class="product-table">
                    <%for (Plant p : list) {%>
                        <tr>
                            <td><img src="<%= p.getImgpath() %>" class='plantimg'/></td>
                            <td>Product ID: <%= p.getId() %></td>
                            <td>Product name: <%= p.getName()%></td>
                            <td>Price: <%= p.getPrice() %></td>
                            <td>Status: <%= tmp[p.getStatus()] %></td>
                            <td>Category: <%= p.getCatename()%></td>
                            <td><a href="MainController?action=addtocart&pid=<%= p.getId() %>">Add to cart</a></td>
                        </tr>  
                    <%}%>
                    </table> 
                <%}
            %>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
