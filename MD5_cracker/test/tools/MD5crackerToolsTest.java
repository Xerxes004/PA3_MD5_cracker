/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.File;
import org.junit.Test;

public class MD5crackerToolsTest {
    @Test
    public void testDictionaryAttack() throws Exception {
        System.out.println("dictionaryAttack");
        String passwordHash = "";
        String passwordSalt = "";
        File dictionary = new File("/Users/wes/Dropbox/Cedarville/2-Sophomore/Spring 2015/Computer Security/Programming Projects/Proj 3/pa3hashes.txt");
        String expResult = "";
        DataBundle result = MD5crackerTools.parseHashFile(dictionary);
    }
}
