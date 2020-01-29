<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajout Client</title>
<link rel="stylesheet" href="<c:url value='/style.css'/>">
</head>
<body>
	<div>
		<c:import url="inc/menu.jsp" />
		<form class="addForm" method="post" action="modify">
			<fieldset>
				<legend>Ajout d'un client</legend>
				<label>Nom :</label> <input type="text" name="nom"
					value="${ requestScope.client.nom }"> <span>${ error.nom }</span><br>
				<label>Prénom :</label> <input type="text" name="prenom"
					value="${ requestScope.client.prenom }"> <span>${ error.prenom }</span><br>
				<label>Adresse:</label> <input type="text" name="adresse"
					value="${ requestScope.client.adresse }"> <span>${ error.adresse }</span><br>
				<label>Telephone:</label> <input type="text" name="telephone"
					value="${ requestScope.client.telephone }"> <span>${ error.telephone }</span><br>
					<input type="hidden" name="idClient"
					value="${ requestScope.client.idClient }">
				<input type="submit" value="Modifier"> <span
					class="${ empty error ? 'succes' : 'erreur'}">${ status }</span>
			</fieldset>
		</form>
	</div>
</body>
</html>