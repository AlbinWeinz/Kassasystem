package register_projekt;

import java.util.Base64;
import java.util.Scanner;

public class Login {

    private Scanner scanner = new Scanner(System.in);
    private User storedUser;

    public Login() throws Exception {
        userName();
    }
    
    private void userName() throws Exception {
        userNamePrompt();
        FetchMatchingUser fetchMatchingUser = new FetchMatchingUser();
        storedUser = fetchMatchingUser.fetchMatchingUser(readStringInput());
        if (storedUser != null) {
            password();
        }
    }

    private String readStringInput() {
        return scanner.nextLine();
    }

    private void userNamePrompt() {
        System.out.print("Enter Name: ");
    }

    private void password() throws Exception {
        pwPrompt();
        String inputPW = readStringInput();
        byte[] salt = Base64.getDecoder().decode(storedUser.getSalt());
        HashPW hPW = new HashPW();
        String hashedInputPW = hPW.hashPW(inputPW, salt);
        if (!comparePWS(hashedInputPW, storedUser.getHashedPw())) {
            throw new Exception("Wrong password for user " + storedUser.getUser());
        }
        else {
            System.out.println("Login successful!");
        }
    }

    private void pwPrompt() {
        System.out.print("Enter password: ");
    }

    private boolean comparePWS(String enteredPW, String storedPW) {
        if (storedPW.equals(enteredPW)) {
            return true;
        }
        return false;
    }
    
}
