package com.revature.daos;

import java.util.List;

import com.revature.beans.Expense;
import com.revature.beans.User;

public class ManagerDao {
	
	/**
	 * Accesses the database and returns a list of all expense tickets for all users.
	 * @throws
	 * @return Returns a linked list of all expense tickets for all users
	 */
	public List<Expense> ViewAllTickets(){
		
		return null;
	}
	
	/**
	 * Takes in a user and a boolean and changes the provided ticket in the database.
	 * True = Approved
	 * False = Denied
	 * 
	 * @param user
	 * @param isApproved
	 * @param expense
	 */
	public void ApproveOrDenyTicket(User user, boolean isApproved, Expense expense){
		
	}
}
