package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Credentials;
import com.revature.beans.User;
import com.revature.services.LoginService;
import com.revature.util.HttpException;

public class LoginServlet extends DefaultServlet{

		
	LoginService loginService = new LoginService();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.addHeader("Access-Control-Allow-Headers", "content-type");
		response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		super.service(request, response);
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.getWriter().write("Yo yoy yo");
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		System.out.println("We did it");
		ObjectMapper om = new ObjectMapper();
		Credentials cred = om.readValue(req.getInputStream(), Credentials.class);
		User user = null;
		try {
			user = this.loginService.login(cred);
		} catch (HttpException e) {
			resp.setStatus(e.getErrorCode());
			return;
		}
		Cookie ussrCookie = new Cookie("username", cred.getUsername());
		Cookie roleCookie = new Cookie("role", user.getRole());
		resp.addCookie(ussrCookie);
		resp.addCookie(roleCookie);
		om.writeValue(resp.getOutputStream(), user);
		
		
		//HttpSession session = req.getSession();
		
		//session.setAttribute("id", id);	
	}
}
