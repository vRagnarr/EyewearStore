<%@page import="connection.DBConnection"%>
<%@page import="model.*"%>
<%@page import="dao.*"%>
<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%User user = (User) request.getSession().getAttribute("auth");
    if(user != null){
    	request.setAttribute("auth", user);
    }
    
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProduct = null;
    if(cart_list != null){
    	ProductDao pDao = new ProductDao(DBConnection.getConnection());
    	cartProduct = pDao.getCartProducts(cart_list);
    	request.setAttribute("cart_list", cart_list);
    	
    }
    %>
<!DOCTYPE html>
<html>
<head>
    <title>Carrello</title>
    <link rel="stylesheet" type="text/css" href="../styles/cartStyle.css">
</head>
<body>
	<%@include file="../fragments/navbar.jsp" %>	
    <table class="carrello-table">
        <thead>
            <tr>
                <th class="col-prodotto">Prodotto</th>
                <th class="col-categoria">Categoria</th>
                <th class="col-prezzo">Prezzo</th>
                <th class="col-quantita">Quantità</th>
                <th class="col-azioni">Azioni</th>
            </tr>
        </thead>
        <tbody>
        <% 
        if(cart_list != null){
           for (Cart c: cartProduct){%>
        		<tr>
                <td><%= c.getNome() %></td>
                <td><%= c.getMarca() %></td>
                <td><%= c.getPrezzo() %></td>
                <td>
                    <form>
                        <button type="button">-</button>
                        <input type="number" min="0" value="1">
                        <button type="button">+</button>
                    </form>
                </td>
                <td>
                    <button type="submit">Buy Now</button>
                    <button type="submit">Rimuovi</button>
                </td>
            </tr>
        	<% }
        }
        %>
            
        </tbody>
    </table>

    <p id="totalPrice">Totale carrello: <span>0.00 €</span></p>
</body>
</html>

