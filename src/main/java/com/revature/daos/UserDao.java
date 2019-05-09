package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Credentials;
import com.revature.beans.Expense;
import com.revature.beans.User;
import com.revature.util.ConnectionUtil;
import com.revature.util.passwordUtil;

public class UserDao {
	/**
	 * Returns a credentials object with the hashed password of the username provided.
	 * throws an SQLException if something goes amiss.
	 * @param username
	 * @return Credentials Object
	 * @throws SQLException
	 */
	public User getUser(String username) throws SQLException{
		String hashedPass = "";
		User user = new User();
		Credentials cred = new Credentials();
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT user_password, user_first_name," + 
					"user_last_name, user_email, user_role FROM users WHERE user_name = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				hashedPass = rs.getString("user_password");
				user.setRole(rs.getString("user_role"));
				user.setFirstname(rs.getString("user_first_name"));
				user.setLastname(rs.getString("user_last_name"));
				user.setEmail(rs.getString("user_email"));
			}
		}catch(SQLException e) {
			throw new SQLException();
		}
		cred.setHashedPass(hashedPass);
		cred.setUsername(username);
		user.setCredentials(cred);
		return user;
	}
	
	public void addUser(User user) throws SQLException { 
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO users(user_name, user_password, user_first_name, "
					+"user_last_name, user_email, user_role) VALUES (?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getCredentials().getUsername());
			ps.setString(2, user.getCredentials().getHashedPass());
			ps.setString(3, user.getFirstname());
			ps.setString(4, user.getLastname());
			ps.setString(5, user.getEmail());
			ps.setInt(6, Integer.parseInt(user.getRole()));
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
	}
	public List<Expense> RetrievePastTicketsByUser(User user){
		return null;
	}
	public void AddTicketByUser(User user, Expense expense){
		
	}


}
