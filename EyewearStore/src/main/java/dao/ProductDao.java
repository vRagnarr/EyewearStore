package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
				row.setMarca(rs.getString("marca"));
				row.setPrezzo(rs.getString("prezzo"));
				row.setImage(rs.getString("image"));
				row.setSesso(rs.getString("sesso"));
				row.setQuantità(rs.getInt("quantita"));
				
				p.add(row);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return p;
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
						row.setMarca(rs.getString("marca"));
						row.setPrezzo(rs.getString("prezzo"));
						row.setImage(rs.getString("image"));
						row.setSesso(rs.getString("sesso"));
						row.setQuantità(rs.getInt("quantita"));
						products.add(row);
					}
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return products;
	}
}
