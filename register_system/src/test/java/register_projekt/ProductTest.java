import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void testProductInitialization() {
        Product product = new Product("Milk", 2.0);
        assertNotNull(product);
    }

    @Test
    public void testGetProductName() {
        Product product = new Product("Bread", 1.5);
        assertEquals("Bread", product.getProductName());
    }

    @Test
    public void testGetProductPrice() {
        Product product = new Product("Eggs", 3.0);
        assertEquals(3.0, product.getProductPrice(), 0.001);
    }

    @Test
    public void testToString() {
        Product product = new Product("Coffee", 5.0);
        assertEquals("CoffeePrice: 5.0", product.toString());
    }

    @Test
    public void testEquals() {
        Product product1 = new Product("Apple", 1.0);
        Product product2 = new Product("Apple", 1.0);
        Product product3 = new Product("Banana", 2.0);

        assertTrue(product1.equals(product2));
        assertFalse(product1.equals(product3));
    }

        @Test
        public void testHashCode () {
            Product product1 = new Product("Cheese", 4.0);
            Product product2 = new Product("Cheese", 4.0);
            Product product3 = new Product("Yogurt", 3.0);

            assertEquals(product1.hashCode(), product2.hashCode());
            assertNotEquals(product1.hashCode(), product3.hashCode());
        }

}
