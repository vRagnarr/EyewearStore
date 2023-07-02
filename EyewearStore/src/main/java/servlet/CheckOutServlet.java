package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import dao.OrderDao;
import model.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.DBConnection;


public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()){
			
			String contextPath = request.getContextPath();
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			
			User user = (User) request.getSession().getAttribute("auth");
			if(cart_list != null && user != null) {
				
				for(Cart c: cart_list) {
					Order order = new Order();
					order.setId(c.getId());
					order.setUserId(user.getId());
					order.setQuantity(c.getQuantity());
					order.setDate(Date.valueOf(LocalDate.now())+"");
					order.setStato("Ordinato");
					order.setValore(c.getQuantity()*c.getPrezzo());
					OrderDao orderDao = new OrderDao(DBConnection.getConnection());
					boolean res = orderDao.insertOrder(order);
					if(!res) break;
				}
				cart_list.clear();
				response.sendRedirect(contextPath+"/pages/orders.jsp");
				
			}else if(user == null) {
				response.sendRedirect(contextPath+"/pages/signupLogin.jsp");
			}else
				response.sendRedirect(contextPath+"/pages/cart.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
