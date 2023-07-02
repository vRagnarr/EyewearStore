<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Navbar</title>
    <link rel="stylesheet" type="text/css" href="../styles/navbar.css">
</head>
<body>
    <div class="navbar">
        <div class="logo">
            <img src="../media/logo.png" alt="Logo">
        </div>
        <div class="search">
            <form action="#" method="GET">
                <input type="text" name="search" placeholder="Cerca...">
                <button type="submit">Cerca</button>
            </form>
        </div>
        <div class="links">
            <a href="../pages/index.jsp">Home</a>
            <% 
            if (user != null) { %>
            	<a href="${pageContext.request.contextPath}/log-out">Logout</a>
            <% } else { %>
            	<a href="../pages/signupLogin.jsp">Login/Signup</a>
            <%} 
            %>
            <a href="../pages/cart.jsp">Carrello<span>${ cart_list.size() }</span></a>
        </div>
    </div>
</body>
</html>
