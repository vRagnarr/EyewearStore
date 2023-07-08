<%@page import="model.Product"%>
<%@page import="dao.ProductDao"%>
<%@page import="connection.DBConnection"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    String selectedSex = request.getParameter("sex");

    ProductDao pd = new ProductDao(DBConnection.getConnection());
    List<Product> products = pd.getProducts();

    // Filtra i prodotti in base al sesso selezionato
    List<Product> filteredProducts = new ArrayList<>();
    if (selectedSex != null && !selectedSex.isEmpty()) {
        for (Product p : products) {
            if (p.getSesso().equalsIgnoreCase(selectedSex) || p.getSesso().equalsIgnoreCase("Unisex")) {
                filteredProducts.add(p);
            }
        }
    } else {
        filteredProducts = products;
    }

    // Genera il contenuto HTML dei prodotti filtrati
    StringBuilder htmlBuilder = new StringBuilder();
    for (Product p : filteredProducts) {
        htmlBuilder.append("<div class=\"product-card\">")
                .append("<p class=\"product-brand\">").append(p.getBrand()).append("</p>")
                .append("<img src=\"../media/").append(p.getImage()).append("\" alt=\"Immagine del prodotto\" class=\"product-image\">")
                .append("<p class=\"product-price\">Prezzo: ").append(p.getPrezzo()).append(" EUR</p>")
                .append("<div class=\"product-buttons\">")
                .append("<a href=\"${pageContext.request.contextPath}/add-cart?id=").append(p.getId()).append("&prezzo=").append(p.getPrezzo()).append("\" class=\"add-to-cart\">Aggiungi al carrello</a>")
                .append("<a href=\"../buy-now\" class=\"buy-now\">Compra ora</a>")
                .append("</div>")
                .append("</div>");
    }

    // Restituisci il contenuto HTML dei prodotti filtrati
    out.print(htmlBuilder.toString());
%>
