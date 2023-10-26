package register_projekt;

import java.util.Locale;
import java.util.Scanner;

public class CashRegister {
    public static void main(String[] args) {
        Category category = new Category();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nCash Register Menu:");
            System.out.println("1. Add product to category");
            System.out.println("2. Check price of a product by name");
            System.out.println("3  Add customer");
            System.out.println("4. Search for customer by name");
            System.out.println("5. Update purchase history");
            System.out.println("6. Calculate total amount");
            System.out.println("7. Payment");
            System.out.println("8. Exist");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter product name: ");
                    String productName = scanner.next();
                    scanner.useLocale(Locale.US);
                    System.out.print("Enter price of product: ");
                    double productPrice = scanner.nextDouble();
                    Product product = new Product(productName, productPrice);
                    category.addToCategory(product);
                    System.out.println("Product added");
                }
                case 2 -> {
                    System.out.print("Enter the name of the product: ");
                    String productToCheck = scanner.next();
                    Double price = category.getProductPrice(productToCheck);
                    if (price != null) {
                        System.out.println("The price of " + productToCheck + " is: " + price);
                    } else {
                        System.out.println("Product not found.");
                    }
                }
                case 3 -> {
                    System.out.print("Enter customer name");
                    String name = scanner.next();
                    System.out.print("Enter customers personal number");
                    String personalNumber = scanner.next();
                    System.out.print("Enter customers age ");
                    int age = scanner.nextInt();
                    System.out.print("Enter points");
                    double points = scanner.nextDouble();
                    System.out.print("Enter membership");
                    String membership = scanner.next();
                    System.out.print("Enter customers email");
                    String email = scanner.next();

                    Customers customers = new Customers();
                    customers.addCustomer(name, personalNumber, age, points, membership, email);
                    System.out.println("Customer added");
                }
                case 4 -> {
                    System.out.print("Enter customer name: ");
                    String name = scanner.next();
                    String customerName = Customers.nameSearch(name);
                    if (customerName != null) {
                        System.out.println("Customer found: " + customerName);
                    } else {
                        System.out.println("Customer not found.");
                    }
                }
                case 5 -> {
                    System.out.print("Enter customer's personal number: ");
                    String personalNumber = scanner.next();
                    Customers customers = new Customers();
                    String customerInfo = customers.personalNumberSearch(personalNumber);

                    if (customerInfo != null) {
                        System.out.print("Enter new years as member: ");
                        int newYearsAsMember = scanner.nextInt();

                        System.out.print("Enter new amount spent: ");
                        int newAmountSpent = scanner.nextInt();

                        System.out.print("Enter new amount of purchases: ");
                        int newAmountOfPurchases = scanner.nextInt();

                        PurchasesHistory purchasesHistory = new PurchasesHistory();
                        purchasesHistory.updatePurchasesHistory(personalNumber, newYearsAsMember, newAmountSpent, newAmountOfPurchases);

                    } else {
                        System.out.println("Customer not found.");
                    }
                }
                case 6 -> {
                    System.out.println("Calculate Total Amount for a Product");
                    System.out.print("Enter product name: ");
                    String productName = scanner.next();
                    Double productPrice = category.getProductPrice(productName);

                    if (productPrice != null) {
                        System.out.print("Enter quantity: ");
                        int quantity = scanner.nextInt();

                        Product product = new Product(productName, productPrice);
                        Money totalAmount = product.getTotalAmount(quantity);

                        System.out.println("Total cost for " + quantity + " " + productName + " is: " + totalAmount);
                    } else {
                        System.out.println("Product not found.");
                    }

                }
                case 7 -> {
                    System.out.print("Payment");
                    double paymentAmount = scanner.nextDouble();
                    CheckOut checkOut = new CheckOut(amount -> amount > 0);
                    try {
                        boolean paymentSuccess = checkOut.processPayment(paymentAmount);
                        if (paymentSuccess) {
                            System.out.print("Payment was successful");
                        } else {
                            System.out.print("Payment failed");
                        }
                    } catch (PaymentFailedException e) {
                        System.out.print("Payment failed" + e.getMessage());
                    }
                }
                case 8 -> isRunning = false;
                default -> System.out.println("Invalid choice. Please enter a valid option.");
            }
            System.out.print("Cash Register session ended. Thank you!");
        }

    }
}


