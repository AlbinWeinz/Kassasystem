package register_projekt;
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
    public void testEqualProducts() {
        Product product1 = new Product("Apple", 1.0);
        Product product2 = new Product("Apple", 1.0);
        assertEquals(product1, product2);

    }
    @Test
    public void testProductEqualsItself(){
        Product product=new Product("Orange",3.5);
        assertTrue(product.equals(product));
    }
    @Test
    public void testNonEqualProducts(){
        Product product=new Product("Corn",5.0);
        Product product1=new Product("Ham", 15.50);
        assertNotEquals(product,product1);
    }

    @Test
    public void testProductComparedToNull(){
        Product product=new Product("Lettuce", 23.0);
        assertNotEquals(product,null);
    }

    @Test
    public void testDoesNotEqualDifferentObject(){
        Product product=new Product("product1",2.0);
        Category category=new Category();
        assertNotEquals(product,category);
    }



    @Test
    public void testHashCode () {
        Product product1 = new Product("Cheese", 4.0);
        Product product2 = new Product("Cheese", 4.0);
        Product product3 = new Product("Yogurt", 3.0);

        assertEquals(product1.hashCode(), product2.hashCode());
        assertNotEquals(product1.hashCode(), product3.hashCode());
    }
    @Test
    public void testGetTotalAmount(){
        Product product= new Product("product1", 5.0);
        int quantity=5;
        Money totalAmount= product.getTotalAmount(quantity);
        double expectedTotal= 5.0*5;
        assertEquals(expectedTotal,totalAmount.getAmount(),0.0001);

    }


}
