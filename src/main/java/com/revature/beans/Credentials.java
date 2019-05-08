package com.revature.beans;

import java.util.Arrays;

public class Credentials {
	private String username;
	private char[] password;
	private String hashedPass;
	
	public Credentials() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Credentials(String username, char[] password, String hashedPass) {
		super();
		this.username = username;
		this.password = password;
		this.hashedPass = hashedPass;
	}
	@Override
	public String toString() {
		return "Credentials [username=" + username + ", password=" + Arrays.toString(password) + ", hashedPass="
				+ hashedPass + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hashedPass == null) ? 0 : hashedPass.hashCode());
		result = prime * result + Arrays.hashCode(password);
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credentials other = (Credentials) obj;
		if (hashedPass == null) {
			if (other.hashedPass != null)
				return false;
		} else if (!hashedPass.equals(other.hashedPass))
			return false;
		if (!Arrays.equals(password, other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public char[] getPassword() {
		return password;
	}
	public void setPassword(char[] password) {
		this.password = password;
	}
	
	public void setHashedPass(String password) {
		this.hashedPass = password;
	}
	public String getHashedPass() {
		return this.hashedPass;
	}

}
