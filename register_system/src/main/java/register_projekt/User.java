package register_projekt;

public class User {

    private String user;
    private String hashedPw;
    private String salt;

    public User() {

    }

    public User(String user, String hashedPw, String salt) {
        this.user = user;
        this.hashedPw = hashedPw;
        this.salt = salt;

    }

    public String getUser() {
        return user;
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

    public void setUser(String user) {
        this.user = user;
    }
}
