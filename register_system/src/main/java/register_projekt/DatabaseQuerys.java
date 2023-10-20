package register_projekt;

import java.sql.*;

public class DatabaseQuerys {

    private User user;


        //update pw
    private Connection createDBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "test");
        return connection;
    }

    public User createAMatchingUser(String userToFetch) throws ClassNotFoundException, SQLException {
        Connection con = createDBConnection();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM user WHERE UserName='" + userToFetch + "'");
        if (rs.next() == false)
        {
            throw new NullPointerException("No such user");
        }
        else {
            user = new User(rs.getString("UserName"), rs. getString("Hash"), rs.getString("Salt"));
            return user;
        }
    }    

    public boolean checkIfuserExits(String userToCheck) throws SQLException, ClassNotFoundException {
        Connection con = createDBConnection();
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM user WHERE UserName='" + userToCheck + "'");
        if (rs.next() == false)
        {
            return false;
        }     
        return true;
    }

    public void insertUserInDB(String userName, String hashedPW, String salt) throws ClassNotFoundException, SQLException {
        Connection con = createDBConnection();
        Statement statement = con.createStatement();
        statement.executeUpdate("INSERT INTO user (UserName, Hash, Salt) VALUES ('" + userName + "', '" + hashedPW + "', '" + salt + "')");
    }
    
    
}
