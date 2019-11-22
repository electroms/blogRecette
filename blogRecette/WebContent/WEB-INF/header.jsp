<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="fr">
<head>
<meta charset="UTF-8" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="css/style.css" />
<title>Mon Blog de Recettes</title>
</head>
<body>
	<header id="header">
		<a href="acceuil"><h1 id="titreBlog">Mon Blog de Recettes</h1></a>
		<div style="width: 300px; margin: 20px auto;">Bienvenue sur mon
			blog de recettes</div>
		<div id="loginBar">

			<c:choose>
				<c:when test="${empty sessionScope.membre}">
					<div class="login">
						<a class="primaryBtn login" href="inscription">Inscription</a>
					</div>
					<div class="connexion">
						<a class="primaryBtn connexion" href="login">Connexion</a>
					</div>
				</c:when>
				<c:otherwise>
					<span class="erreur">Bienvenu ${sessionScope.membre.nom}
						alias ${sessionScope['membre'].pseudo}.
						<a class="primaryBtn login" href="tag">Administration des Tags</a>
						<div class="deconnexion">
							<a class="primaryBtn deconnexion" href="login?logout=true">Deonnexion</a>
						</div>
				</c:otherwise>
			</c:choose>

		</div>
	</header>
	<div id="global">