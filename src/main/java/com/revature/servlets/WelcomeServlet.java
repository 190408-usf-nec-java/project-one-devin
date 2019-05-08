package com.revature.servlets;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

import com.revature.beans.Expense;
import com.revature.services.ExpenseService;

public class WelcomeServlet extends DefaultServlet{
	ExpenseService exService = new ExpenseService();
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() {
		System.out.println("Expense servlet is initialising.");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Service is being called");
		super.service(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*System.out.println("doGet() being called.");
		System.out.println(req.getRequestURL());
		System.out.println(req.getRequestURI());
		System.out.println(req.getPathInfo());
		int id = Integer.parseInt(req.getPathInfo().split("/")[1]);
		System.out.println(id);
		Optional<Expense> expense = exService.getExpense(id);
		if(expense.isPresent()) {
			resp.getWriter().write(expense.get().toString());
		}else {
			resp.setStatus(404);
		}*/
		resp.getWriter().write("Don't drink the water");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost() being called.");
		
		/*double amount = Double.parseDouble(req.getParameter("Expense Amount"));
		int employeeID = Integer.parseInt(req.getParameter("Employee ID"));
		int expenseID = Integer.parseInt(req.getParameter("Expense ID"));
		Expense expense = new Expense(employeeID, amount, expenseID);
		exService.createExpense(expense);*/
		resp.getWriter().write("Don't drink the water");
	}

}
