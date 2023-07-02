<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="model.User" %>
<%@page import="model.Product" %>
<%@page import="model.Order"%>
<%@page import="dao.OrderDao"%>
<%@page import="dao.ProductDao"%>
<%@page import="dao.UserDao"%>
<%@page import="connection.DBConnection"%>
<%@ page import="java.util.List" %>
	<% User user = (User) request.getSession().getAttribute("auth"); 
	   if(user != null){
		   request.setAttribute("auth", user);
	   }%>
	   
<!DOCTYPE html>
<html>
<head>
    <title>Gestione Admin</title>
</head>
<body>

	<h1>Aggiungi Prodotto</h1>
    <form action="../../admin" method="post">
        <input type="hidden" name="action" value="addProduct">
        <table>
            <tr>
                <th>Nome</th>
                <th>Brand</th>
                <th>Modello</th>
                <th>Prezzo</th>
                <th>Sesso</th>
            </tr>
            <tr>
                <td><input type="text" name="nome"></td>
                <td><input type="text" name="brand"></td>
                <td><input type="text" name="modello"></td>
                <td><input type="text" name="prezzo"></td>
                <td><input type="text" name="sesso"></td>
            </tr>
        </table>
        <button type="submit">Aggiungi</button>
    </form>
	
    <h1>Prodotti</h1>
    <table border="1">
        <tr>
        	<th>Id</th>
            <th>Immagine</th>
            <th>Nome</th>
            <th>Brand</th>
            <th>Prezzo</th>
            <th>Modifica Prezzo</th>
            <th>Elimina Prodotto</th>
        </tr>
        <%
            // Esempio di codice per iterare sulla lista di prodotti e popolare la tabella
            List<Product> products = new ProductDao(DBConnection.getConnection()).getProducts();
            for (Product product : products) {
        %>
        <tr>
        	<td><%= product.getId()%></td>
            <td><img src="<%= product.getImage() %>"></td>
            <td><%= product.getNome() %></td>
            <td><%= product.getBrand() %></td>
            <td><%= product.getPrezzo() %></td>
            <td>
            	<form action="../../admin" method="post">
                    <input type="hidden" name="productId" value="<%= product.getId() %>">
                    <input type="hidden" name="action" value="updatePrice">
                  	<input type="text" name="newPrice"> 
                    <button type="submit">Modifica Prezzo</button>
                </form>
            </td>
            <td> 
            	<form action="../../admin" method="post">
                    <input type="hidden" name="productId" value="<%= product.getId() %>">
                    <input type="hidden" name="action" value="deleteProduct">
                    <button type="submit">Elimina Prodotto</button>
                </form>
            </td>
            
        </tr>
        <%
            }
        %>
    </table>

    <h1>Ordini</h1>
    <table border="1">
        <tr>
            <th>ID Utente</th>
            <th>ID Prodotto</th>
            <th>Quantità</th>
            <th>Valore</th>
            <th>Stato</th>
            <th>Modifica Stato</th>
            <th>Elimina Ordine</th>
        </tr>
        <%
            // Esempio di codice per iterare sulla lista di ordini e popolare la tabella
            List<Order> orders = new OrderDao(DBConnection.getConnection()).getAllOrders();
            for (Order order : orders) {
        %>
        <tr>
            <td><%= order.getUserId() %></td>
            <td><%= order.getId() %></td>
            <td><%= order.getQuantity() %></td>
            <td><%= order.getValore() %></td>
            <td><%= order.getStato() %></td>
            <td>
            	<form action="../../admin" method="post">
                    <input type="hidden" name="orderId" value="<%= order.getOrderId() %>">
                    <input type="hidden" name="action" value="updateStatus">
                    <button type="submit">Aggiorna Stato</button>
                </form>
            </td>
            <td>
            	<form action="../../admin" method="post">
                    <input type="hidden" name="orderId" value="<%= order.getOrderId() %>">
                    <input type="hidden" name="action" value="deleteOrdine">
                    <button type="submit">Elimina Ordine</button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>

    <h1>Utenti</h1>
    <table border="1">
        <tr>
        	<th>Id</th>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Email</th>
            <th>Indirizzo</th>
            <th>Elimina Utente</th>
        </tr>
        <%
            // Esempio di codice per iterare sulla lista di utenti e popolare la tabella
            List<User> users = new UserDao(DBConnection.getConnection()).getAllUsersExceptAdmin();
            for (User u : users) {
        %>
        <tr>
        	<td><%= u.getId() %></td>
            <td><%= u.getNome() %></td>
            <td><%= u.getCognome() %></td>
            <td><%= u.getEmail() %></td>
            <td><%= u.getIndirizzo() %></td>
            <td>
            	<form action="../../admin" method="post">
                    <input type="hidden" name="userId" value="<%= u.getId() %>">
                    <input type="hidden" name="action" value="deleteUtente">
                    <button type="submit">Elimina Utente</button>
                </form>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>

