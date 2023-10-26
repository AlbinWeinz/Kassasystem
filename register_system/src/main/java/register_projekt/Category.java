package register_projekt;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class Category {
    Map<String, Double> categoryMap;

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
        if(product==null) {
            throw new IllegalArgumentException("Product must exist");
        }
        String productName = product.getProductName();
        if(productName==null){
            throw new IllegalArgumentException("Product must have name");
        }
        Double productPrice= product.getProductPrice();
        if(productPrice==null){
            throw new IllegalArgumentException("Product must have a price");
        }
        if (categoryMap.containsKey(productName)) {
            return false;
        } else {
            categoryMap.put(productName, productPrice);
            return true;
        }
    }
    public boolean containsProduct(String productName) {
        return categoryMap.containsKey(productName);
    }

    public void updateProductPrice(String productName, Double newPrice) {
        if (categoryMap.containsKey(productName)) {
            categoryMap.put(productName, newPrice);
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }
    public void removeProductFromCategory(Product product) {
        if (categoryMap.isEmpty()) {
            throw new IllegalStateException("Category is empty. Cannot remove from an empty category.");
        }
        categoryMap.remove(product.getProductName());
    }
    public boolean checkProductPriceAgainstExistingProduct(Product product) {
        if (product.getProductPrice() < 0) {
            return false;
        }
        return !categoryMap.containsKey(product.getProductName());
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
        productEntries.sort(Map.Entry.comparingByValue());

        for (Map.Entry<String, Double> entry : productEntries) {
            String productName = entry.getKey();
            Double productPrice = entry.getValue();
            sortedProducts.add(new Product(productName, productPrice));
        }
        return sortedProducts;
    }
}


