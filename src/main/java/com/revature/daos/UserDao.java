package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
			String sql = "SELECT user_id, user_password, user_first_name," + 
					"user_last_name, user_email, user_role FROM users WHERE user_name = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				hashedPass = rs.getString("user_password");
				user.setId(rs.getInt("user_id"));
				user.setRole(rs.getString("user_role"));
				user.setFirstname(rs.getString("user_first_name"));
				user.setLastname(rs.getString("user_last_name"));
				user.setEmail(rs.getString("user_email"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
		cred.setHashedPass(hashedPass);
		cred.setUsername(username);
		user.setCredentials(cred);
		return user;
	}
	
	
	public User getUserById(int id) throws SQLException{
		String hashedPass = "";
		User user = new User();
		String username="";
		Credentials cred = new Credentials();
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT user_name, user_password, user_first_name," + 
					"user_last_name, user_email, user_role FROM users WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				hashedPass = rs.getString("user_password");
				username = rs.getString("user_name");
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
	public List<Expense> RetrievePastTicketsByUser(int id){
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<Expense> expenses = new ArrayList<Expense>();
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "select r.re_id as expenseID, r.re_author as authorID, r.re_amount as amount, r.re_submitted_at as submittedAt, "
					+ "r.re_resolver as resolver, r.re_resolved_at as resolvedAt, "
					+ "r.re_reciept as link, u.user_name as username, r.re_description "
					+ "as description, s.status_label as status, t.type_label as type "
					+ "from reimbursements r join status s on r.re_status = s.status_id "
					+ "inner join users u on u.user_id = r.re_author"
					+" inner join re_types t on r.re_type = t.type_id where r.re_author = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			System.out.println(id);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Expense expense = new Expense();
				expense.setExpenseID(rs.getInt("expenseid"));
				expense.setAmount(rs.getDouble("amount"));
				expense.setAuthor(getUser(rs.getString("username")));
				expense.setAuthorId(rs.getInt("authorid"));
				expense.setReceipt(rs.getString("link"));
				expense.setStatus(rs.getString("status"));
				expense.setDesciption(rs.getString("description"));
				String temp[] = rs.getString("submittedat").split(" ");
				expense.setSubmittedAt(temp[0]);
				if(rs.getString("resolvedat") != null) {
					String temp2[] = rs.getString("resolvedat").split(" ");
					expense.setResolvedAt(temp2[0]);
				} else {
					expense.setResolvedAt("N/A");
				}
				if(getUser(rs.getString("resolver")) == null) {
					expense.setResolver(new User(new Credentials("","".toCharArray(),
							""),"","","","", 0));//setting empty user
				}else {
					expense.setResolver(getUser(rs.getString("resolver")));
				}
				expense.setType(rs.getString("type"));
				expenses.add(expense);
			}
		}catch(SQLException E) {
			E.printStackTrace();
		}
		System.out.println(expenses.isEmpty());
		return expenses;
	}
	public void AddTicketByUser(Expense expense){
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "insert into reimbursements (re_amount, re_author, re_description, "
					+ "re_submitted_at, re_status, re_type) values "
					+ "(?, ?, ?, current_timestamp, 1, ?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, expense.getAmount());
			ps.setInt(2, expense.getAuthorId());
			ps.setString(3, expense.getDesciption());
			//ps.setString(4, expense.getReceipt());
			int type;
			if(expense.getType().contentEquals("Lodging")) {
				//Lodging Travel Food Other
				type = 1;
			}else if(expense.getType().contentEquals("Travel")) {
				type = 2;
			}else if(expense.getType().contentEquals("Food")) {
				type = 3;
			}else {
				type = 4;
			}
			ps.setInt(4, type);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}


}
