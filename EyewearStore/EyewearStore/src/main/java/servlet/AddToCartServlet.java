package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Cart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF=8");
		
		try (PrintWriter out = response.getWriter()){
			ArrayList<Cart> cartList = new ArrayList<>();
			
			int id = Integer.parseInt(request.getParameter("id"));
			double p = Double.parseDouble(request.getParameter("prezzo"));
			Cart c = new Cart();
			c.setId(id);
			c.setQuantity(1);
			c.setPrezzo(p);
			
			HttpSession session = request.getSession();
			String contextPath = request.getContextPath();
			ArrayList<Cart> cartListFromSession = (ArrayList<Cart>) session.getAttribute("cart-list");
			if (cartListFromSession == null) {
				cartList.add(c);
				session.setAttribute("cart-list", cartList); //mettiamo CartList creata all'inizio, non quella della sessione
				response.sendRedirect(contextPath + "/pages/index.jsp");
			}else {
				cartList = cartListFromSession;
				boolean exist = false;
				
				for(Cart x: cartListFromSession) {
					if(x.getId() == id) {
						exist = true;
						out.println("<h3 style='color:crimson; text-align:center'>Prodotto già nel carrello."
								+ "<a href='"+contextPath+"/pages/cart.jsp'>"
								+ "Vai al carrello</a></h3>");
						
					}
				}
				if(!exist) {
					cartList.add(c);
					response.sendRedirect(contextPath + "/pages/index.jsp");
				}
			}
		}
	}
}
