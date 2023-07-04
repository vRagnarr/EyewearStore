<%@page import="connection.DBConnection"%>
<%@page import="model.*"%>
<%@page import="dao.*"%>
<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    User user = (User) request.getSession().getAttribute("auth");
    if (user != null) {
        request.setAttribute("auth", user);
    }
    
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartProduct = null;
    String total = "0"; // Imposta il valore predefinito a 0
    if (cart_list != null) {
        ProductDao pDao = new ProductDao(DBConnection.getConnection());
        cartProduct = pDao.getCartProducts(cart_list);
        total = pDao.getTotalCartPrice(cart_list);
        request.setAttribute("cart_list", cart_list);
        request.setAttribute("total", total);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Carrello</title>
    <link rel="stylesheet" type="text/css" href="../styles/cartStyle.css">
    <script src="../scripts/cartScript.js"></script>
</head>
<body>
    <%@include file="../fragments/navbar.jsp" %>
    <div class="carrello-table-container">
    <table class="carrello-table">
        <thead>
            <tr>
                <th class="col-prodotto">Prodotto</th>
                <th class="col-categoria">Brand</th>
                <th class="col-prezzo">Prezzo</th>
                <th class="col-quantita">Quantità</th>
                <th class="col-azioni">Azioni</th>
            </tr>
        </thead>
        <tbody>
        <%
        if (cart_list != null) {
            for (Cart c: cartProduct) {
            	System.out.println(c.getQuantity());
        %>
            <tr>
                <td><%= c.getNome() %></td>
                <td><%= c.getBrand() %></td>
                <td><%= c.getPrezzo() %></td>
                <td>
                    <form>
  						<a href="../inc-dec?action=dec&id=<%= c.getId() %>">-</a>
  						<input type="text" name="quantity" value="<%= c.getQuantity() %>" readonly>
  						<a href="../inc-dec?action=inc&id=<%= c.getId() %>">+</a>
					</form>
                </td>
                <td>
                    <a href="../buy-now?id=<%= c.getId() %>&quantity=<%= c.getQuantity() %>&prezzo=<%= c.getPrezzo() %>" type="submit">Buy Now</a>
                    <a href="../remove-cart?id=<%= c.getId() %>" type="submit">Rimuovi</a>
                </td>
            </tr>
        <% 
            }
        }
        %>
        </tbody>
    </table>
	</div>
    <p id="totalPrice">
    Totale carrello:
    <% if (Float.parseFloat(total) > 0) { %>
        <span><%= total %></span>
    <% } else { %>
        <span>0</span>
    <% } %>
    euro
</p>
<a href="../check-out" class="checkout-button">Check-out</a>
<%@include file="../fragments/footer.jsp" %>
</body>
</html>
