package register_projekt;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRegistration {

    private static final Pattern userPattern = Pattern.compile("^[\\p{L}\\s.'\\-,]+$");
    private static final Pattern pwPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()-[{}]:;',?/*~$^+=<>])[A-Za-z0-9!@#&()-[{}]:;',?/*~$^+=<>]{8,20}$");
    private Scanner scanner = new Scanner(System.in);
    private User newUser = new User();
    private DatabaseQuerys databaseQuerys = new DatabaseQuerys();
    private Connection con;

    public void userNameField() throws IOException, ClassNotFoundException, SQLException {
        userNamePrompt();
        String input = readStringInput();
        checkStringAgainstMatcher(input, userPattern);
        con = databaseQuerys.createDBConnection();
        if (databaseQuerys.getAMatchingUser(con, input) != null) {
            scanner.close();
            con.close();
            throw new RuntimeException("A user with that name already exists");
        }
        newUser.setUserName(input);
    }

    private boolean checkStringAgainstMatcher(String input, Pattern pattern) throws IOException {
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return true;
        }
        else {
            throw new IOException("Invalid input");
        }
    }

    private void userNamePrompt() {
        System.out.print("Enter Name: ");
    }

    private String readStringInput() {
        return scanner.nextLine();
    }
    
    public boolean passwordField() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException, ClassNotFoundException, SQLException {
        pwPrompt();
        String input = readStringInput();
        scanner.close();
        if (checkStringAgainstMatcher(input, pwPattern)) {
            HashPW hPW = new HashPW();
            byte[] salt = hPW.createSalt();
            newUser.setSalt(Base64.getEncoder().encodeToString(salt));
            newUser.setHashedPw(hPW.hashPW(input, salt));
            databaseQuerys.insertUserInDB(con, newUser.getUserName(), newUser.getHashedPw(), newUser.getSalt());
            con.close();
            registrationConfirmation();
            return true;
        }
        con.close();
        return false;
    }

    private void registrationConfirmation() {
        System.out.println("User registered");
    }

    private void pwPrompt() {
        System.out.print("Password (Min 8 chars, max 20, min one digit, min one uppercase char, min one lower case char, min one special char (!@#$%&*()-+=^)): ");
    }

}
