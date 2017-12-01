package util;

import java.security.SecureRandom;
import util.Hash;

public class Token {

	public static String create(){
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[256];
		random.nextBytes(bytes);
		String token = bytes.toString();
	    return Hash.sha1(token);
	}
}
