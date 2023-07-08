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


public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	try(PrintWriter out = response.getWriter()){
    		String email = request.getParameter("email");
            String password = PasswordHasher.hashPassword(request.getParameter("password"));
            System.out.println(password);
            UserDao userDao = new UserDao(DBConnection.getConnection());
            User user;
            if(!email.equals("admin@admin.com")) {
            	user = userDao.userLogin(email, password);
            }else {
            	if(request.getParameter("password").equals("EyewearAdmin")) {
            		user = new User();
            		user.setNome("admin");
            	}else
            		user = null;
            }
            if(user != null) {
            	request.getSession().setAttribute("auth", user);
            	String contextPath = request.getContextPath();
            	if(user.getNome().equals("admin"))
            		response.sendRedirect(contextPath+"/pages/admin/amministrazione.jsp");
            	else
            		response.sendRedirect(contextPath + "/pages/index.jsp");

            }else {
            	out.print("user login failed");
            }
    	}
    }
}