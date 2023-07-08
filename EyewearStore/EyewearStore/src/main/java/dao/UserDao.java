package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

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
				user.setId(rs.getInt("id"));
				user.setNome(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
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
            query = "INSERT INTO Utente (name, cognome, indirizzo, data_nascita, sesso, email, password) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pst = this.con.prepareStatement(query);
            pst.setString(1, user.getNome());
            pst.setString(2, user.getCognome());
            pst.setString(3, user.getIndirizzo());
            pst.setString(4, user.getData_nascita());
            pst.setString(5, user.getSesso());
            pst.setString(6, user.getEmail());
            pst.setString(7, user.getPassword());

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
            return false;
        }
    }
	
	public List<User> getAllUsersExceptAdmin() {
	    List<User> userList = new ArrayList<>();
	    try {
	        query = "SELECT * FROM Utente WHERE email <> 'admin@admin.com'";
	        pst = this.con.prepareStatement(query);
	        rs = pst.executeQuery();

	        while (rs.next()) {
	            User user = new User();
	            user.setId(rs.getInt("id"));
	            user.setNome(rs.getString("name"));
	            user.setEmail(rs.getString("email"));
	            user.setPassword(rs.getString("password"));
	            user.setCognome(rs.getString("cognome"));
	            user.setIndirizzo(rs.getString("indirizzo"));
	            user.setSesso(rs.getString("sesso"));
	            user.setData_nascita(rs.getString("data_nascita"));
	            userList.add(user);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.print(e.getMessage());
	    }
	    return userList;
	}


    public void deleteUser(int userId) {
        String sql = "DELETE FROM Utente WHERE id = ?";

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, userId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Non è stato possibile eliminare l'utente con ID: " + userId);
            } else {
                System.out.println("Utente eliminato con successo!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
}
