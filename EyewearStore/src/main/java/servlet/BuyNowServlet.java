package servlet;

import java.io.IOException;
import dao.OrderDao;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;

import model.User;
import model.Order;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.DBConnection;

public class BuyNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()) {
			String contextPath = request.getContextPath();
			
			User auth = (User) request.getSession().getAttribute("auth");
			if(auth != null) {
				
				int id = Integer.parseInt(request.getParameter("id"));
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				
				Order order = new Order();
				order.setId(id);
				order.setQuantity(quantity);
				order.setUserId(auth.getId());
				order.setDate(Date.valueOf(LocalDate.now())+"");
				order.setStato("Ordinato");
				order.setValore(quantity*(Double.parseDouble(request.getParameter("prezzo"))));
				
				OrderDao orderDao = new OrderDao(DBConnection.getConnection());
				boolean res = orderDao.insertOrder(order);
				if(res) {
					response.sendRedirect(contextPath+"/pages/userpage.jsp");
				}
				
			}else {
				response.sendRedirect(contextPath+"/pages/signupLogin.jsp");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
