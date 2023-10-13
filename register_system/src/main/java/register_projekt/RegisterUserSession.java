package register_projekt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.util.regex.Matcher;

public class RegisterUserSession {

    private String user;
    private String userSalt;
    private String hashedPw;
    private Scanner scanner = new Scanner(System.in);
    private final Pattern userPattern = Pattern.compile("^[\\p{L}\\s.’\\-,]+$");
    private final Pattern pwPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
    private Matcher matcher;

    public RegisterUserSession() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        userName();
        password();
        registerUserInFile();
    }

    private void userName() throws NoSuchAlgorithmException {
        System.out.print("Name: ");
        String input = scanner.nextLine();
        matcher = userPattern.matcher(input);
        if (matcher.find()) {
            user = input;
        }
        else {
            throw new NoSuchAlgorithmException("Invalid input");
        }
    }

    private void password() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        System.out.print("Password (Min 8 chars, max 20, min one digit, min one uppercase char, min one lower case char, min one special char (!@#$%&*()-+=^)): ");
        String pw = scanner.nextLine();
        matcher = pwPattern.matcher(pw);
        if (matcher.find()) {
            hashAndSaltPw(pw);
        }
        else {
            throw new IOException("Invalid password");
        }
    }

    private void hashAndSaltPw(String pw) throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeySpec keySpec = new PBEKeySpec(pw.toCharArray(), createSalt(), 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] bytes = factory.generateSecret(keySpec).getEncoded();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        hashedPw = sb.toString();
    }

    private byte[] createSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < salt.length; i++) {
            sb.append(Integer.toString((salt[i] & 0xff) + 0x100, 16).substring(1));
        }
        userSalt = sb.toString();
        return salt;
    }

    private void registerUserInFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("user_pw.txt"));
        StringBuilder sb = new StringBuilder();
        sb.append(user + ":" + hashedPw + ":" + userSalt + "\n");
        System.out.println(sb.toString());
        writer.write(sb.toString());
        writer.close();
    }    
}
