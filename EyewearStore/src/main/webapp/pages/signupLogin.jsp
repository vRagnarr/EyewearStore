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
		<meta name="viewport" content="width=device-width, initial-scale=0.5">
		<link rel="stylesheet" type="text/css" href="../styles/signupLogin.css">
	</head>
	<body>
		<video autoplay muted loop id="back-video">
			<source src="../media/video.mp4" type="video/mp4">
		</video>
		<div class="login-main">
			<div style="height: 100%; width: 50%; display: flex; flex-wrap: nowrap; justify-content: center; align-items: center; float: left;">
				<form id="registration" action="../signup" method="post">
					<h1>SIGNUP</h1>
					<div style="width: 100%; display: flex; flex-wrap: nowrap; margin: 10px auto;">
						<input type="text" placeholder="Nome" required id="input-nome" name="nome"><input type="text" placeholder="Cognome" required id="input-cognome" name="cognome">
					</div>
					<input type="text" name="data_nascita" placeholder="Data di nascita" required onfocus="(this.type='date')">
					<input type="text" name="indirizzo" placeholder="Indirizzo">
					<div style="width: 100%; text-align: center; margin-top: 10px; min-width: 150px;">
						<input type="radio" name="sesso" id="uomo" required value="uomo">
						<label for="uomo">Uomo</label>
						<input type="radio" name="sesso" id="donna" required value="uomo">
						<label for="donna">Donna</label>
					</div>
					<input type="email" name="email" placeholder="Email" required>
					<input type="password" name="password" placeholder="Password" required>
					<input type="submit" value="Ok">
				</form>
			</div>
			<div style="height: 100%; width: 50%; display: flex; flex-wrap: nowrap; justify-content: center; align-items: center; float: right;">
				<form id="login" action="../login" method="post">
					<h1>LOGIN</h1>
					<input type="email" placeholder="Email" required name="email">
					<input type="password" placeholder="Password" required name="password">
					<div style="margin: 10px auto; min-width: 150px;">
					</div>
					<input type="submit" value="OK">
				</form>
			</div>
			<input type="checkbox" id="check1" onclick="slide()">
			<label for="check1" id="moving-panel">
				<img src="../media/logo.png" height=200px width=200px>
				<h1>PREMI PER</h1>
				<h1 id="texttochange">LOGIN</h1>
				<div id="nav-categories-arrow-container">
					<span class="left-arrow">
					</span>
					<span class="right-arrow">
					</span>
				</div>
			</label>
		</div>
		<script src="../scripts/signupLogin.js"></script>
	</body>
</html>
