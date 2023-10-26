package register_projekt;
import java.util.Objects;

public class Product {

    public final String productName;
    public final Double productPrice;

    public Product(String productName, Double productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;

    }

    public String getProductName() {
        return this.productName;

    }
    public Double getProductPrice() {
        return this.productPrice;
    }

    public Money getTotalAmount(int quantity) {
        double total = productPrice * quantity;
        return new Money(total);
    }

    @Override
    public String toString() {
        return productName + "Price: " + productPrice;
    }

    @Override
    public boolean equals(Object product) {
        if (this == product) {
            return true;
        }
        if (product == null || getClass() != product.getClass()) {
            return false;
        }
        Product otherProduct = (Product) product;
        return Objects.equals(productName, otherProduct.productName) && Objects.equals(productPrice, otherProduct.productPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productPrice);
    }
}
