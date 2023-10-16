package register_projekt;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRegistration {

    private final Pattern userPattern = Pattern.compile("^[\\p{L}\\s.’\\-,]+$");
    private final Pattern pwPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
    private Matcher matcher;
    private Scanner scanner = new Scanner(System.in);
    private String userName;
    private String hashedPw;
    private String userSalt;

    public UserRegistration() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException, SQLException {
        if(userName()) {
            password();
        }
    }

    private boolean userName() throws IOException, ClassNotFoundException, SQLException {
        userNamePrompt();
        String input = readStringInput();
        if (checkStringAgainstMatcher(input, userPattern) && !userAlreadyExists(input) ) {
            userName = input;
            return true;
        }
        return false;
    }

    private boolean userAlreadyExists(String user) throws ClassNotFoundException, SQLException {
        FetchMatchingUser fetchMatchingUser = new FetchMatchingUser();
        if (fetchMatchingUser.fetchMatchingUser(user) == null) {
            return false;
        }
        System.out.println("Error: A user with that name already exists");
        return true;
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

    private String readStringInput() {
        return scanner.nextLine();
    }
    
    private void password() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException, ClassNotFoundException, SQLException {
        pwPrompt();
        String input = readStringInput();
        if (checkStringAgainstMatcher(input, pwPattern)) {
            HashPW hPW = new HashPW();
            hashedPw = hPW.hashPW(input, createSalt());
            InsertUserInDB insertUserInDB = new InsertUserInDB();
            insertUserInDB.insertUserINDB(userName, hashedPw, userSalt);
            registrationConfirmation();
        }
    }

    private void registrationConfirmation() {
        System.out.println("User registered");
    }

    private void pwPrompt() {
        System.out.print("Password (Min 8 chars, max 20, min one digit, min one uppercase char, min one lower case char, min one special char (!@#$%&*()-+=^)): ");
    }

    private byte[] createSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        userSalt = Base64.getEncoder().encodeToString(salt);
        return salt;
    }

}
