package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import extra.PasswordHasher;
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
	
	public boolean registerUser(User user) {
        try {
            query = "INSERT INTO Utente (nome, email, password, indirizzo, data_nascita, sesso, cognome) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pst = this.con.prepareStatement(query);
            pst.setString(1, user.getNome());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getIndirizzo());
            pst.setString(5, user.getData_nascita());
            pst.setString(6, user.getSesso());
            pst.setString(7, user.getCognome());

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
            return false;
        }
    }
}
