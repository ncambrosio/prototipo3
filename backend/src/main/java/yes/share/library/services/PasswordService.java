package yes.share.library.services;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Random;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Ver {@link https://www.owasp.org/index.php/Hashing_Java}
 * 
 * @author noeca
 *
 */
public class PasswordService {

	public static byte[] hashPassword( final char[] password ) {
		
		//byte[] salt = hexStringToByteArray("e04fd020ea3a6910a2d808002b30309d");
		
		final Random r = new SecureRandom();
		byte[] salt = new byte[32];
		r.nextBytes(salt);
		
		int iterations = 10;
		int keyLength = 256;
		
		return PasswordService.hashPassword(password, salt, iterations, keyLength);
	}
	
	public static byte[] hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {
	   
       try {
           SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
           PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
           SecretKey key = skf.generateSecret( spec );
           byte[] res = key.getEncoded( );
           return res;
 
       } catch( NoSuchAlgorithmException | InvalidKeySpecException e ) {
           throw new RuntimeException( e );
       }
	}
	
	private static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
}
