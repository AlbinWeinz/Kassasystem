package register_projekt;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class HashPW {

    public String hashPW(String pw, byte[] salt) throws InvalidKeySpecException, NoSuchAlgorithmException, UnsupportedEncodingException {
        KeySpec keySpec = new PBEKeySpec(pw.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] bytes = factory.generateSecret(keySpec).getEncoded();
        String hashedPw = Base64.getEncoder().encodeToString(bytes);
        
        return hashedPw;
    }
    
}
