package tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class MD5crackerTools {
    
    private static DataBundle parseHashFile(File hashFile) 
            throws FileNotFoundException {
        String[] userNames = new String[0];
        String[] salts     = new String[0];
        String[] MD5hashes = new String[0];
        
        if (!hashFile.canRead()) {
            throw new FileNotFoundException();
        }
        
        Scanner dictionaryScanner = new Scanner(hashFile, "UTF-8");
        
        int i = 0;
        
        while(dictionaryScanner.hasNextLine()) {
            
            String[] fields = dictionaryScanner.nextLine().split(":");
            
            if (userNames.length < i + 1) {
                String[] userTemp = new String[userNames.length * 2];
                String[] saltTemp = new String[userNames.length * 2];
                String[] hashTemp = new String[userNames.length * 2];
                
                for (int j = 0; j < userNames.length; j++) {
                    userTemp[j] = userNames[j];
                    saltTemp[j] = salts[j];
                    hashTemp[j] = MD5hashes[j];
                }
                userNames = userTemp;
                salts     = saltTemp;
                MD5hashes = hashTemp;
            }
            
            userNames[i] = fields[0];
            salts[i]     = fields[1];
            MD5hashes[i] = fields[2];
            
            System.out.println("Username: " + userNames[i]);
            System.out.println("Salt    : " + salts[i]);
            System.out.println("Password: " + MD5hashes[i]);
            System.out.println("---");
            
            i++;
        }
        
        return new DataBundle(userNames, salts, MD5hashes);
    }
    
    public static String dictionaryAttack(String passwordHash, String passwordSalt, File dictionary) {
        
        
        return null;
    }
    
    
    
    /**
     * Attacks an MD5 hash and salt by using all permutations of [a-z] and [0-9]
     * from 1-5 characters in length (inclusive)
     * @param passwordHash MD5 hash of password
     * @param passwordSalt password salt
     * @return String value of password, null if password is not found
     */
    public static String bruteForceAttack(String passwordHash, String passwordSalt) {
        final String[] possibleChars = {
            "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q",
            "r","s","t","u","v","w","x","y","z",
            "0","1","2","3","4","5","6","7","8","9"
        };
        
        String crackedPassword = null;
        boolean foundPassword = false;
        
        for (int i = 0; i < possibleChars.length && !foundPassword; i++) {
            String plainGuess = possibleChars[i];
            String MD5guessLength1 = getMD5hashString(plainGuess + passwordSalt);
            crackedPassword = (MD5guessLength1.equals(passwordHash) ? plainGuess : null);
            
            foundPassword = crackedPassword != null;
        }
        
        for (int i = 0; i < possibleChars.length && !foundPassword; i++) {
            for (int j = 0; j < possibleChars.length && !foundPassword; j++) {
                String plainGuess = 
                    possibleChars[i] + 
                    possibleChars[j];
                
                String guessLength2 = getMD5hashString(plainGuess + passwordSalt);
                
                crackedPassword = (guessLength2.equals(passwordHash) ? plainGuess : null);
                
                foundPassword = crackedPassword != null;
            }
        }
        
        for (int i = 0; i < possibleChars.length && !foundPassword; i++) {
            for (int j = 0; j < possibleChars.length && !foundPassword; j++) {
                for (int k = 0; k < possibleChars.length && !foundPassword; k++) {
                    String plainGuess = 
                        possibleChars[i] + 
                        possibleChars[j] +
                        possibleChars[k];
                    
                    String guessLength3 = getMD5hashString(plainGuess + passwordSalt);
                    
                    crackedPassword = (guessLength3.equals(passwordHash) ? plainGuess : null);
                    
                    foundPassword = crackedPassword != null;
                }
            }
        }
        
        for (int i = 0; i < possibleChars.length && !foundPassword; i++) {
            for (int j = 0; j < possibleChars.length && !foundPassword; j++) {
                for (int k = 0; k < possibleChars.length && !foundPassword; k++) {
                    for (int l = 0; l < possibleChars.length && !foundPassword; l++) {
                        String plainGuess = 
                            possibleChars[i] + 
                            possibleChars[j] +
                            possibleChars[k] +
                            possibleChars[l];
                        
                        String guessLength4 = getMD5hashString(plainGuess + passwordSalt);

                        crackedPassword = (guessLength4.equals(passwordHash) ? plainGuess : null);

                        foundPassword = crackedPassword != null;
                    }
                }
            }
        }
        
        for (int i = 0; i < possibleChars.length && !foundPassword; i++) {
            for (int j = 0; j < possibleChars.length && !foundPassword; j++) {
                for (int k = 0; k < possibleChars.length && !foundPassword; k++) {
                    for (int l = 0; l < possibleChars.length && !foundPassword; l++) {
                        for (int m = 0; m < possibleChars.length && !foundPassword; m++) {
                            String plainGuess = 
                                possibleChars[i] + 
                                possibleChars[j] +
                                possibleChars[k] +
                                possibleChars[l] +
                                possibleChars[m];
                            
                            String guessLength5 = getMD5hashString(plainGuess + passwordSalt);
                    
                            crackedPassword = (guessLength5.equals(passwordHash) ? plainGuess : null);
                    
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
