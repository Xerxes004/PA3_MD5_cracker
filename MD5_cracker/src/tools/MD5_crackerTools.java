package tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

/**
 *
 * @author wes
 */
public class MD5_crackerTools {
    
    public static String getMD5hashString(String input) {
        HexBinaryAdapter adapter = new HexBinaryAdapter();
        
        String MD5hashString = adapter.marshal(getMD5bytes(input));
        MD5hashString = MD5hashString.trim();
        MD5hashString = MD5hashString.toLowerCase();
        
        return MD5hashString;
    }
    
    private static byte[] getMD5bytes (String MD5input) {
        try {
            MessageDigest mDig = MessageDigest.getInstance("MD5");
            byte[] MD5bytes = mDig.digest(MD5input.getBytes("UTF-8"));
            
            return MD5bytes;
        }
        
        catch (UnsupportedEncodingException ex) {
            System.out.println("UTF-8 encoding is not supported in the current environment: " + ex.getMessage());
            return null;
        }
        catch (NoSuchAlgorithmException ex) {
            return null;
        }   
    }    
}
