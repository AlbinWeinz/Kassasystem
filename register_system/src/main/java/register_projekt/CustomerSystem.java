package register_projekt;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerSystem {
        public static void main (String[]args) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException, SQLException, ClassNotFoundException {
            Scanner scanner= new Scanner(System.in);
            Login login= new Login();
            User user=login.userNameField();
            boolean sucessfulLoggin=login.passwordField(user);

            if(sucessfulLoggin){
                System.out.println("1 Search by Name");
                System.out.println("2 Search by Email");
                System.out.println("3 Search by Personal Number");
                System.out.println("4 View All Customers");
                System.out.println("0 Exit");
                System.out.print("Enter your choice: ");
                int choice= scanner.nextInt();
                switch (choice) {
                    case 1 -> {
                        System.out.print("Namn:");
                        String name = scanner.nextLine();
                        String result = Customers.nameSearch(name);
                        if (result.equals(name)) {
                            System.out.println(Customers.nameSearch(name));
                        } else {
                            System.out.println("Ingen sådan kund");
                        }
                    }
                    case 2 -> {
                        System.out.print("E-post:");
                        String email = scanner.nextLine();
                        String resultEmail = Customers.nameSearch(email);
                        if (resultEmail.equals(email)) {
                            System.out.println(Customers.nameSearch(email));
                        } else {
                            System.out.println("Ingen sådan kund");
                        }
                    }
                    case 3 -> {
                        System.out.print("Personnummer:");
                        String pn = scanner.nextLine();
                        String resultPn = Customers.nameSearch(pn);
                        if (resultPn.equals(pn)) {
                            System.out.println(Customers.nameSearch(pn));
                        } else {
                            System.out.println("Ingen sådan kund");
                        }
                    }
                    case 4 -> System.out.print(Customers.getAllCustomers());
                    default -> System.out.println("Ogiltigt!");
                }
                scanner.close();
            }
        }
    }


