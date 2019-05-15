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
	public User login(Credentials cred) throws HttpException{
		
		/*char[] pwd = "iamtheadminbro".toCharArray();
		Credentials testCred = new Credentials("DevinStuart", pwd, passwordUtil.hashPassword(pwd));
		User user = new User(testCred, "Devin", "Stuart", "devin.stuart740@gmail.com", "1", 0);
		try {
			userDao.addUser(user);
		}catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		User user = null;
		try {
			user = userDao.getUser(cred.getUsername());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new HttpException(500);
		}
		cred.setHashedPass(passwordUtil.hashPassword(cred.getPassword()));
		
		if(user.getCredentials().getHashedPass().equalsIgnoreCase("")) {
			throw new HttpException(400); // bad request
		}
		if(cred.getHashedPass().contentEquals(user.getCredentials().getHashedPass())) {
			return user;
		}else {
			throw new HttpException(400);
		}
		
	}
}
