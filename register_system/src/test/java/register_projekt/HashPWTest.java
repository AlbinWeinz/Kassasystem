package register_projekt;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.junit.Test;

public class HashPWTest {

    //createSalt() method under testing
    @Test
    public void isCorrectSize() throws NoSuchAlgorithmException{
        HashPW hashPW = new HashPW();
        assertEquals(16, hashPW.createSalt().length);
    }

    @Test 
    public void producesDifferentSalts() throws NoSuchAlgorithmException {
        HashPW hashPW = new HashPW();
        byte[] firstArr = hashPW.createSalt();
        byte[] secondArr = hashPW.createSalt();
        assertNotSame(firstArr, secondArr);
    }

    //hashPW() method under testing
    @Test
    public void samePWWithSameSalt() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
        HashPW hashPW = new HashPW();
        byte[] salt = hashPW.createSalt();
        String samplePW = "SamplePW1!";
        assertEquals(hashPW.hashPW(samplePW, salt), hashPW.hashPW(samplePW, salt));
    }

    @Test 
    public void samePWAndDifferentSalt() throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException {
        HashPW hashPW = new HashPW();
        byte[] firstSalt = hashPW.createSalt();
        byte[] secondSalt = hashPW.createSalt();
        String samplePW = "SamplePW1!";
        assertNotSame(hashPW.hashPW(samplePW, firstSalt), hashPW.hashPW(samplePW, secondSalt));
    }

    @Test
    public void differentPWWithSameSalt() throws InvalidKeySpecException, NoSuchAlgorithmException, UnsupportedEncodingException {
        HashPW hashPW = new HashPW();
        byte[] salt = hashPW.createSalt();
        String firstSamplePW = "SamplePW1!";
        String secondSamplePW = "NotTheSamePW";
        assertNotSame(hashPW.hashPW(firstSamplePW, salt), hashPW.hashPW(secondSamplePW, salt));
    }

    @Test
    public void differentPWAndDifferentSalt() throws InvalidKeySpecException, NoSuchAlgorithmException, UnsupportedEncodingException {
        HashPW hashPW = new HashPW();
        byte[] firstSalt = hashPW.createSalt();
        byte[] secondSalt = hashPW.createSalt();
        String firstSamplePW = "SamplePW1!";
        String secondSamplePW = "NotTheSamePW";
        assertNotSame(hashPW.hashPW(firstSamplePW, firstSalt), hashPW.hashPW(secondSamplePW, secondSalt));
    }

    @Test(expected = NullPointerException.class)
    public void nullStringArg() throws InvalidKeySpecException, NoSuchAlgorithmException, UnsupportedEncodingException {
        HashPW hashPW = new HashPW();
        String nullString = null;
        byte[] createSalt = hashPW.createSalt();
        hashPW.hashPW(nullString, createSalt);      
    }

    @Test(expected = NullPointerException.class)
    public void nullArrayArg() throws InvalidKeySpecException, NoSuchAlgorithmException, UnsupportedEncodingException {
        HashPW hashPW = new HashPW();
        String samplePW = "SamplePW1!";
        byte[] nullSalt = null;
        hashPW.hashPW(samplePW, nullSalt);
    }
}
