<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="header.jsp"%>
<div id="categorie">
	<ul>

		<c:forEach items="${categories}" var="categorie">
			<li><a href="categorie?idCategorie=${categorie.id}">${categorie.nom}</a></li>
		</c:forEach>

	</ul>
</div>

<c:forEach items="${recettes}" var="recette">
	<article>
		<header>
			<img class="imgRecette" src='img/<c:out value="${recette.photo}" />' />
			<a href="recette?id=<c:out value="${recette.id}" />">
				<h1 class="titreRecette">
					<c:out value="${recette.titre}" />
				</h1>
			</a>
			<time>
				<c:out value="${recette.dateCreation} " />
			</time>
		</header>
		<p>
			<c:out value="${recette.description} " />
		</p>
	</article>
</c:forEach>
<hr />
</div>
<%@ include file="footer.jsp"%>

