package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import model.Cart;
import model.Product;

public class ProductDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ProductDao(Connection con) {
		this.con = con;
	}
	
	public List<Product> getProducts(){
		List<Product> p = new ArrayList<Product>();
		
		try {
			query = "SELECT * FROM Prodotto";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			while(rs.next()) {
				Product row = new Product();
				row.setId(rs.getInt("id"));
				row.setNome(rs.getString("nome"));
				row.setBrand(rs.getString("brand"));
				row.setPrezzo(rs.getDouble("prezzo"));
				row.setImage(rs.getString("immagine"));
				row.setSesso(rs.getString("sesso"));
				row.setModello(rs.getString("modello"));
				row.setData_Inserimento(rs.getString("data_inserimento"));
				p.add(row);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return p;
	}
	
	 public Product getSingleProduct(int id) {
		 Product row = null;
	        try {
	            query = "select * from Prodotto where id=? ";

	            pst = this.con.prepareStatement(query);
	            pst.setInt(1, id);
	            ResultSet rs = pst.executeQuery();

	            while (rs.next()) {
	            	row = new Product();
	                row.setId(rs.getInt("id"));
	                row.setNome(rs.getString("nome"));
	                row.setBrand(rs.getString("brand"));
	                row.setPrezzo(rs.getDouble("prezzo"));
	                row.setImage(rs.getString("immagine"));
	                row.setSesso(rs.getString("sesso"));
	                row.setModello(rs.getString("modello"));
					row.setData_Inserimento(rs.getString("data_inserimento"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }

	        return row;
	    }
	
	public List<Cart> getCartProducts(ArrayList<Cart> cartList){
		List<Cart> products = new ArrayList<Cart>();
		
		try {
			if(cartList.size() > 0) {
				for(Cart item: cartList) {
					query = "select * from Prodotto where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					while(rs.next()) {
						Cart row = new Cart();
						row.setId(rs.getInt("id"));
						row.setNome(rs.getString("nome"));
						row.setBrand(rs.getString("brand"));
						row.setPrezzo(rs.getDouble("prezzo"));
						row.setImage(rs.getString("immagine"));
						row.setSesso(rs.getString("sesso"));
						row.setModello(rs.getString("modello"));
						row.setData_Inserimento(rs.getString("data_inserimento"));
						row.setQuantity(item.getQuantity());
						products.add(row);
					}
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return products;
	}
	
	public String getTotalCartPrice(ArrayList<Cart> cartList) {
		double d = 0.00;
		try {
			if(cartList.size() > 0) {
				for (Cart c: cartList) {
					query = "select prezzo from Prodotto where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, c.getId());
					rs = pst.executeQuery();
					
					while(rs.next()) {
						d += rs.getDouble("prezzo")*c.getQuantity();
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return d+"";
	}
	
	public void addProduct(Product product) {
	    String sql = "INSERT INTO Prodotto (nome, brand, modello, prezzo, data_inserimento, sesso) VALUES (?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement statement = con.prepareStatement(sql)) {
	        statement.setString(1, product.getNome());
	        statement.setString(2, product.getBrand());
	        statement.setString(3, product.getModello());
	        statement.setDouble(4, product.getPrezzo());
	        statement.setString(5, product.getData_Inserimento());
	        statement.setString(6, product.getSesso());

	        int rowsAffected = statement.executeUpdate();
	        if (rowsAffected == 0) {
	            System.out.println("Non è stato possibile aggiungere il prodotto: " + product.getNome());
	        } else {
	            System.out.println("Prodotto aggiunto con successo!");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	
	public void updateProduct(Product product) {
        String sql = "UPDATE Prodotto SET prezzo = ? WHERE id = ?";

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setDouble(1, product.getPrezzo());
            statement.setInt(2, product.getId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Non è stato possibile aggiornare il prodotto con ID: " + product.getId());
            } else {
                System.out.println("Prodotto aggiornato con successo!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void deleteProduct(int productId) {
        String sql = "DELETE FROM Prodotto WHERE id = ?";

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, productId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Non è stato possibile eliminare il prodotto con ID: " + productId);
            } else {
                System.out.println("Prodotto eliminato con successo!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
