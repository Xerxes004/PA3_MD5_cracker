/**
 * Author : Wesley Kelly
 */
package ui;
import exceptions.DictionaryNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import tools.DataBundle;
import tools.MD5crackerTools;

/**
 *
 * @author WesKellyPC
 */
public class main {
    public static void main (String[] args) {
        File hashes = new File("D:\\Dropbox\\Cedarville\\2-Sophomore\\Spring 2015\\Computer Security\\Programming Projects\\Proj 3\\pa3hashes.txt");
        File dictionary = new File("D:\\Dropbox\\Cedarville\\2-Sophomore\\Spring 2015\\Computer Security\\Programming Projects\\Proj 3\\Bible.txt");
        
        try {
            DataBundle data = MD5crackerTools.parseHashFile(hashes);
            
            for (int i = 0; i < data.length(); i++) {
            
                String testUser = data.getUserName(i);
                String testSalt = data.getSalt(i);
                String testPass = data.getHash(i);

                String crackedPass = "";

                try {
                    crackedPass = MD5crackerTools.fullAttack(testPass, testSalt, dictionary);
                }

                catch (DictionaryNotFoundException | FileNotFoundException ex) {}

                System.out.println(testUser + " " + crackedPass);
            }
        }
        
        catch(FileNotFoundException ex) {
            
        }
    }
}
