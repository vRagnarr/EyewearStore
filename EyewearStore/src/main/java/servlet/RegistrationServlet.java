package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import connection.DBConnection;
import dao.UserDao;
import model.User;
import extra.PasswordHasher;

public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            String nome = request.getParameter("nome");
            String cognome = request.getParameter("cognome");
            String email = request.getParameter("email");
            String indirizzo = request.getParameter("indirizzo");
            String password = request.getParameter("password");
            String dataNascita = request.getParameter("data_nascita");
            String sesso = request.getParameter("sesso");


            // Inserimento dei dati nel database

            UserDao userDao = new UserDao(DBConnection.getConnection());
            User newUser = new User(nome, cognome, email, indirizzo, PasswordHasher.hashPassword(password),
                    dataNascita, sesso);

            if (userDao.registerUser(newUser)) {
                String contextPath = request.getContextPath();
                response.sendRedirect(contextPath + "/pages/index.jsp");
            } else {
                out.print("Registrazione utente fallita");
            }
        }
    }
}
