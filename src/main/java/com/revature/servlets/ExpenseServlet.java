package com.revature.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Expense;
import com.revature.beans.User;
import com.revature.services.ExpenseService;

public class ExpenseServlet extends DefaultServlet{
	ExpenseService exService = new ExpenseService();
	private static final long serialVersionUID = 1L;
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.addHeader("Access-Control-Allow-Headers", "content-type");
		response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.addHeader("Access-Control-Allow-Credentials", "true");
		super.service(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		System.out.println(req.getCookies());
		User user = new User();
		if(cookies != null) {
			for(int i=0; i < cookies.length; i++) {
				if(cookies[i].getName().contentEquals("role")) {
					user.setRole(cookies[i].getValue());
				}
				if(cookies[i].getName().contentEquals("id")) {
					user.setId(Integer.parseInt(cookies[i].getValue()));
				}
			}
		}else {
			System.out.println("error");
		}
		/*HttpSession session = req.getSession();
		user.setRole(session.getAttribute("role").toString());
		user.getCredentials().setUsername(session.getAttribute("username").toString());*/
		exService.getUserExpenses(user);
		List<Expense> expenses = exService.getExpenses();
		ObjectMapper om = new ObjectMapper();
		om.writeValue(resp.getOutputStream(), expenses);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ObjectMapper om = new ObjectMapper();
		Expense ex = om.readValue(request.getInputStream(), Expense.class);
		exService.setExpenseStatus(ex);
	}
}
