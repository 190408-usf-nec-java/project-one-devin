package com.revature.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import com.revature.beans.Credentials;

public class passwordUtil {

	public static String hashPassword(char[] password) {
		
		Credentials cred = new Credentials();
		
        String hashedPassword = genSecurePassword(password);
		return hashedPassword;
	}
	private static String genSecurePassword(char[] passwordToHash)
    {
        String generatedPassword = null;
        
        try {
            MessageDigest SHA256 = MessageDigest.getInstance("SHA-256");
            StringBuilder sob = new StringBuilder();
            for(int i=0; i< passwordToHash.length; i++) {
            	sob.append(passwordToHash[i]);
            }
            byte[] bytes = SHA256.digest(sob.toString().getBytes());
            StringBuilder sb = new StringBuilder();
            
            for(int i=0; i< bytes.length; i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
	
}
