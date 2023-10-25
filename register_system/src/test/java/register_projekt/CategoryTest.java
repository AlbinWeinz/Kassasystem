package register_projekt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;


public class CategoryTest {
    private Category category;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setUp() {
        category = new Category();
        outputStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setErr(System.err);

    }


    @Test
    public void testAddToCategory() {
        Product product = new Product("Milk", 12.0);
        category.addToCategory(product);
        assertEquals(1, category.getProductCount());
    }

    @Test
    public void testAddMoreThanOne() {
        Product product1 = new Product("Lollipop", 8.90);
        Product product2 = new Product("Chocolate", 23.50);
        Product product3 = new Product("Cake", 199.90);

        category.addToCategory(product1);
        category.addToCategory(product2);
        category.addToCategory(product3);

        assertEquals(3, category.getProductCount());
    }
    @Test
    public void testAddDuplicates() {
        Product product1 = new Product("Blueberry", 18.00);
        Product product2 = new Product("Blueberry", 18.00);
        assertTrue(category.addToCategory(product1));
        assertFalse(category.addToCategory(product2));
    }

    @Test
    public void testValidProductPriceWithExistingName() {
        Category category = new Category();
        Product existingProduct = new Product("ExistingProduct", 15.0);
        category.addToCategory(existingProduct);
        assertFalse(category.getProductCount() == 0);
        Product productWithExistingName = new Product("ExistingProduct", 20.0);
        assertFalse(category.validProductPrice(productWithExistingName));
    }
    @Test
    public void testValidProductPriceWithPositivePrice() {
        Category category = new Category();
        Product product = new Product("Product2", 10.0);
        assertTrue(category.validProductPrice(product));
    }
    @Test
    public void testValidProductPriceWithNegativePrice() {
        Category category = new Category();
        Product product = new Product("Product1", -5.0);
        assertFalse(category.validProductPrice(product));
    }

    @Test
    public void testRemoveFromCategory() {
        Category category = new Category();
        Product product1 = new Product("Product1", 10.0);
        Product product2 = new Product("Product2", 15.0);

        category.addToCategory(product1);
        category.addToCategory(product2);

        assertFalse(category.getProductCount() == 0);
        category.removeProductFromCategory(product1);

        assertNull(category.getProductPrice(product1.getProductName()));
        assertFalse(category.getProductCount() == 0);
    }
    @Test
    public void testRemoveProductFromEmptyCategory() {
        Category category = new Category();
        Product product = new Product("Product1", 10.0);
        assertTrue(category.getProductCount() == 0);
        assertThrows(IllegalStateException.class, () -> {
            category.removeProductFromCategory(product);
        });
    }

    @Test
    public void testEmptyCategory() {
        assertTrue(category.getProductCount() == 0);
    }
    @Test
    public void testCategoryContainsProduct() {
        Product product = new Product("Blueberry", 18.00);
        category.addToCategory(product);
        assertTrue(category.containsProduct("Blueberry"));
    }
    @Test
    public void testClearCategory() {
        Product product1 = new Product("Milk", 12.0);
        category.addToCategory(product1);
        assertFalse(category.getProductCount() == 0);
        category.clearCategory();
        assertTrue(category.getProductCount() == 0);
    }

    @Test
    public void testUpdateProductPrice() {
        Product product = new Product("Milk", 12.50);
        category.addToCategory(product);
        Double newPrice = 10.0;
        category.updateProductPrice("Milk", newPrice);
        assertEquals(newPrice, category.getProductPrice("Milk"));
    }
    @Test
    public void testUpdateProductPrice_ProductNotFound() {
        String productName = "Product not found";
        Double newPrice = 10.0;
        Category category1= new Category();
        Category updatePrice= category1;
        assertNotEquals(productName, newPrice);
    }
    @Test
    public void testSortProductsByName() {
        Product product1 = new Product("Banana", 5.50);
        Product product2 = new Product("Apple", 3.50);
        Product product3 = new Product("Orange", 1.20);
        category.addToCategory(product1);
        category.addToCategory(product2);
        category.addToCategory(product3);
        List<Product> sortedProducts = category.sortProductsByName();
        assertEquals(3, sortedProducts.size());
        assertEquals("Apple", sortedProducts.get(0).getProductName());
        assertEquals("Banana", sortedProducts.get(1).getProductName());
        assertEquals("Orange", sortedProducts.get(2).getProductName());
    }
    @Test
    public void testSortProductsByPrice() {
        Product product1 = new Product("Banana", 1.0);
        Product product2 = new Product("Apple", 0.5);
        category.addToCategory(product1);
        category.addToCategory(product2);
        List<Product> sortedProducts = category.sortProductsByPrice();
        assertEquals(2, sortedProducts.size());
        assertEquals("Apple", sortedProducts.get(0).getProductName());
        assertEquals("Banana", sortedProducts.get(1).getProductName());
    }
    @Test
    public void testCategoryError() {
        Exception testException = new Exception("Test Exception Message");
        category.categoryError(testException);
        String systemOutput = outputStream.toString();
        assertTrue(systemOutput.contains("An error occurred: Test Exception Message"));
        assertTrue(systemOutput.contains("java.lang.Exception"));
    }
}

