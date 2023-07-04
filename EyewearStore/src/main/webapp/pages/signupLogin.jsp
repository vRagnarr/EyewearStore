<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
  User user = (User) request.getSession().getAttribute("auth"); 
  if (user != null) {
    request.setAttribute("auth", user);
    String contextPath = request.getContextPath();
    response.sendRedirect(contextPath + "/pages/index.jsp");
  }
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Registrazione</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="login.css">
  <script src="Validation.js"></script>
</head>
<body>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-5">
        <div class="card">
          <h2 class="card-title text-center">Registrazione <a href="../pages/index.jsp">ClubOf90s</a></h2>
          <div class="card-body py-md-4">
            <form action="../signup" method="post">
              <div class="form-group">
                <input type="text" class="form-control" id="name" placeholder="Nome" name="nome">
              </div>
               <div class="form-group">
                <input type="text" class="form-control" id="surname" placeholder="Cognome" name="cognome">
              </div>
              <div class="form-group">
                <input type="text" class="form-control" id="address" placeholder="Indirizzo" name="indirizzo">
              </div>
              <div class="form-group">
                <input type="date" class="form-control" id="birthdate" placeholder="Data di nascita" name="data_nascita">
              </div>
              <div class="form-group">
                <label>Sesso:</label>
                <div class="form-check">
                  <input class="form-check-input" type="radio" name="sesso" id="male" value="Uomo">
                  <label class="form-check-label" for="male">Maschio</label>
                </div>
                <div class="form-check">
                  <input class="form-check-input" type="radio" name="sesso" id="female" value="Donna">
                  <label class="form-check-label" for="female">Femmina</label>
                </div>
              </div>
              <div class="form-group">
                <input type="email" class="form-control" id="email" placeholder="Email" name="email">
              </div>
              <div class="form-group">
                <input type="password" class="form-control" id="password" placeholder="Password" name="password" oninput="validatePassword()">
              </div>
              <div class="form-group">
                <input type="password" class="form-control" id="confirm-password" placeholder="Conferma password" name="confirm-password" oninput="validatePassword()">                
              </div>
              <div class="d-flex flex-row align-items-center justify-content-between">
                <a href="../pages/login.jsp">Accedi</a>
                <button class="btn btn-primary" id="submit-btn" disabled>Crea Account</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</html>
