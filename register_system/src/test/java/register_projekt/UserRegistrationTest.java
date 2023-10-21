package register_projekt;

import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Rule;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Scanner;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationTest {

    @Rule
    public final TextFromStandardInputStream sysInMock = TextFromStandardInputStream.emptyStandardInputStream();

    @InjectMocks
    UserRegistration userRegistrationWithMocks = new UserRegistration();

    @Mock
    Scanner scanner;

    @Mock
    DatabaseQuerys databaseQuerys;

    @Mock 
    Connection con;

    // userNameField = method under test
    @Test(expected = NoSuchElementException.class)
    public void userNameNull() throws IOException, ClassNotFoundException, SQLException {
        when(scanner.nextLine()).thenReturn(null);
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.userNameField();
    }                      
    
    @Test(expected = IOException.class)
    public void userNameEmpty() throws IOException, ClassNotFoundException, SQLException {
        sysInMock.provideLines("");
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.userNameField();
    }   


    @Test(expected = RuntimeException.class)
    public void userNameAlreadyExists() throws IOException, ClassNotFoundException, SQLException {
        User user = new User("Test User", "", "");
        when(scanner.nextLine()).thenReturn("Test User");
        when(databaseQuerys.createDBConnection()).thenReturn(con);
        when(databaseQuerys.getAMatchingUser(con, "Test User")).thenReturn(user);
        userRegistrationWithMocks.userNameField();
    }

    @Test
    public void standardUserName() throws IOException, ClassNotFoundException, SQLException {
        when(scanner.nextLine()).thenReturn("Test User");
        when(databaseQuerys.createDBConnection()).thenReturn(con);
        when(databaseQuerys.getAMatchingUser(con, "Test User")).thenReturn(null);
        userRegistrationWithMocks.userNameField();
        verify(databaseQuerys).getAMatchingUser(con, "Test User");
    }

    @Test
    public void userNameOnylSingleName() throws ClassNotFoundException, SQLException, IOException {
        when(scanner.nextLine()).thenReturn("Cher");
        when(databaseQuerys.createDBConnection()).thenReturn(con);
        when(databaseQuerys.getAMatchingUser(con, "Cher")).thenReturn(null);
        userRegistrationWithMocks.userNameField();
        verify(databaseQuerys).getAMatchingUser(con, "Cher");
    }

    @Test
    public void userNamewithHyphen() throws ClassNotFoundException, SQLException, IOException {
        when(scanner.nextLine()).thenReturn("Cher-Lloyd");
        when(databaseQuerys.createDBConnection()).thenReturn(con);
        when(databaseQuerys.getAMatchingUser(con, "Cher-Lloyd")).thenReturn(null);
        userRegistrationWithMocks.userNameField();
        verify(databaseQuerys).getAMatchingUser(con, "Cher-Lloyd");
    }

    @Test
    public void userNameWithApostrophe() throws ClassNotFoundException, SQLException, IOException {
        when(scanner.nextLine()).thenReturn("Cher O'Connor");
        when(databaseQuerys.createDBConnection()).thenReturn(con);
        when(databaseQuerys.getAMatchingUser(con, "Cher O'Connor")).thenReturn(null);
        userRegistrationWithMocks.userNameField();
        verify(databaseQuerys).getAMatchingUser(con, "Cher O'Connor");
    }

    @Test
    public void userNameWithAccentedChar() throws ClassNotFoundException, SQLException, IOException {
        when(scanner.nextLine()).thenReturn("Chér");
        when(databaseQuerys.createDBConnection()).thenReturn(con);
        when(databaseQuerys.getAMatchingUser(con, "Chér")).thenReturn(null);
        userRegistrationWithMocks.userNameField();
        verify(databaseQuerys).getAMatchingUser(con, "Chér");
    }

    //passwordField = method under test
    @Test(expected = IOException.class)
    public void pwNull() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException, SQLException {
        String nullString = null;
        sysInMock.provideLines(nullString);
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.passwordField();
    }

    @Test(expected = IOException.class)
    public void pwEmpty() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException, SQLException {
        sysInMock.provideLines("");
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.passwordField();
    }

    @Test(expected = IOException.class)
    public void pwTooShort() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException, SQLException {
        sysInMock.provideLines("Passw1!");
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.passwordField();
    }

    @Test(expected = IOException.class)
    public void pwTooLong() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException, SQLException {
        sysInMock.provideLines("Superawesomepassword1!");
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.passwordField();
    }

    @Test(expected = IOException.class)
    public void pwNoUpper() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException, SQLException {
        sysInMock.provideLines("awesomepass1!");
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.passwordField();
    }

    @Test(expected = IOException.class)
    public void pwNoLower() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException, SQLException {
        sysInMock.provideLines("AWESOMEPASS1!");
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.passwordField();
    }

    @Test(expected = IOException.class)
    public void pwNoSpecial() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException, SQLException {
        sysInMock.provideLines("Awesomepass1");
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.passwordField();
    }

    @Test(expected = IOException.class)
    public void pwNoDogit() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException, SQLException {
        sysInMock.provideLines("Awesomepass!");
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.passwordField();
    }

    @Test(expected = IOException.class)
    public void pwNonEnglishChar() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException, SQLException {
        sysInMock.provideLines("Awesömepass1!");
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.passwordField();
    }
 
    @Test
    public void pwValidTest() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException, SQLException {
        when(scanner.nextLine()).thenReturn("Awesomepass1!");
        userRegistrationWithMocks.passwordField();
        assertTrue(userRegistrationWithMocks.passwordField());
    }
    
}