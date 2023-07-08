<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%
    User auth = user;
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Navbar</title>
    
    <% if (auth != null && auth.getEmail().equals("admin@admin.com")) { %>
        <link rel="stylesheet" type="text/css" href="../../styles/navbar.css">
    <% } else { %>
        <link rel="stylesheet" type="text/css" href="../styles/navbar.css">
    <% } %>
</head>
<body>
    <div class="navbar">
        <div class="logo">
            <img src="../media/logo.png" alt="">
        </div>
        <div class="links">
            <a href="../pages/index.jsp">Home</a>
            <% 
            if (auth != null) { %>
                <% if (auth.getEmail().equals("admin@admin.com")) { %>
                    <a href="../pages/admin/amministrazione.jsp">Pagina Admin</a>
                <% } else { %>
                    <a href="../pages/userpage.jsp">Il mio profilo</a>
                <% } %>
                <a href="${pageContext.request.contextPath}/log-out">Logout</a>
            <% } else { %>
                <a href="../pages/signupLogin.jsp">Login/Signup</a>
            <% } %>
            <a href="../pages/cart.jsp">Carrello<span>${ cart_list.size() }</span></a>
        </div>
    </div>
</body>
</html>
