package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import Metier.User;
import dao.UserDaoImpl;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet(name = "register", urlPatterns = {"/register"})
public class registerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public registerServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		
    		String login = request.getParameter("login");
            String mdp = request.getParameter("mdp");
            User user = new User();
            user.setLogin(login);
            user.setMp(mdp);
            UserDaoImpl userDao = new UserDaoImpl();
            if (userDao.register(user) != null) {
            	response.sendRedirect("produits");
            } else {
                request.setAttribute("msg", "incorrect");
                response.sendRedirect("register");
            }
            
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
}
