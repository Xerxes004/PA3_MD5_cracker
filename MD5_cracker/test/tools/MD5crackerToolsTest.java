/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wes
 */
public class MD5crackerToolsTest {
    
    @Test
    public void testDictionaryAttack() throws Exception {
        System.out.println("dictionaryAttack");
        String passwordHash = "";
        String passwordSalt = "";
        File dictionary = new File("/Users/wes/Dropbox/Cedarville/2-Sophomore/Spring 2015/Computer Security/Programming Projects/Proj 3/pa3hashes.txt");
        String expResult = "";
        String result = MD5crackerTools.dictionaryAttack(passwordHash, passwordSalt, dictionary);
        assertEquals(expResult, result);
    }
/*
    @Test
    public void testPermutationBruteForce() {
        System.out.println("permutationBruteForce");
        String passwordHash = "61e3bbe3ca4c8bc38e142b139a346b46";
        String passwordSalt = "";
        String expResult = "stops";
        String result = MD5crackerTools.bruteForceAttack(passwordHash, passwordSalt);
        System.out.println("Expected password: " + expResult);
        System.out.println("  Actual password: " + result);
        Assert.assertEquals(expResult, result);
        System.out.println("-----------------------------------");
    }

    @Test
    public void testGetMD5hashString() {
        System.out.println("getMD5hashString");
        String inputString = "";
        String expResult = "";
        String result = MD5crackerTools.getMD5hashString(inputString);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    */
}
