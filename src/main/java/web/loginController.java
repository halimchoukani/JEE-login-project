package web;

import dao.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import Metier.Produit;
import Metier.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(name = "ms", urlPatterns = {"/login","*.do"})
public class loginController extends HttpServlet {
	
	
	
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     */
    public loginController() {
        super();
    }

    /**
     * Handles the HTTP POST method.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		
    		String login = request.getParameter("login");
            String mdp = request.getParameter("mdp");
            User user = new User();
            user.setLogin(login);
            user.setMp(mdp);
            UserDaoImpl userDao = new UserDaoImpl();
            if (userDao.login(user)) {
            	response.sendRedirect("produits");
            } else {
                request.setAttribute("msg", "incorrect");
                response.sendRedirect("login");
            }
            
    }

    /**
     * Handles the HTTP GET method. Optionally implemented if necessary.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
