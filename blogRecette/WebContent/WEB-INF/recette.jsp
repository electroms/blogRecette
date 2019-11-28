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
<article>
	<header>
		<img class="imgRecette" src='img/${recette.photo}' />
		<h1 class="titreRecette">${recette.titre}
			<c:forEach var="i" begin="1" end="${noteAverage}">
                    <span class="fa fa-star checked"></span>
                </c:forEach>
                <c:forEach var="i" begin="${noteAverage + 1}" end="5">
                    <span class="fa fa-star "></span>
                </c:forEach>
		</h1>
		</br>
		<time> ${recette.dateCreation} </time>
	</header>
	<p>${recette.description}</p>
</article>
<form name="tag" method="post" action="recette?id=<c:out value="${recette.id}" />">
                 <SELECT
                    name="tag" id="tag" class="select">
                    <c:forEach items="${tags}" var="tag">
                        <OPTION value="${tag.id}"><c:out value="${tag.nom}" /><c:out value="${tag.auteur}" />
                    </c:forEach>
                </SELECT> <input type="submit" name="AddTag" value="Ajouter" class="submitBtn" />
            </form>
<hr />
<header>
	<h2 id="titreIngredient">Ingrédients</h2>
	<ul>
		<c:forEach items="${ingredients}" var="ingredient">
			<li><c:out value="${ingredient.nom}" /> <c:out
					value="${ingredient.quantite}" /> <c:out
					value="${ingredient.unit}" /></li>
		</c:forEach>
	</ul>
</header>
<h2 id="titreCommentaire">Commentaires</h2>
<c:forEach items="${commentaires}" var="commentaire">
	<div class="divCommentaire">
		<p>
			<c:out value="${commentaire.auteur}" />
			:
			<c:out value="${commentaire.contenu}" />
		</p>
		<p>Note : ${commentaire.note}/5</p>
		<p>${commentaire.dateCreation}</p>
		<hr>
	</div>
</c:forEach>
<form method="post" action="recette?id=${recette.id}">
	<input id="auteur" name="auteur" type="text" placeholder="Votre
nom *"
		class="inputChamp" /><br />
	<textarea id="txtCommentaire" name="contenu" rows="4"
		placeholder="Votre commentaire *" class="inputTextArea"></textarea>
	<br /> <label for="note">Note</label><br /> <select name="note"
		id="note" class="select">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
		<option value="5">5</option>
	</select> <br /> <input type="submit" name="AddComment" value="Commenter" class="submitBtn" />
</form>
<div id="erreur">
	<p>Erreurs</p>
</div>
</div>
<%@ include file="footer.jsp"%>
