package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
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
}
