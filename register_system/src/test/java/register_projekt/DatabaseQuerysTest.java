package register_projekt;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseQuerysTest {

    private DatabaseQuerys databaseQuerys = new DatabaseQuerys();
    private User sampleUser = new User("Another User", "bS57Pyea2cF9299DljiLfQ==", "US1bWtstV3wh/51/NbNHRQ==");
    private User sampleUser2 = new User("Test User", "qEhfR5P3a3b1wPJKV0c2bA==", "jmkX7eFubvcBrGcsWHE1ig==");
    private Connection inMemoryCon;
    private Statement statement;

    @Before
    public void setUp() throws Exception {
        String jdbcURL = "jdbc:h2:mem:test";
        inMemoryCon= DriverManager.getConnection(jdbcURL);
        String sql = "Create table users (UserName varchar(255) primary key, Hash varchar(255), Salt varchar(255))";
        statement = inMemoryCon.createStatement();      
        statement.execute(sql);
        sql = "Insert into users (UserName, Hash, Salt) values ('Test User', 'qEhfR5P3a3b1wPJKV0c2bA==', 'jmkX7eFubvcBrGcsWHE1ig==')";       
        statement.executeUpdate(sql);
    }

    @After
    public void tearDown() throws SQLException {
        inMemoryCon.close();
        statement.close();
    }

    @Test
    public void matchingUserInDB() throws ClassNotFoundException, SQLException {
        assertEquals("Test User", databaseQuerys.getAMatchingUser(inMemoryCon, "Test User").getUserName());
    }

    @Test
    public void noMatchingUserInDB() throws ClassNotFoundException, SQLException {
        assertEquals(null, databaseQuerys.getAMatchingUser(inMemoryCon, "Another User"));
    }

    @Test
    public void insertUserInDB() throws ClassNotFoundException, SQLException {
        databaseQuerys.insertUserInDB(inMemoryCon, sampleUser.getUserName(), sampleUser.getHashedPw(), sampleUser.getSalt());
        ResultSet rs = statement.executeQuery("SELECT * FROM users");
        int count = 0;
        while (rs.next()) {{
            count++;
        }}
        rs.close();
        assertEquals(2, count);
    }

    @Test(expected = SQLException.class)
    public void insertUserInDBWithSameUserName() throws ClassNotFoundException, SQLException {
        databaseQuerys.insertUserInDB(inMemoryCon, sampleUser2.getUserName(), sampleUser2.getHashedPw(), sampleUser2.getSalt());
        ResultSet rs = statement.executeQuery("SELECT * FROM users");
        int count = 0;
        while (rs.next()) {{
            count++;
        }}
        rs.close();
        assertEquals(1, count);
    }
}
