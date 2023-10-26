package register_projekt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.HashMap;

public class CategoryTest {
    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();

    }
    @Test
    public void testAddToCategory() {
        Product product = new Product("Milk", 12.0);
        category.addToCategory(product);
        assertEquals(1, category.getProductCount());
    }

    @Test
    public void testAddProductToCategoryWithNullReference(){
        assertThrows(IllegalArgumentException.class,()->category.addToCategory(null));
    }

    @Test
    public void testAddProductToCategoryWithoutPrice(){
        Product product = new Product("product1", null);
        assertThrows(IllegalArgumentException.class,()->category.addToCategory(product));
    }

    @Test
    public void testAddProductToCategoryWithoutName(){
        Product product= new Product(null,10.0);
        assertThrows(IllegalArgumentException.class,()->category.addToCategory(product));
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
    public void testAddExistingProductWithDifferentPrice() {
        Category category = new Category();
        Product existingProduct = new Product("ExistingProduct", 15.0);
        category.addToCategory(existingProduct);
        assertNotEquals(0, category.getProductCount());
        Product productWithExistingName = new Product("ExistingProduct", 20.0);
        assertFalse(category.checkProductPriceAgainstExistingProduct(productWithExistingName));
    }
    @Test
    public void testProductPriceWithPositivePrice() {
        Category category = new Category();
        Product product = new Product("Product2", 10.0);
        assertTrue(category.checkProductPriceAgainstExistingProduct(product));
    }
    @Test
    public void testCheckProductPriceAgainstExistingProductWithNegativePrice() {
        Category category = new Category();
        Product product = new Product("Product1", -5.0);
        assertFalse(category.checkProductPriceAgainstExistingProduct(product));
    }

    @Test
    public void testRemoveFromCategory() {
        Category category = new Category();
        Product product1 = new Product("Product1", 10.0);
        Product product2 = new Product("Product2", 15.0);

        category.addToCategory(product1);
        category.addToCategory(product2);

        assertNotEquals(0, category.getProductCount());
        category.removeProductFromCategory(product1);

        assertNull(category.getProductPrice(product1.getProductName()));
        assertNotEquals(0, category.getProductCount());
    }
    @Test
    public void testRemoveProductFromEmptyCategory() {
        Category category = new Category();
        Product product = new Product("Product1", 10.0);
        assertEquals(0, category.getProductCount());
        assertThrows(IllegalStateException.class, () -> category.removeProductFromCategory(product));
    }

    @Test
    public void testEmptyCategory() {
        assertEquals(0, category.getProductCount());
    }
    @Test
    public void testCategoryContainsProduct() {
        Product product = new Product("Blueberry", 18.00);
        category.addToCategory(product);
        assertTrue(category.containsProduct("Blueberry"));
    }
    @Test
    public void testCategoryDoesNotContainProduct(){
        Product product = new Product("Strawberry", 25.50);
        category.addToCategory(product);
        assertFalse(category.containsProduct("Blackberry"));
    }
    @Test
    public void testClearCategory() {
        Product product1 = new Product("Milk", 12.0);
        category.addToCategory(product1);
        assertNotEquals(0, category.getProductCount());
        category.clearCategory();
        assertEquals(0, category.getProductCount());
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
    public void testUpdateProductPrice_ProductIsNotInCategory() {
        String productName = "NonExistingProduct";
        Double newPrice = 10.0;
        assertThrows(IllegalArgumentException.class,()-> category.updateProductPrice(productName,newPrice));
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
    public void testSortProductByName_CategoryIsEmpty(){
        category.categoryMap = new HashMap<>();
        assertThrows(IllegalStateException.class, () -> category.sortProductsByName());
    }
    @Test
    public void testSortProductByPrice_CategoryIsEmpty(){
        category.categoryMap = new HashMap<>();
        assertThrows(IllegalStateException.class, () -> category.sortProductsByPrice());
    }

}

