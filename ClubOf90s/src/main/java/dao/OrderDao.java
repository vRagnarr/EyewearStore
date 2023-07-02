package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import model.Order;
import model.Product;

public class OrderDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public OrderDao(Connection con) {
		this.con = con;
	}
	
	public boolean insertOrder(Order model) {
        boolean result = false;
        try {
            query = "insert into Ordine (id, utente_email, data_ordine, stato,quantita) values(?,?,?,?,?)";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, model.getId()); //id del prodotto
            pst.setString(2, model.getUserId());
            pst.setDate(3, model.getDate());
            pst.setString(4, "P");
            pst.setInt(5, model.getQuantity());
            pst.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
	

    public List<Order> userOrders(String id) {
        List<Order> list = new ArrayList<>();
        try {
            query = "select * from Ordine where utente_email=? order by data_ordine desc";
            pst = this.con.prepareStatement(query);
            pst.setString(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                ProductDao productDao = new ProductDao(this.con);
                int pId = rs.getInt("id");
                Product product = productDao.getSingleProduct(pId);
                order.setOrderId(rs.getInt("id"));
                order.setId(pId);
                order.setNome(product.getNome());
                order.setMarca(product.getMarca());
                order.setPrezzo(Integer.parseInt(product.getPrezzo())*rs.getInt("quantita")+"");
                order.setQuantity(rs.getInt("quantita"));
                order.setDate(rs.getDate("data_ordine"));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void cancelOrder(int id) {
        //boolean result = false;
        try {
            query = "delete from Ordine where id=?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            pst.execute();
            //result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        //return result;
    }
}
