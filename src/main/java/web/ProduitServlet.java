package web;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import Metier.Produit;
import dao.ProduitDaoImpl;

@WebServlet(name = "ps", urlPatterns = {"/produits"})
public class ProduitServlet extends HttpServlet {
    private ProduitDaoImpl pdao;

    @Override
    public void init() throws ServletException {
        pdao = new ProduitDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Produit> prods = pdao.produits();

        request.setAttribute("prods", prods);
        request.getRequestDispatcher("produits.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    
}
