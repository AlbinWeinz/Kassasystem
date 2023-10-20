package register_projekt;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRegistration {

    private final Pattern userPattern = Pattern.compile("^[\\p{L}\\s.'\\-,]+$");
    private final Pattern pwPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()-[{}]:;',?/*~$^+=<>])[A-Za-z0-9!@#&()-[{}]:;',?/*~$^+=<>]{8,20}$");
    private Matcher matcher;
    private Scanner scanner = new Scanner(System.in);
    private String userName;
    private String hashedPw;
    private String userSalt;
    private DatabaseQuerys databaseQuerys = new DatabaseQuerys();

    public void userNameField() throws IOException, ClassNotFoundException, SQLException {
        userNamePrompt();
        String input = readStringInput();
        checkStringAgainstMatcher(input, userPattern);
        if (databaseQuerys.checkIfuserExits(input)) {{
            throw new IOException("A user with that name already exists");
        }}
        userName = input;
    }

    private boolean checkStringAgainstMatcher(String input, Pattern pattern) throws IOException {
        matcher = pattern.matcher(input);
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

    protected String readStringInput() {
        return scanner.nextLine();
    }
    
    public void passwordField() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException, ClassNotFoundException, SQLException {
        pwPrompt();
        String input = readStringInput();
        if (checkStringAgainstMatcher(input, pwPattern)) {
            HashPW hPW = new HashPW();
            byte[] salt = hPW.createSalt();
            userSalt = Base64.getEncoder().encodeToString(salt);
            hashedPw = hPW.hashPW(input, salt);
            databaseQuerys.insertUserInDB(userName, hashedPw, userSalt);
            registrationConfirmation();
        }
    }

    private void registrationConfirmation() {
        System.out.println("User registered");
    }

    private void pwPrompt() {
        System.out.print("Password (Min 8 chars, max 20, min one digit, min one uppercase char, min one lower case char, min one special char (!@#$%&*()-+=^)): ");
    }

}
