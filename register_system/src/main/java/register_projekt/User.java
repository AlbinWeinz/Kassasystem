package register_projekt;

public class User {

    private String userName;
    private String hashedPw;
    private String salt;

    public User() {

    }

    public User(String userName, String hashedPw, String salt) {
        this.userName = userName;
        this.hashedPw = hashedPw;
        this.salt = salt;

    }

    public String getUserName() {
        return userName;
    }

    public String getHashedPw() {
        return hashedPw;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setHashedPw(String hashedPw) {
        this.hashedPw = hashedPw;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
