<%-- 
    Document   : ManageAccounts
    Created on : Oct 30, 2022, 8:18:07 PM
    Author     : Admin
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="basicclass.Account"%>
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
                <input type="submit" name="action" value="searchAccount"/>
            </form>
            <h1></h1>
            <table class="order">
                <tr class="title">
                    <td>ID</td>
                    <td>Email</td>
                    <td>Full name</td>
                    <td>Status</td>
                    <td>Phone</td>
                    <td>Role</td>
                    <td>Action</td>
                </tr>
                <c:forEach var="acc" items="${listAccount}">
                    <c:if test="${search == null || acc.fullname.toLowerCase().contains(search.toLowerCase())}">
                        <tr>
                            <td><c:out value="${acc.accid}"></c:out></td>
                            <td><c:out value="${acc.email}"></c:out></td>
                            <td><c:out value="${acc.fullname}"></c:out></td>
                            <td>
                                <c:choose>
                                    <c:when test="${acc.status eq 1}">Active</c:when>
                                    <c:otherwise>Inactive</c:otherwise>
                                </c:choose>
                            </td>
                            <td><c:out value="${acc.phone}"></c:out></td>
                            <td>
                                <c:choose>
                                    <c:when test="${acc.role eq 1}">Admin</c:when>
                                    <c:otherwise>User</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:if test="${acc.role eq 0}">
                                    <c:url var="mylink" value="MainController">
                                        <c:param name="email" value="${acc.email}"></c:param>
                                        <c:param name="status" value="${acc.status}"></c:param>
                                        <c:param name="action" value="updateStatusAccount"></c:param>
                                    </c:url>
                                    <a href="${mylink}">Block/Unblock</a>
                                </c:if>
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
