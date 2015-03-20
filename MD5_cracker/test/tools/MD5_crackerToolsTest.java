/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import org.junit.Test;

/**
 *
 * @author wes
 */
public class MD5_crackerToolsTest {

    @Test
    public void testGetMD5bytes() {
        System.out.println("Unit Test: getMD5bytes");
        String MD5input = "12";
        System.out.println("Hashing: " + MD5input);
        
        String expectedResult = "c20ad4d76fe97759aa27a0c99bff6710";
        String actualResult = MD5_crackerTools.getMD5hashString(MD5input);

        System.out.println("Found   : " + actualResult);
        System.out.println("Expected: " + expectedResult);

        assert(expectedResult.equals(actualResult));    
    }   
}
