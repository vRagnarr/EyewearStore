<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="dao.ProductDao" %>
<%@ page import="connection.DBConnection" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%
 User user = (User) request.getSession().getAttribute("auth"); 
   if(user != null){
	   request.setAttribute("auth", user);
   }

ProductDao pd = new ProductDao(DBConnection.getConnection());
List<Product> products = pd.getProducts();

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if(cart_list != null){
	request.setAttribute("cart_list", cart_list);
}


%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="../styles/index.css">
</head>
<body>
    <%@ include file="../fragments/navbar.jsp" %>
    <div class="container">
        <h1 class="title">CATALOGO</h1>

        <!-- Aggiungi il filtro di sesso -->
        <div class="filters">
            <label for="sex-filter">Filtro Sesso:</label>
            <select id="sex-filter">
                <option value="">Tutti</option>
                <option value="Uomo">Uomo</option>
                <option value="Donna">Donna</option>
            </select>
        </div>

        <!-- Contenitore dei prodotti -->
        <div id="product-container" class="product-container">
            <% if (!products.isEmpty()) {
                for (Product p : products) { %>
                    <div class="product-card">
                        <p class="product-brand"><%= p.getBrand() %></p>
                        <img src="../media/<%= p.getImage() %>" alt="Immagine del prodotto" class="product-image">
                        <p class="product-price">Prezzo: <%= p.getPrezzo() %> EUR</p>
                        <div class="product-buttons">
                            <a href="${pageContext.request.contextPath}/add-cart?id=<%= p.getId() %>&prezzo=<%= p.getPrezzo() %>" class="add-to-cart">Aggiungi al carrello</a>
                            <a href="../buy-now" class="buy-now">Compra ora</a>
                        </div>
                    </div>
            <% }
            } %>
        </div>
    </div>

    <%@ include file="../fragments/footer.jsp"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="../scripts/filter-script.js"></script>
<script src="../scripts/filterByModel-script.js"></script>
</body>
</html>
