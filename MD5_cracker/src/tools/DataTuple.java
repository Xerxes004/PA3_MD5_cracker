/**
 * Author : Wesley Kelly
 */
package tools;

public class DataTuple {
    public DataTuple(String initUsername, String initPassword) {
        username = initUsername;
        password = initPassword;
    }
    
    private final String username;
    private final String password;
    
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
