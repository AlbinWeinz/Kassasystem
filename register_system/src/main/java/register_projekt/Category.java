package register_projekt;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class Category {
    Map<String, Double> categoryMap= new HashMap<>();

    public Category(){
        categoryMap=new HashMap<>();
    }

    public int getProductCount() {
        return categoryMap.size();
    }
    public Double getProductPrice(String productName) {
        return categoryMap.get(productName);
    }
    public boolean addToCategory(Product product) {
        String productName = product.getProductName();
        if (categoryMap.containsKey(productName)) {
            return false;
        } else {
            categoryMap.put(productName, product.getProductPrice());
            return true;
        }
    }

    public boolean containsProduct(String productName) {
        return categoryMap.containsKey(productName);
    }

    public void updateProductPrice(String productName, Double newPrice) {
        if (categoryMap.containsKey(productName)) {
            categoryMap.put(productName, newPrice);
            System.out.println("Price of " + productName + " updated to " + newPrice);
        } else {
            System.out.println("Product not found: " + productName);
        }
    }
    public void removeProductFromCategory(Product product) {
        if (categoryMap.isEmpty()) {
            throw new IllegalStateException("Category is empty. Cannot remove from an empty category.");
        }
        categoryMap.remove(product.getProductName());
    }
    public boolean validProductPrice(Product product) {
        if (product.getProductPrice() < 0) {
            System.out.println("Invalid product price: Price cannot be negative.");
            return false;
        }

        if (categoryMap.containsKey(product.getProductName())) {
            System.out.println("Product with the same name already exists in the category.");
            return false;
        }

        return true;
    }

    public void clearCategory() {
        categoryMap.clear();
    }
    public List<Product> sortProductsByName() {
        if (categoryMap.isEmpty()) {
            throw new IllegalStateException("Cannot sort an empty category.");
        }
        List<Product> sortedProducts = new ArrayList<>();
        List<String> productNames = new ArrayList<>(categoryMap.keySet());
        Collections.sort(productNames);

        for (String productName : productNames) {
            Double productPrice = categoryMap.get(productName);
            Product product=new Product(productName, productPrice);
            sortedProducts.add(product);
        }

        return sortedProducts;
    }

    public List<Product> sortProductsByPrice() {
        if (categoryMap.isEmpty()) {
            throw new IllegalStateException("Cannot sort an empty category.");
        }
        List<Product> sortedProducts = new ArrayList<>();
        List<Map.Entry<String, Double>> productEntries = new ArrayList<>(categoryMap.entrySet());
        Collections.sort(productEntries, Comparator.comparing(Map.Entry::getValue));

        for (Map.Entry<String, Double> entry : productEntries) {
            String productName = entry.getKey();
            Double productPrice = entry.getValue();
            sortedProducts.add(new Product(productName, productPrice));
        }

        return sortedProducts;
    }
    public void categoryError(Exception e) {
        System.err.println("An error occurred: " + e.getMessage());
        e.printStackTrace();
    }


}


