package tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class MD5_crackerTools {
    
    
    
    /**
     * String to be turned into an MD5-hash string
     * @param inputString
     * @return String representation of inputString's MD5 hash
     */
    public static String getMD5hashString(String inputString) {
        HexBinaryAdapter adapter = new HexBinaryAdapter();
        
        String MD5hashString = adapter.marshal(getMD5bytes(inputString));
        MD5hashString = MD5hashString.trim();
        MD5hashString = MD5hashString.toLowerCase();
        
        return MD5hashString;
    }
    
    /**
     * String input is turned into an MD5-hash byte array
     * @param MD5input
     * @return the MD5 hashed representation of the input string
     */
    private static byte[] getMD5bytes (String MD5input) {
        try {
            //  initializes a MessageDigest as an MD5 digest
            MessageDigest digest = MessageDigest.getInstance("MD5");
            //  digests the input byte array (encoded in UTF-8) into an MD5
            //  byte array
            byte[] MD5bytes = digest.digest(MD5input.getBytes("UTF-8"));
            
            return MD5bytes;
        }
        
        catch (UnsupportedEncodingException | 
               NoSuchAlgorithmException ex) {
            return null;
        }   
    }
    
    
}
