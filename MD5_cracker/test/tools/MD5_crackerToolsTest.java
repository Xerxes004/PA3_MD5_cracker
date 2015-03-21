/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import org.junit.Assert;
import org.junit.Test;

public class MD5_crackerToolsTest {
    @Test
    public void testPermutationBruteForce() {
        System.out.println("permutationBruteForce");
        String passwordHash = "827ccb0eea8a706c4c34a16891f84e7b";
        String passwordSalt = "";
        String expResult = "12345";
        String result = MD5_crackerTools.permutationBruteForce(passwordHash, passwordSalt);
        System.out.println("Expected: " + expResult);
        System.out.println("  Actual: " + result);
        Assert.assertEquals(expResult, result);
        System.out.println("-----------------------------------");
    }
}
