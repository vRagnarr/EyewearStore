<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*" %>
<%@page import="connection.*" %>
<%@page import="java.util.*" %>
<%@page import="dao.*" %>
    <%
    List<Order> orders = null;
    User user = (User) request.getSession().getAttribute("auth");
    if(user != null){
    	request.setAttribute("auth", user);
    	orders = new OrderDao(DBConnection.getConnection()).userOrders(user.getId());
    }
    
    
    %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tabella Prodotti</title>
</head>
<body>
    <h1>Storico Ordini</h1>
    
    <table>
    	<thead>
        	<tr>
            	<th>Data</th>
            	<th>Nome</th>
            	<th>Brand</th>
            	<th>Quantità</th>
            	<th>Prezzo</th>
            	<th>Stato</th>
        	</tr>
       	</thead> 
        <tbody>
        	<%
        	
        	if(orders != null){
        		for(Order o: orders){%>
        		<tr>
        			<td><%= o.getDate()%> </td>
        			<td><%= o.getNome()%> </td>
        			<td><%= o.getBrand()%> </td>
        			<td><%= o.getQuantity()%> </td>
        			<td><%= o.getValore()%> </td>
        			<td><%= o.getStato()%> </td>
        		</tr>
        		<%}
        	
        	}
        	
        	%>
        </tbody>
    </table>
</body>
</html>
