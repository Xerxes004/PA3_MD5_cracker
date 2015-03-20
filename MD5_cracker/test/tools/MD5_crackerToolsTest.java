/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wes
 */
public class MD5_crackerToolsTest {
    
    public MD5_crackerToolsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testGetMD5bytes() {
        System.out.println("Unit Test: getMD5bytes");
        String MD5input = "1";
        System.out.println("Hashing: " + MD5input);
        
        String expectedResult = "c4ca4238a0b923820dcc509a6f75849b";
        String actualResult = MD5_crackerTools.getMD5hashString(MD5input);

        System.out.println("Found   : " + actualResult);
        System.out.println("Expected: " + expectedResult);

        assert(expectedResult.equals(actualResult));    
    }

    /*
    @Test
    public void testGetMD5bytes_String() {
        System.out.println("getMD5bytes");
        String MD5input = "";
        byte[] expResult = null;
        byte[] result = MD5_crackerTools.getMD5bytes(MD5input);
        assertArrayEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetMD5bytes_byteArr() {
        System.out.println("getMD5bytes");
        byte[] MD5input = null;
        byte[] expResult = null;
        byte[] result = MD5_crackerTools.getMD5bytes(MD5input);
        assertArrayEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    */    
}
