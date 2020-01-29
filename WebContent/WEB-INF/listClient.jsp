<%@page import="java.util.ArrayList"%>
<%@page import="beans.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value='/style.css'/>">
<title>Liste des clients</title>
</head>
<body>
	<jsp:include page="inc/menu.jsp" />
	<div>
	  <table id="client" border="1">

		<c:if test="${!empty requestScope.clients}">
			<tr>
				<th>Prénom</th>
				<th>Nom</th>
				<th>Login</th>
				<th>Password</th>
				<th colspan="2">Actions</th>
			</tr>
			<c:forEach items="${requestScope.clients}" var="client">
				<tr>
					<td><c:out value="${client.prenom}" /></td>
					<td><c:out value="${client.nom }" /></td>
					<td><c:out value="${client.adresse }" /></td>
					<td><c:out value="${client.telephone }" /></td>
					<td><a href="modify?idClient=${client.idClient }">Modifier</a></td>
					<td><a href="delete?idClient=${client.idClient }">Supprimer</a></td>
				</tr>
			</c:forEach>
		</c:if>

	</table>
	</div>

</body>
</html>