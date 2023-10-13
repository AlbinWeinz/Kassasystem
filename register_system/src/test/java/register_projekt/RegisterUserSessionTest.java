package register_projekt;

import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.junit.Assert.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.junit.Rule;

public class RegisterUserSessionTest {

    @Rule
    public final TextFromStandardInputStream sysInMock = TextFromStandardInputStream.emptyStandardInputStream();


    @Test(expected = IOException.class)
    public void pwNull() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        sysInMock.provideLines("User Name", null);
        new RegisterUserSession();
    }

    @Test(expected = IOException.class)
    public void pwEmpty() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        sysInMock.provideLines("User Name", "");
        new RegisterUserSession();
    }

    @Test(expected = IOException.class)
    public void pwTooShort() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        sysInMock.provideLines("User Name", "Passw1!");
        new RegisterUserSession();
    }

    @Test(expected = IOException.class)
    public void pwTooLong() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        sysInMock.provideLines("User Name", "Superawesomepassword1!");
        new RegisterUserSession();
    }

    @Test(expected = IOException.class)
    public void pwNoUpper() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        sysInMock.provideLines("User Name", "awesomepass1!");
        new RegisterUserSession();
    }

    @Test(expected = IOException.class)
    public void pwNoLower() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        sysInMock.provideLines("User Name", "AWESOMEPASS1!");
        new RegisterUserSession();
    }

    @Test(expected = IOException.class)
    public void pwNoSpecial() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        sysInMock.provideLines("User Name", "Awesomepass1");
        new RegisterUserSession();
    }

    @Test(expected = IOException.class)
    public void pwNoDogit() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        sysInMock.provideLines("User Name", "Awesomepass!");
        new RegisterUserSession();
    }


    //currently sometimes allows nonenglish letters if all other criterias are met
    @Test(expected = IOException.class)
    public void pwNonEnglishChar() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        sysInMock.provideLines("User Name", "amepÄåSs1hhhhjd!");
        new RegisterUserSession();
    }

    //What to assert? method is called?, matcher boolean?
    @Test
    public void pwValidTest() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        sysInMock.provideLines("User Name", "Awesomepass1!");
        new RegisterUserSession();
    }
    
}
