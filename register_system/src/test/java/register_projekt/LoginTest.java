package register_projekt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoginTest {

    @InjectMocks
    Login loginWithMocks = new Login();

    @Mock 
    DatabaseQuerys databaseQuerys;

    @Mock 
    Scanner scanner;

    @Mock
    Connection con;

    private User sampleUser = new User("Test User", "qEhfR5P3a3b1wPJKV0c2bA==", "jmkX7eFubvcBrGcsWHE1ig==");
    private String sampleUserPW = "Testpassword1!";

    //userNameField = method under test
    @Test(expected = RuntimeException.class)
    public void noMatchingUserInDB() throws ClassNotFoundException, SQLException {
        when(scanner.nextLine()).thenReturn("Test User");
        when(databaseQuerys.createDBConnection()).thenReturn(con);
        when(databaseQuerys.getAMatchingUser(con, "Test User")).thenReturn(null);
        loginWithMocks.userNameField();
        verify(databaseQuerys).getAMatchingUser(con, "Test User");
    }
    
    @Test
    public void matchingUserInDB() throws ClassNotFoundException, SQLException {
        when(scanner.nextLine()).thenReturn("Test User");
        when(databaseQuerys.createDBConnection()).thenReturn(con);
        when(databaseQuerys.getAMatchingUser(con, "Test User")).thenReturn(sampleUser);
        assertEquals(sampleUser, loginWithMocks.userNameField());
        verify(databaseQuerys, times(2)).getAMatchingUser(con, "Test User");
    }

    //passwordField = method under test
    @Test(expected = IOException.class)
    public void wrongPassword() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        when(scanner.nextLine()).thenReturn("NotTheSamePassword");
        loginWithMocks.passwordField(sampleUser);
    }

    @Test
    public void correctPassword() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        when(scanner.nextLine()).thenReturn(sampleUserPW);
        loginWithMocks.passwordField(sampleUser);
        assertTrue(loginWithMocks.passwordField(sampleUser));
    }
}
