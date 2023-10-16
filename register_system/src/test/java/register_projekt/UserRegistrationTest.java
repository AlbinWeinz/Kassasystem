package register_projekt;

import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.Rule;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class UserRegistrationTest {

    @Rule
    public final TextFromStandardInputStream sysInMock = TextFromStandardInputStream.emptyStandardInputStream();

    @Test(expected = IOException.class)
    public void pwNull() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException, SQLException {
        String nullString = null;
        sysInMock.provideLines("User", nullString);
        new UserRegistration();
    }

    @Test(expected = IOException.class)
    public void pwEmpty() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException, SQLException {
        sysInMock.provideLines("User", "");
        new UserRegistration();
    }

    @Test(expected = IOException.class)
    public void pwTooShort() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException, SQLException {
        sysInMock.provideLines("User", "Passw1!");
        new UserRegistration();
    }

    @Test(expected = IOException.class)
    public void pwTooLong() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException, SQLException {
        sysInMock.provideLines("User", "Superawesomepassword1!");
        new UserRegistration();
    }

    @Test(expected = IOException.class)
    public void pwNoUpper() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException, SQLException {
        sysInMock.provideLines("User", "awesomepass1!");
        new UserRegistration();
    }

    @Test(expected = IOException.class)
    public void pwNoLower() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException, SQLException {
        sysInMock.provideLines("User", "AWESOMEPASS1!");
        new UserRegistration();
    }

    @Test(expected = IOException.class)
    public void pwNoSpecial() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException, SQLException {
        sysInMock.provideLines("User", "Awesomepass1");
        new UserRegistration();
    }

    @Test(expected = IOException.class)
    public void pwNoDogit() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException, SQLException {
        sysInMock.provideLines("User", "Awesomepass!");
        new UserRegistration();
    }


    //currently sometimes allows nonenglish letters if all other criterias are met
    @Test(expected = IOException.class)
    public void pwNonEnglishChar() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException, SQLException {
        sysInMock.provideLines("User", "Awesömepass1!");
        new UserRegistration();
    }

    //What to assert? method is called?, matcher boolean?
    @Test
    public void pwValidTest() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException, SQLException {
        sysInMock.provideLines("User", "Awesomepass1!");
        new UserRegistration();
    }
    
}
