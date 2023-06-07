<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet" %> <!-- importare ResultSet -->
<!DOCTYPE html>
<html>
<head>
    <title>Product Pages</title>
</head>
<body>
    <h1>Elenco dei prodotti</h1>
    
    <table>
        <tr>
            <th>Prezzo</th>
            <th>Marca</th>
        </tr>
        
        <%
        ResultSet resultSet = (ResultSet) request.getAttribute("resultSet");
        while (resultSet.next()) {
            float prezzo = resultSet.getFloat("prezzo");
            String marca = resultSet.getString("marca");
        %>
            <tr>
                <td><%= prezzo %></td>
                <td><%= marca %></td>
            </tr>
        <% } %>
    </table>
</body>
</html>
    