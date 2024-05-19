<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="login.css">
</head>
<body>
	<form method="post" action=addproduct>
		<h1>Ajouter Produit</h1>
		<div class="input">
			<label>Nom produit</label>
			<input type="text"  name="name">
		</div>
		<div class="input">
			<label>Prix</label>
			<input type="text" name="price">
		</div>
		<button type="submit">Ajouter</button>
		<a href="produits">Produits</a>
	</form>
</body>
</html>