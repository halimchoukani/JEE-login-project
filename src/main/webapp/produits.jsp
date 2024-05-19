<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Metier.Produit" %>
<%
    List<Produit> prods = (List<Produit>) request.getAttribute("prods");

    // Logging the products attribute
    if (prods == null) {
        System.out.println("JSP: No products found - prods is null");
    } else if (prods.isEmpty()) {
        System.out.println("JSP: No products found - prods is empty");
    } else {
        System.out.println("JSP: Products retrieved: " + prods.size());
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Products Table</title>
    <link rel="stylesheet" href="produit.css" />
</head>
<body>
	<a href="addproduct" class="addproduct">+ Ajouter Produit</a>
    <table border="1">
        <thead>
        <tr>
          <th>Id</th>
          <th>Name</th>
          <th>Price</th>
          <th>Actions</th>
        </tr>
      </thead>
        <%
            if (prods != null && !prods.isEmpty()) {
                for (Produit product : prods) {
        %>
        <tr>
	        <td><%= product.getIdProduit() %></td>
	        <td><%= product.getNomProduit() %></td>
	        <td><%= product.getPrix() %></td>
	        <td>
	          <form action="deleteProduit" method="post" style="display: inline">
	            <input
	              type="hidden"
	              name="idProduit"
	              value="<%= product.getIdProduit() %>"
	            />
	            <button type="submit" class="delete">Delete</button>
	          </form>
	        </td>
      	</tr>
        <%
                }
            } else {
        %>
        <tr class="notfound">
        <td colspan="4">No products found</td>
      </tr>
        <%
            }
        %>
    </table>
</body>
</html>
