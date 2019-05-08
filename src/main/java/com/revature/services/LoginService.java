package com.revature.services;

import java.sql.SQLException;

import com.revature.beans.Credentials;
import com.revature.beans.User;
import com.revature.daos.UserDao;
import com.revature.util.HttpException;
import com.revature.util.passwordUtil;

public class LoginService {
	private UserDao userDao = new UserDao();
	
	/**
	 * Checks login credentials against the database
	 * @param credentials object
	 * @return true if credentials are correct, false if not
	 * @throws HttpException(500) if database error
	 */
	public boolean login(Credentials cred) throws HttpException{
		
		/*char[] pwd = "hamsterparty".toCharArray();
		Credentials testCred = new Credentials("MontyPython", pwd, passwordUtil.hashPassword(pwd));
		User user = new User(testCred, "Monty", "Python", "monty@python.com", "1");
		try {
			userDao.addUser(user);
		}catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		
		
		Credentials checkCred=null;
		try {
			checkCred = userDao.GetCredentialsByUser(cred.getUsername());
		} catch (SQLException e) {
			throw new HttpException(500);
		}
		cred.setHashedPass(passwordUtil.hashPassword(cred.getPassword()));
		
		if(checkCred.getHashedPass().equalsIgnoreCase("")) {
			throw new HttpException(400); // bad request
		}
		System.out.println(cred.getHashedPass());
		System.out.println(checkCred.getHashedPass());
		
		if(cred.getHashedPass().equals(checkCred.getHashedPass())) {
			return true;
		}else {
			throw new HttpException(400);
		}
		
	}
}
