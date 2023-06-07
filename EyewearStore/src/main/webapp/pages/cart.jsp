<%@page import="connection.DBConnection"%>
<%@page import="model.*"%>
<%@page import="dao.*"%>
<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%User user = (User) request.getSession().getAttribute("auth");
    if(user != null){
    	request.setAttribute("auth", user);
    }%>
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
            <tr>
                <td>Prodotto 1</td>
                <td>Categoria 1</td>
                <td>10.00 €</td>
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
            <tr>
                <td>Prodotto 2</td>
                <td>Categoria 2</td>
                <td>15.00 €</td>
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
            <!-- Aggiungere altre righe per i prodotti nel carrello -->
        </tbody>
    </table>

    <p id="totalPrice">Totale carrello: <span>0.00 €</span></p>
</body>
</html>

