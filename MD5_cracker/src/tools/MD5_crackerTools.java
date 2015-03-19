package tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.MessageDigestSpi;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author wes
 */
public class MD5_crackerTools {
    
    public static byte[] getMD5bytes (String MD5input) {
        byte[] MD5bytes = new byte[0];
        try {
            MessageDigest mDig = MessageDigest.getInstance("MD5");
            byte[] MD5inputBytes = MD5input.getBytes("UTF-8");
            mDig.digest(MD5inputBytes);
        }
        catch (NoSuchAlgorithmException ex) {
            System.out.println("No MD5 algorithm exists in the current environment: " + ex.getMessage());
        }
        catch (UnsupportedEncodingException ex) {
            System.out.println("UTF-8 encoding is not supported in the current environment: " + ex.getMessage());
        }
        return MD5bytes;
    }        
}
