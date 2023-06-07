package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;

public class UserDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public UserDao(Connection con) {
		this.con = con;
	}
	
	public User userLogin(String email, String password) {
		User user = null;
		try {
			query = "select * from Utente where email=? and password=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, password);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				user = new User();
				user.setNome(rs.getString("nome"));
				user.setCognome(rs.getString("cognome"));
				user.setIndirizzo(rs.getString("indirizzo"));
				user.setSesso(rs.getString("sesso"));
				user.setData_nascita(rs.getString("data_nascita"));
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		return user;
	}
}
