<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%@ page import="dao.OrderDao" %>
<%@ page import="model.Order" %>
<%@ page import="connection.DBConnection" %>
<%@ page import="java.util.List" %>
<%
    User user = (User) session.getAttribute("auth");
    if (user == null) {
        // L'utente non è loggato, reindirizza alla pagina di login
        response.sendRedirect("login.jsp");
    } else {
        OrderDao orderDao = new OrderDao(DBConnection.getConnection());
        List<Order> orders = orderDao.userOrders(user.getId());
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Page</title>
    <link rel="stylesheet" type="text/css" href="../styles/userpage.css">
    <style>
        body {
            background-color: #f5f5f5;
        }
    </style>
</head>
<body>
    <%@ include file="../fragments/navbar.jsp" %>

    <div class="container">
        <h1>Ciao <%= user.getNome() %> <%= user.getCognome() %>!</h1>
        <p><strong>Indirizzo:</strong> <%= user.getIndirizzo() %></p>
        <p><strong>Data di nascita:</strong> <%= user.getData_nascita() %></p>
        
        <h2>Storico Ordini:</h2>
        <% if (orders.isEmpty()) { %>
            <p>Nessun ordine effettuato.</p>
        <% } else { %>
            <table>
                <tr>
                    <th>Nome Prodotto</th>
                    <th>Quantità</th>
                    <th>Data Ordine</th>
                    <th>Stato</th>
                    <th>Valore</th>
                </tr>
                <% for (Order order : orders) { %>
                    <tr>
                        <td><%= order.getNome() %></td>
                        <td><%= order.getQuantity() %></td>
                        <td><%= order.getDate() %></td>
                        <td><%= order.getStato() %></td>
                        <td><%= order.getValore() %></td>
                    </tr>
                <% } %>
            </table>
        <% } %>
    </div>

    <%@ include file="../fragments/footer.jsp" %>
</body>
</html>
<%
    }
%>
