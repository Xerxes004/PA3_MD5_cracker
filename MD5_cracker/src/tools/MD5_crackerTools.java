package tools;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class MD5_crackerTools {
    
    /**
     * 
     * @param passwordHash
     * @param passwordSalt
     * @return 
     */
    public static String permutationBruteForce(
            String passwordHash, String passwordSalt) 
    {
        final String[] possibleChars = {
            "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q",
            "r","s","t","u","v","w","x","y","z",
            "0","1","2","3","4","5","6","7","8","9"
        };
        
        String crackedPassword = null;
        boolean foundPassword = false;
        
        for (int i = 0; i < possibleChars.length && !foundPassword; i++) 
        {
            String plainGuessLength1 = possibleChars[i];
            String MD5guessLength1 = getMD5hashString(plainGuessLength1 + passwordSalt);
            crackedPassword = (MD5guessLength1.equals(passwordHash) ? plainGuessLength1 : null);
            
            foundPassword = crackedPassword != null;
        }
        
        for (int i = 0; i < possibleChars.length && !foundPassword; i++) 
        {
            String plainGuessLength1 = possibleChars[i];
            
            for (int j = 0; j < possibleChars.length && !foundPassword; j++) 
            {
                String plainGuessLength2 = plainGuessLength1 + possibleChars[j];
                String guessLength2 = getMD5hashString(plainGuessLength2 + passwordSalt);
                
                crackedPassword = (guessLength2.equals(passwordHash) ? plainGuessLength2 : null);
                
                foundPassword = crackedPassword != null;
            }
        }
        
        for (int i = 0; i < possibleChars.length && !foundPassword; i++) 
        {
            String plainGuessLength1 = possibleChars[i];
            
            for (int j = 0; j < possibleChars.length && !foundPassword; j++) 
            {
                String plainGuessLength2 = plainGuessLength1 + possibleChars[j];
                
                for (int k = 0; k < possibleChars.length && !foundPassword; k++) 
                {
                    String plainGuessLength3 = plainGuessLength2 + possibleChars[k];
                    String guessLength3 = getMD5hashString(plainGuessLength3 + passwordSalt);
                    
                    crackedPassword = (guessLength3.equals(passwordHash) ? plainGuessLength3 : null);
                    
                    foundPassword = crackedPassword != null;
                }
            }
        }
        
        for (int i = 0; i < possibleChars.length && !foundPassword; i++) 
        {
            String plainGuessLength1 = possibleChars[i];
            
            for (int j = 0; j < possibleChars.length && !foundPassword; j++) 
            {
                String plainGuessLength2 = plainGuessLength1 + possibleChars[j];
                
                for (int k = 0; k < possibleChars.length && !foundPassword; k++) 
                {
                    String plainGuessLength3 = plainGuessLength2 + possibleChars[k];
                    
                    for (int l = 0; l < possibleChars.length && !foundPassword; l++) 
                    {
                        String plainGuessLength4 = plainGuessLength3 + possibleChars[l];
                        String guessLength4 = getMD5hashString(plainGuessLength4 + passwordSalt);

                        crackedPassword = (guessLength4.equals(passwordHash) ? plainGuessLength4 : null);

                        foundPassword = crackedPassword != null;
                    }
                }
            }
        }
        
        for (int i = 0; i < possibleChars.length && !foundPassword; i++) 
        {
            String plainGuessLength1 = possibleChars[i];
            
            for (int j = 0; j < possibleChars.length && !foundPassword; j++) 
            {
                String plainGuessLength2 = plainGuessLength1 + possibleChars[j];
                
                for (int k = 0; k < possibleChars.length && !foundPassword; k++) 
                {
                    String plainGuessLength3 = plainGuessLength2 + possibleChars[k];
                    
                    for (int l = 0; l < possibleChars.length && !foundPassword; l++) 
                    {
                        String plainGuessLength4 = plainGuessLength3 + possibleChars[l];
                        
                        for (int m = 0; m < possibleChars.length && !foundPassword; m++) 
                        {
                            String plainGuessLength5 = plainGuessLength4 + possibleChars[m];
                            String guessLength5 = getMD5hashString(plainGuessLength5 + passwordSalt);
                    
                            crackedPassword = (guessLength5.equals(passwordHash) ? plainGuessLength5 : null);
                    
                            foundPassword = crackedPassword != null;
                        }
                    }
                }
            }
        }
        
        return crackedPassword;
    }
    
    /**
     * String to be turned into an MD5-hash string
     * @param inputString
     * @return String representation of inputString's MD5 hash
     */
    public static String getMD5hashString(String inputString) {
        HexBinaryAdapter adapter = new HexBinaryAdapter();
        
        String MD5hashString = adapter.marshal(getMD5bytes(inputString));
        MD5hashString = MD5hashString.trim().toLowerCase();
        
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
