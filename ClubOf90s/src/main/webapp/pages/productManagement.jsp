<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.ResultSet" %> <!-- importare ResultSet -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista prodotti</title>
</head>
<body>
    <h1>Lista prodotti</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Prodotto</th>
            <th>Quantità</th>
            <th>Prezzo</th>
        </tr>
        <% 
            ResultSet resultSet = (ResultSet) request.getAttribute("products");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                int quantita = resultSet.getInt("quantita");
                double prezzo = resultSet.getDouble("prezzo");
                String prezzoFormatted = String.format("%.2f", prezzo); // Formatta il prezzo con due decimali
        %>
        <tr>
            <td><%= id %></td>
            <td><%= nome %></td>
            <td><%= quantita %></td>
            <td><%= prezzoFormatted %></td>
        </tr>
        <% 
            }
        %>
    </table>
    
    <h2>Inserisci prodotto</h2>
    <form action="addProduct" method="post">
        <label for="id">ID:</label>
        <input type="number" id="id" name="id" required><br>
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required><br>
        <label for="marca">Marca:</label>
        <input type="text" id="marca" name="marca" required><br>
        <label for="sesso">Sesso:</label>
        <select id="sesso" name="sesso" required>
            <option value="M">M</option>
            <option value="F">F</option>
            <option value="U">U</option>
        </select><br>
        <label for="quantita">Quantità:</label>
        <input type="number" id="quantita" name="quantita" required><br>
        <label for="prezzo">Prezzo:</label>
        <input type="number" id="prezzo" name="prezzo" step="0.01" required><br>
        <input type="submit" value="Aggiungi">
    </form>
</body>
</html>
