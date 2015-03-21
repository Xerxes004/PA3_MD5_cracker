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
        String passwordHash = "f3abb86bd34cf4d52698f14c0da1dc60";
        String passwordSalt = "";
        String expResult = "zzz";
        String result = MD5_crackerTools.permutationBruteForce(passwordHash, passwordSalt);
        System.out.println("Expected password: " + expResult);
        System.out.println("  Actual password: " + result);
        Assert.assertEquals(expResult, result);
        System.out.println("-----------------------------------");
    }
}
