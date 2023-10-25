package register_projekt;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CashRegisterTest {

    private CashRegister cashRegister;
    private ByteArrayOutputStream outputStream;
    private InputStream inputStream;

    @BeforeEach
    public void setUp() {
        cashRegister = new CashRegister();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        inputStream = System.in;
    }

    @AfterEach
    public void tearDown() {
        System.setIn(inputStream);
        System.setOut(System.out);
    }

    @Test
    public void testAddProductToCategory() {
        ByteArrayInputStream in = new ByteArrayInputStream("1\nProduct1\n10.0\n7\n".getBytes());
        System.setIn(in);
        cashRegister.main(new String[0]);
        String output = outputStream.toString();
        assertTrue(output.contains("Product added"));
    }

    @Test
    public void testCheckPriceOfProduct() {
        // Add a product to the category first
        cashRegister.main(new String[]{"1", "Product1", "10.0", "7"});

        // Provide input to check the price
        ByteArrayInputStream in = new ByteArrayInputStream("2\nProduct1\n7\n".getBytes());
        System.setIn(in);

        cashRegister.main(new String[0]);

        // Verify that the price is displayed
        String output = outputStream.toString();
        assertTrue(output.contains("The price of Product1 is: 10.0"));
    }

    @Test
    public void testAddCustomer() {
        ByteArrayInputStream in = new ByteArrayInputStream("3\nJohn\n12345\n30\n100.0\nGold\njohn@example.com\n7\n".getBytes());
        System.setIn(in);

        cashRegister.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("Customer added"));
    }

    @Test
    public void testSearchForCustomerByName() {
        cashRegister.main(new String[]{"3", "John", "12345", "30", "100.0", "Gold", "john@example.com", "7"});

        ByteArrayInputStream in = new ByteArrayInputStream("4\nJohn\n7\n".getBytes());
        System.setIn(in);

        cashRegister.main(new String[0]);

        String output = outputStream.toString();
        assertTrue(output.contains("Customer found: John"));
    }

    @Test
    public void testUpdatePurchaseHistory() {
        cashRegister.main(new String[]{"3", "John", "12345", "30", "100.0", "Gold", "john@example.com", "7"});

        // Provide input to update the purchase history
        ByteArrayInputStream in = new ByteArrayInputStream("5\n12345\n5\n2\n500\n10\n7\n".getBytes());
        System.setIn(in);

        cashRegister.main(new String[0]);

        // Verify that the purchase history is updated
        String output = outputStream.toString();
        assertTrue(output.contains("Purchase history updated"));
    }

    @Test
    public void testCalculateTotalAmount() {
        // Add a product first
        cashRegister.main(new String[]{"1", "Product1", "10.0", "7"});

        // Provide input to calculate the total amount
        ByteArrayInputStream in = new ByteArrayInputStream("6\nProduct1\n5\n7\n".getBytes());
        System.setIn(in);

        cashRegister.main(new String[0]);

        // Verify that the total amount is calculated
        String output = outputStream.toString();
        assertTrue(output.contains("Total cost for 5 Product1 is: 50.0"));
    }

    @Test
    public void testExitCashRegister() {
        ByteArrayInputStream in = new ByteArrayInputStream("7\n".getBytes());
        System.setIn(in);
        cashRegister.main(new String[0]);
        String output = outputStream.toString();
        assertTrue(output.contains("Cash Register session ended. Thank you!"));
    }

    @Test
    public void testInvalidChoice() {
        ByteArrayInputStream in = new ByteArrayInputStream("8\n7\n".getBytes());
        System.setIn(in);
        cashRegister.main(new String[0]);
        String output = outputStream.toString();
        assertTrue(output.contains("Invalid choice. Please enter a valid option."));
    }

    @Test
    public void testProductNotFound() {
        ByteArrayInputStream in = new ByteArrayInputStream("2\nNonexistentProduct\n7\n".getBytes());
        System.setIn(in);
        cashRegister.main(new String[0]);
        String output = outputStream.toString();
        assertTrue(output.contains("Product not found."));
    }

    @Test
    public void testCustomerNotFound() {
        ByteArrayInputStream in = new ByteArrayInputStream("4\nNonexistentCustomer\n7\n".getBytes());
        System.setIn(in);
        cashRegister.main(new String[0]);
        String output = outputStream.toString();
        assertTrue(output.contains("Customer not found."));
    }
}
