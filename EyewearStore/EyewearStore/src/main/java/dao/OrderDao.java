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
            query = "insert into Ordine (prodotto_id, utente_id, quantità, data_ordine, stato, valore) values(?,?,?,?,?,?)";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, model.getId()); //id del prodotto
            pst.setInt(2, model.getUserId());
            pst.setInt(3, model.getQuantity());
            pst.setString(4, model.getDate());
            pst.setString(5, model.getStato());
            pst.setDouble(6, model.getValore());
            pst.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
	

    public List<Order> userOrders(int id) {
        List<Order> list = new ArrayList<>();
        try {
            query = "select * from Ordine where utente_id=? order by data_ordine desc";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                ProductDao productDao = new ProductDao(this.con);
                int pId = rs.getInt("prodotto_id");
                Product product = productDao.getSingleProduct(pId);
                order.setOrderId(rs.getInt("ordine_id"));
                order.setId(pId);
                order.setUserId(rs.getInt("utente_id"));
                order.setNome(product.getNome());
                order.setValore(rs.getDouble("valore"));
                order.setQuantity(rs.getInt("quantità"));
                order.setDate(rs.getString("data_ordine"));
                order.setStato(rs.getString("stato"));
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
            query = "delete from Ordine where ordine_id=?";
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
    
    public List<Order> getAllOrders() {
        List<Order> list = new ArrayList<>();
        try {
            query = "select * from Ordine";
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                ProductDao productDao = new ProductDao(this.con);
                int pId = rs.getInt("prodotto_id");
                Product product = productDao.getSingleProduct(pId);
                order.setOrderId(rs.getInt("ordine_id"));
                order.setId(pId);
                order.setUserId(rs.getInt("utente_id"));
                order.setNome(product.getNome());
                order.setValore(rs.getDouble("valore"));
                order.setQuantity(rs.getInt("quantità"));
                order.setDate(rs.getString("data_ordine"));
                order.setStato(rs.getString("stato"));
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public Order getOrderById(int orderId) {
        String sql = "SELECT * FROM Ordine WHERE ordine_id = ?";
        Order order = null;

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, orderId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = new Order();
                order.setOrderId(resultSet.getInt("ordine_id"));
                order.setUserId(resultSet.getInt("utente_id"));
                order.setId(resultSet.getInt("prodotto_id"));
                order.setQuantity(resultSet.getInt("quantità"));
                order.setValore(resultSet.getDouble("valore"));
                order.setStato(resultSet.getString("stato"));
                order.setDate(resultSet.getString("data_ordine"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }
    

    public void updateOrder(Order order) {
        String sql = "UPDATE Ordine SET stato = ? WHERE ordine_id = ?";

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, order.getStato());
            statement.setInt(2, order.getOrderId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Non è stato possibile aggiornare l'ordine con ID: " + order.getOrderId());
            } else {
                System.out.println("Ordine aggiornato con successo!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
