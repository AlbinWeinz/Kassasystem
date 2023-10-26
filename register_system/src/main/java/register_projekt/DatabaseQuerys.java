package register_projekt;

import java.sql.*;

public class DatabaseQuerys {

    //update pw
    public Connection createDBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/MyDB", "root", "root");
    }

    public User getAMatchingUser(Connection con, String userToFetch) throws ClassNotFoundException, SQLException {
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE UserName='" + userToFetch + "'");
        if (rs.next() == false)
        {
            rs.close();
            return null;
        }
        User user = new User(rs.getString("UserName"), rs.getString("Hash"), rs.getString("Salt"));
        rs.close();
        statement.close();
        return user;
    }    

    public void insertUserInDB(Connection con, String userName, String hashedPW, String salt) throws ClassNotFoundException, SQLException {
        Statement statement = con.createStatement();
        statement.executeUpdate("INSERT INTO users (UserName, Hash, Salt) VALUES ('" + userName + "', '" + hashedPW + "', '" + salt + "')");
        statement.close();
    }
}
