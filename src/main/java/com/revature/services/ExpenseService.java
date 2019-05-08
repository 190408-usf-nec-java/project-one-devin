package com.revature.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.revature.beans.Expense;

public class ExpenseService {
	
	Map<Integer, Expense> expenses = new HashMap<>();
	
	{
		expenses.put(1, new Expense());
	}
	private int counter=1;
	public Optional<Expense> getExpense(int id) {
		
		return Optional.ofNullable(expenses.get(id));
		
	}

	public void createExpense(Expense expense) {
		expenses.put(++counter, expense);
		
	}
}
