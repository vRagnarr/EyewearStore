<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<% User user = (User) request.getSession().getAttribute("auth"); 
	   if(user != null){
		   request.setAttribute("auth", user);
		   String contextPath = request.getContextPath();
		   response.sendRedirect(contextPath + "/pages/index.jsp");
	   }
	%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="login.css">
</head>
<body>
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-5">
        <div class="card">
          <h2 class="card-title text-center">Login <a href="../pages/index.jsp">ClubOf90s</a></h2>
          <div class="card-body py-md-4">
            <form action="../login" method="post">
              <div class="form-group">
                <input type="email" class="form-control" id="email" placeholder="Email" name="email">
              </div>
              <div class="form-group">
                <input type="password" class="form-control" id="password" placeholder="Password" name="password">
              </div>
              <div class="d-flex flex-row align-items-center justify-content-between">
                <a href="../pages/signupLogin.jsp">Registrati</a>
                <button class="btn btn-primary">Accedi</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
