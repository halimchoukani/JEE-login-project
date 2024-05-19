package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import Metier.Produit;
import dao.ProduitDaoImpl;

/**
 * Servlet implementation class ajouterproduit
 */
@WebServlet(urlPatterns = {"/addproduct"})
public class ajouterproduitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ajouterproduitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("ajouterproduit.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProduitDaoImpl pdao = new ProduitDaoImpl();
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        Produit newProduct = new Produit(name, price);
        pdao.save(newProduct);
        response.sendRedirect(request.getContextPath() + "/produits");
    }

}
