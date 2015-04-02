package tools;

import exceptions.DictionaryNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Scanner;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class MD5crackerTools {
    
    /**
     * Performs a dictionary and brute-force attack on an MD5-hashed password
     * using the specified dictionary
     * @param hash MD5-hash to attack
     * @param salt
     * @param dictionary
     * @return
     * @throws DictionaryNotFoundException
     * @throws FileNotFoundException 
     */
    public static String fullAttack(String hash, String salt, File dictionary) 
            throws DictionaryNotFoundException,
                   FileNotFoundException
    {
        String dictionaryResult = dictionaryAttack(hash, salt, dictionary);

        if (dictionaryResult != null) {
            return dictionaryResult;
        }
        
        String bruteResult = bruteForceAttack(hash, salt);

        if (bruteResult != null) {
            return bruteResult;
        }
        
        return null;
    }
    
    /**
     * Uses a dictionary to attack an MD5-hashed password.
     * @param passwordHash hash of password to attack
     * @param passwordSalt salt of password to attack
     * @param dictionary dictionary to use when attacking password
     * @return String of password if found, null if not found
     * @throws DictionaryNotFoundException
     * @throws FileNotFoundException 
     */
    public static String dictionaryAttack
        (String passwordHash, String passwordSalt, File dictionary) 
            throws DictionaryNotFoundException,
                   FileNotFoundException
    {
        if (dictionary == null) {
            throw new DictionaryNotFoundException();
        }
        
        Scanner dictionaryScanner = new Scanner(dictionary);
        HashMap<String, String> map = new HashMap();
        
        while (dictionaryScanner.hasNext()) {
            String nextWord = dictionaryScanner.next().toLowerCase().trim();
            
            for (int i = 0; i < nextWord.length(); i++) {
                if (!Character.isLetterOrDigit(nextWord.charAt(i))) {
                    nextWord = nextWord.replace(nextWord.charAt(i), ' ');
                }
            }
            nextWord = nextWord.toLowerCase().trim();
            map.put(nextWord, nextWord);
            
            String dictionaryWordHash = null;
            
            if (nextWord.length() > 5) {
                dictionaryWordHash = getMD5hashString(nextWord + passwordSalt);
            
                if (!map.containsKey(dictionaryWordHash)) {
                    if (dictionaryWordHash.equals(passwordHash)) {
                        return nextWord;
                    }
                }
            }
        }
        
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
     * Parses the hash file which has lines formatted like this:
     *     username:salt:MD5hash
     * @param hashFile file to be parsed
     * @return DataBundle object that contains the usernames, salts, and hashes
     * @throws FileNotFoundException 
     */
    public static DataBundle parseHashFile(File hashFile) 
            throws FileNotFoundException 
    {
        final int dataLength = countLines(hashFile);
        
        String[] userNames = new String[dataLength];
        String[] salts     = new String[dataLength];
        String[] MD5hashes = new String[dataLength];
        
        if (!hashFile.canRead()) {
            throw new FileNotFoundException();
        }
        
        Scanner dictionaryScanner = new Scanner(hashFile, "UTF-8");
        
        int i = 0;
        
        while(dictionaryScanner.hasNextLine()) {
            //  Splits the line at each ":" and stores each chunk in a String[]
            String[] fields = dictionaryScanner.nextLine().split(":");
            
            //  username:salt:MD5hash
            //  field[0]:field[1]:field[2]
            userNames[i] = fields[0].toLowerCase().trim();
            salts[i]     = fields[1].toLowerCase().trim();
            MD5hashes[i] = fields[2].toLowerCase().trim();
            
            i++;
        }
        
        return new DataBundle(userNames, salts, MD5hashes);
    }
    
    /**
     * Counts the lines of a file
     * @param file to have its lines read
     * @return number of lines in file
     * @throws FileNotFoundException 
     */
    private static int countLines(File file) 
            throws FileNotFoundException 
    {
        Scanner scanner = new Scanner(file);
        
        int i = 0;
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            i++;
        }
        scanner.close();
        
        return i;
    }
    
    /**
     * Turns a string into an MD5-hashed string
     * @param inputString String to be turned into an MD5-hash string
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
    
    /**
     * Hashes all possible passwords and stores them in a hash table
     * to use in a forward search attack 
     * @param dictionary
     * @return A hashmap of all possible hashes
     */
    public static HashMap forwardSearch(File dictionary) throws FileNotFoundException, DictionaryNotFoundException {
        final String[] possibleChars = {
            "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q",
            "r","s","t","u","v","w","x","y","z",
            "0","1","2","3","4","5","6","7","8","9"
        };
        String password = "";
        String hashedPassword;
        HashMap<String, String> passwords = new HashMap();
        for(int i = 0; i < possibleChars.length; i++) {
            passwords.put(getMD5hashString(possibleChars[i]), possibleChars[i]);          
        }
        for (int i = 0; i < possibleChars.length; i++) {
            for (int j = 0; j < possibleChars.length; j++) {
                password = possibleChars[i] + possibleChars[j];
                
                hashedPassword = getMD5hashString(password);
                passwords.put(hashedPassword, password);
            }
        }
        
        for (int i = 0; i < possibleChars.length; i++) {
            for (int j = 0; j < possibleChars.length; j++) {
                for (int k = 0; k < possibleChars.length; k++) {
                    password = possibleChars[i] + possibleChars[j] + possibleChars[k];
                    
                    hashedPassword = getMD5hashString(password);
                    passwords.put(hashedPassword, password);
                }
            }
        }
        
        for (int i = 0; i < possibleChars.length; i++) {
            for (int j = 0; j < possibleChars.length; j++) {
                for (int k = 0; k < possibleChars.length; k++) {
                    for (int l = 0; l < possibleChars.length; l++) {
                        password = possibleChars[i] + possibleChars[j] + possibleChars[k] + possibleChars[l];
                        
                        hashedPassword = getMD5hashString(password);
                        passwords.put(hashedPassword, password);
                    }
                }
            }
        }
        
        for (int i = 0; i < possibleChars.length; i++) {
            for (int j = 0; j < possibleChars.length; j++) {
                for (int k = 0; k < possibleChars.length; k++) {
                    for (int l = 0; l < possibleChars.length; l++) {
                        for (int m = 0; m < possibleChars.length; m++) {
                            password = possibleChars[i] + possibleChars[j] + possibleChars[k] + possibleChars[l] +
                                possibleChars[m];
                            
                            hashedPassword = getMD5hashString(password);
                            passwords.put(hashedPassword, password);
                        }
                    }
                }
            }
        } 
        
        if(dictionary == null){
            throw new DictionaryNotFoundException();
        }
        
        Scanner fileSearch = new Scanner(dictionary);
        
        while (fileSearch.hasNext()) {
            String nextWord = fileSearch.next().toLowerCase().trim();

            for (int i = 0; i < nextWord.length(); i++) {
                if (!Character.isLetterOrDigit(nextWord.charAt(i))) {
                    nextWord = nextWord.replace(nextWord.charAt(i), ' ');
                }
            }
            nextWord = nextWord.toLowerCase().trim();


            if (nextWord.length() > 5) {
                hashedPassword = getMD5hashString(nextWord);

                passwords.put(hashedPassword, nextWord);
            }
        }
        
        return passwords;
        
    }
}
