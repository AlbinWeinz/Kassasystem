package register_projekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.userNameField();
        userRegistration.passwordField();
    }
}
