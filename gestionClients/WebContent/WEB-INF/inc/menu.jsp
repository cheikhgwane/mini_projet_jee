<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
 .menu{
	border: 1px solid black;
    height: 500px;
    float:left;
    width: 170px;
    box-shadow: 2px 2px 10px 0 grey ;
}
</style>
<div class="menu">
	<ul>
		<c:choose>
			<c:when test="${empty sessionScope.user}">
				<li><a href="<c:url value='/login'/>">Se connecter</a></li>
			</c:when>
			<c:otherwise>
			  <h1>Menu </h1>
				<li><a href="<c:url value='/clients/add'/>">Ajouter un
						client</a></li>
				<li><a href="<c:url value='/clients/list'/>">Liste des
						clients</a></li>
				<li><a href="<c:url value='/logout'/>">Se déconnecter</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>