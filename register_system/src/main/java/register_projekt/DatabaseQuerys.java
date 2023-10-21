package register_projekt;

import java.sql.*;


public class DatabaseQuerys {

    private User user;
    private Statement statement;
    private ResultSet rs;


    //update pw
    public Connection createDBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyDB", "root", "root");
        return con;
    }

    public User getAMatchingUser(Connection con, String userToFetch) throws ClassNotFoundException, SQLException {
        statement = con.createStatement();
        rs = statement.executeQuery("SELECT * FROM users WHERE UserName='" + userToFetch + "'");
        if (rs.next() == false)
        {
            return null;
        }
        user = new User(rs.getString("UserName"), rs. getString("Hash"), rs.getString("Salt"));
        return user; 
    }    

    public void insertUserInDB(Connection con, String userName, String hashedPW, String salt) throws ClassNotFoundException, SQLException {
        statement = con.createStatement();
        statement.executeUpdate("INSERT INTO users (UserName, Hash, Salt) VALUES ('" + userName + "', '" + hashedPW + "', '" + salt + "')");
    }
    
    
}
