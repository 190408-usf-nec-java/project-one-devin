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

public class ManagerDao {
	
	UserDao userDao = new UserDao();
	/**
	 * Accesses the database and returns a list of all expense tickets for all users.
	 * @throws
	 * @return Returns a linked list of all expense tickets for all users
	 */
	public List<Expense> getAllTickets() {
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
					+" inner join re_types t on r.re_type = t.type_id;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Expense expense = new Expense();
				System.out.println(rs.getString(1));
				expense.setExpenseID(rs.getInt("expenseid"));
				expense.setAmount(rs.getDouble("amount"));
				expense.setAuthor(userDao.getUser(rs.getString("username")));
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
				if(userDao.getUser(rs.getString("resolver")) == null) {
					expense.setResolver(new User(new Credentials("","".toCharArray(),
							""),"","","","", 0));//setting empty user
				}else {
					expense.setResolver(userDao.getUser(rs.getString("resolver")));
				}
				expense.setType(rs.getString("type"));
				System.out.println(expense.getSubmittedAt());
				expenses.add(expense);
			}	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println(expenses);
		return expenses;
		
	}
	/**
	 * Takes in a user and a boolean and changes the provided ticket in the database.
	 * True = Approved
	 * False = Denied
	 * null = Pending
	 * 
	 * 
	 * @param isApproved
	 * @param expense
	 */
	public String ApproveOrDenyTicket(boolean isApproved, int exId){
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("expense ID = " + exId);
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE reimbursements SET re_status = ? where re_id = ?;"
					+ "UPDATE reimbursements SET re_resolved_at = current_timestamp where re_id = ?;";
			String exStatus;
			int status;
			PreparedStatement ps = conn.prepareStatement(sql);
			if(isApproved) {
				status = 2;
				exStatus = "approved";
			}else {
				status = 3;
				exStatus = "denied";
			}
			ps.setInt(1, status);
			ps.setInt(2, exId);
			ps.setInt(3, exId);
			ps.executeUpdate();
			return exStatus;
		}catch(SQLException e) {
			e.printStackTrace();
			return "pending";
		}
	}
}
