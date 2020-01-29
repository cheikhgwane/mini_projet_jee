<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
<link rel="stylesheet" href="<c:url value='/style.css'/>">
</head>
</head>
<body>
	<div class="container">
		<form class="loginForm" method="post">

			<label>Login</label> <br> <input type="text" name="login">
			<br> <label>Password</label> <br> <input type="password"
				name="password"> <br> <input type="submit"
				value="Se connecter">
		</form>
	</div>

</body>
</html>

