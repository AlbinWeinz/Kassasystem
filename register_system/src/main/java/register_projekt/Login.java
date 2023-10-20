package register_projekt;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Scanner;


public class Login {

    private Scanner scanner = new Scanner(System.in);
    private User storedUser;

    public void userNameField() throws ClassNotFoundException, SQLException {
        userNamePrompt();
        DatabaseQuerys databaseQuerys = new DatabaseQuerys();
        storedUser = databaseQuerys.createAMatchingUser(readStringInput());
    }

    private String readStringInput() {
        return scanner.nextLine();
    }

    private void userNamePrompt() {
        System.out.print("Enter Name: ");
    }

    public void passwordField() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        pwPrompt();
        String inputPW = readStringInput();
        byte[] salt = Base64.getDecoder().decode(storedUser.getSalt());
        HashPW hPW = new HashPW();
        String hashedInputPW = hPW.hashPW(inputPW, salt);
        if (!hashedInputPW.equals(storedUser.getHashedPw())) {
            throw new IOException("Wrong password for user " + storedUser.getUser());
        }
        else {
            System.out.println("Login successful!");
        }
    }

    private void pwPrompt() {
        System.out.print("Enter password: ");
    }
    
}
