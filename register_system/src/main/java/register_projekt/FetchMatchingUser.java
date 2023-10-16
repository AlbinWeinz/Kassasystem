package register_projekt;

import java.sql.*;

public class FetchMatchingUser {

    private String userName;
    private String hashedPw;
    private String salt;
    private User user;


    public User fetchMatchingUser(String userToFetch) throws ClassNotFoundException, SQLException {
        CreateDBConnection createDBConnection = new CreateDBConnection();
        Connection con = createDBConnection.createDBConnection();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE user='" + userToFetch + "'");
        while (rs.next()) {
            userName = rs.getString("user");
            hashedPw = rs. getString("hash");
            salt = rs.getString("salt");
        }
        if (userName != null) {
            user = new User(userName, hashedPw, salt);
            return user;
        }
        else {
            throw new NullPointerException("No such user");
        }
    }    
}
