/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author wes
 */
public class DataBundle {

    public DataBundle(String[] initUserNames, String[] initSalts, String[] initHashes) {
        userNames = initUserNames;
        salts = initSalts;
        hashes = initHashes;
        dataLength = initUserNames.length;
    }
    
    private final String[] userNames;
    private final String[] salts;
    private final String[] hashes;
    private final int dataLength;
    
    
    public String[] getAllUserNames() {
        return userNames;
    }
    public String getUserName(int i) {
        return userNames[i];
    }
    public String[] getAllSalts() {
        return salts;
    }
    public String getSalt(int i) {
        return salts[i];
    }
    public String[] getAllHashes() {
        return hashes;
    }
    public String getHash(int i) {
        return hashes[i];
    }
    public int length() {
        return dataLength;
    }
}
