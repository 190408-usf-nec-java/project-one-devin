package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Expense;
import com.revature.daos.UserDao;

public class CreateServlet extends DefaultServlet{
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.addHeader("Access-Control-Allow-Headers", "content-type");
		response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("content-type", "application/json");
		super.service(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ObjectMapper om = new ObjectMapper();
		Expense ex = om.readValue(request.getInputStream(), Expense.class);
		ex.setAuthorId(ex.getAuthorId());
		UserDao uDao = new UserDao();
		uDao.AddTicketByUser(ex);
	}
}
