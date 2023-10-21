package register_projekt;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Scanner;


public class Login {

    private Scanner scanner = new Scanner(System.in);
    private User storedUser;
    private DatabaseQuerys databaseQuerys = new DatabaseQuerys();
    private HashPW hPW = new HashPW();
    private Connection con;

    public User userNameField() throws ClassNotFoundException, SQLException {
        userNamePrompt();
        String input = readStringInput();
        con = databaseQuerys.createDBConnection();
        if (databaseQuerys.getAMatchingUser(con, input) == null) {
            throw new RuntimeException("No such user exists");
        }
        storedUser = databaseQuerys.getAMatchingUser(con, input);
        return storedUser;
    }

    private String readStringInput() {
        return scanner.nextLine();
    }

    private void userNamePrompt() {
        System.out.print("Enter Name: ");
    }

    public boolean passwordField(User storedUser) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        pwPrompt();
        String inputPW = readStringInput();
        byte[] salt = Base64.getDecoder().decode(storedUser.getSalt());
        String hashedInputPW = hPW.hashPW(inputPW, salt);
        if (!hashedInputPW.equals(storedUser.getHashedPw())) {
            throw new IOException("Wrong password for user " + storedUser.getUser());
        }
        System.out.println("Login succesful");
        return true;
    }

    private void pwPrompt() {
        System.out.print("Enter password: ");
    }
    
}
