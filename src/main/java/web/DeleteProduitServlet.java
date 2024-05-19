package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import dao.ProduitDaoImpl;

@WebServlet("/deleteProduit")
public class DeleteProduitServlet extends HttpServlet {
    private ProduitDaoImpl pdao;

    @Override
    public void init() throws ServletException {
        pdao = new ProduitDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idProduit = request.getParameter("idProduit");
        if (idProduit != null) {
            Long id = Long.parseLong(idProduit);
            pdao.deleteProduit(id);
        }
        response.sendRedirect("produits"); // Redirect back to the products list
    }
}
