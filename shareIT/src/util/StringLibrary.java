package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringLibrary {
	public String md5(String str){
		String result = null;
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(str.getBytes());
				BigInteger bigInteger  = new BigInteger(1, md.digest());
				result = bigInteger.toString(16);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return result;
	}
}
 
  