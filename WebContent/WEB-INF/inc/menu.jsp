<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #333;
}

li {
	float: left;
}

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

li a:hover:not (.active ) {
	background-color: #111;
}

.active {
	background-color: #4CAF50;
}
</style>
<div class="menu">
	<ul>
		<c:choose>
			<c:when test="${empty sessionScope.user}">
				<li><a href="<c:url value='/login'/>">Se connecter</a></li>
			</c:when>
			<c:otherwise>
				<li><a class="active" href="#">Gestion des clients</a></li>
				<li><a href="<c:url value='/clients/add'/>">Ajouter un
						client</a></li>
				<li><a href="<c:url value='/clients/list'/>">Liste des
						clients</a></li>
				<li style="float:right"><a href="<c:url value='/logout'/>">Se déconnecter</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>