package register_projekt;

import java.util.Scanner;

public class CashRegister {
        public static void main(String[] args) {
            Category category = new Category();
            Scanner scanner = new Scanner(System.in);
            boolean isRunning = true;

            while (isRunning) {
                System.out.println("\nCash Register Menu:");
                System.out.println("1. add product to category");
                System.out.println("2. Check price of a product by name");
                System.out.println("3  Add customer");
                System.out.println("4. Search for customer by name");
                System.out.println("5. Calculate discount");
                System.out.println("6. Check if product has age restriction");
                System.out.println("7. Calculate product price");
                System.out.println("8. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter product name: ");
                        String productName = scanner.next();
                        System.out.print("Enter price of product");
                        Double productPrice = scanner.nextDouble();
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
                        String Name = scanner.next();
                        System.out.print("Enter customers personalnumber");
                        String personalNumber = scanner.next();
                        System.out.print("Enter customers age ");
                        int age = scanner.nextInt();
                        System.out.print(" Enter points");
                        double points= scanner.nextDouble();
                        System.out.print("Enter membership");
                        String membership= scanner.next();
                        System.out.print(" Enter customers emali");
                        String email = scanner.next();

                        Customer customer = new Customer(Name, personalNumber, age, points, membership, email);
                        Customers.addCustomer(customer); // addcustomer är non static och can inte be referenced
                        System.out.print("Customer added");
                    }
                    case 4 -> {
                        System.out.print(" Enter customer name");
                        String customerName = scanner.next();
                        String name = Customer.getName();
                        Customers.nameSearch();
                    }
                    case 8 -> isRunning = false;
                    default -> System.out.println("Invalid choice. Please enter a valid option.");
                }
            }

            System.out.println("Cash Register session ended. Thank you!");
        }
    }

