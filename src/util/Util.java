package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

public class Util {
	public static String convertTimeToString(Date date) {
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		return df.format(date);
	}

	public static String hash(String password){
	    String sha1 = "";
	    try{
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(password.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    } catch(NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    } catch(UnsupportedEncodingException e){
	        e.printStackTrace();
	    }
	    return sha1;
	}

	private static String byteToHex(final byte[] hash){
	    Formatter formatter = new Formatter();
	    for (byte b : hash){
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}	
}
