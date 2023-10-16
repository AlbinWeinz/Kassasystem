package register_projekt;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertUserInDB {

    public void insertUserINDB(String userName, String hashedPW, String salt) throws ClassNotFoundException, SQLException {
        CreateDBConnection createDBConnection = new CreateDBConnection();
        Connection con = createDBConnection.createDBConnection();
        Statement statement = con.createStatement();
        statement.executeUpdate("INSERT INTO users (user, hash, salt) VALUES ('" + userName + "', '" + hashedPW + "', '" + salt + "')");
    }
    
}
