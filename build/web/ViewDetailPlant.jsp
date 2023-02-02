<%-- 
    Document   : ViewDetailPlant
    Created on : Oct 27, 2022, 7:54:07 AM
    Author     : Admin
--%>

<%@page import="basicclass.Plant"%>
<%@page import="dao.PlantDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plant Shop</title>
    </head>
    <body>
        <%
            String pid = request.getParameter("pid");
            Plant plant = PlantDAO.getPlants(pid);
        %>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <section class="view-plant-detail">
            <div class="card">
                <img src="<%= plant.getImgpath() %>" style="width: 100%;">
                <h3><%= plant.getName() %></h3>
                <p style="text-align: justify;"><%= plant.getDescription() %></p>
                <table style="margin: 1rem 0 0 0;">
                    <tr><td><b>Id</b></td><td style="width: 50%;"><b>Category</b></td><td><b>Price</b></td></tr>
                    <tr>
                        <td><%= plant.getId() %></td>
                        <td><%= plant.getCatename() %></td>
                        <td><%= plant.getPrice() %></td>
                    </tr>
                </table>
            </div>
            <img src='./images/deco1.gif' class='gif' style='left: 0;'>
            <img src='./images/deco3.gif' class='gif'>
            
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
