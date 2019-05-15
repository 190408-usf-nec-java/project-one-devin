package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Expense;
import com.revature.beans.User;
import com.revature.daos.ManagerDao;
import com.revature.daos.UserDao;

public class ExpenseService {
	
	private List<Expense> expenses = new ArrayList<Expense>();
	
	UserDao dao = new UserDao();
	ManagerDao mDao = new ManagerDao();
	
	public void createExpense(Expense expense) {
		dao.AddTicketByUser(expense);
	}

	public void getUserExpenses(User user) {
		if(user.getRole().contentEquals("1")) {
			expenses = mDao.getAllTickets();
		}else {
			expenses = dao.RetrievePastTicketsByUser(user.getId());
		}
		expenses.forEach( expense -> {
			String[] temp = expense.getSubmittedAt().split("-");
			expense.setSubmittedAt(temp[1] + "/" + temp[2]+ "/"+ temp[0]);
			if(!expense.getResolvedAt().contentEquals("N/A")) {
				String[] temp2 = expense.getResolvedAt().split("-");
				expense.setResolvedAt(temp2[1] + "/" + temp2[2]+ "/"+ temp2[0]);
			}
		});
		
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenseStatus(Expense ex) {
		System.out.println("status: " + ex.getStatus());
		boolean isApproved;
		if(ex.getStatus().contentEquals("approved")) {
			isApproved = true;
		} else {
			isApproved = false;
		}
		ex.setStatus(mDao.ApproveOrDenyTicket(isApproved, ex.getExpenseID()));
	}
	
}
