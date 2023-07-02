<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    // Verifica se l'attributo di sessione desiderato è ancora presente
    if (session.getAttribute("nome") == null) {
        // L'utente non è più loggato, reindirizza alla pagina di login
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Benvenuto</title>
</head>
<body>
    <%-- Ottieni il nome utente dalla sessione --%>
    <% String nomeUtente = (String) session.getAttribute("nome"); %>
    
    <%-- Verifica se l'utente è loggato --%>
    <% if (nomeUtente != null && !nomeUtente.isEmpty()) { %>
        <h1>Ciao <%= nomeUtente %>!</h1>
    <% } else { %>
        <h1>Utente non loggato</h1>
    <% } %>
</body>
</html>
