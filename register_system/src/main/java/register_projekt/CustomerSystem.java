package register_projekt;



import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Scanner;


public class CustomerSystem {

    private static Customers customerSystem = new Customers();

    public static void main(String[] args) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException, SQLException, ClassNotFoundException {


        Scanner scanner = new Scanner(System.in);
        Login login = new Login();
        User user = login.userNameField();
        boolean sucessfulLoggin = login.passwordField(user);

        if (sucessfulLoggin) {
            System.out.println("1 Sök på kund med namn");
            System.out.println("2 Sök på kund med mejl");
            System.out.println("3 Sök på kund med personnummer");
            System.out.println("4 Se alla kunder");
            System.out.println("5 Ta bort kund");
            System.out.println("6 Se kund data");
            System.out.println("0 Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 0 -> {
                    System.out.println("Exit!");
                    System.exit(0);
                }
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
                case 4 -> {
                    System.out.print(Customers.getAllCustomers());
                }

                case 5 -> {

                    System.out.println("Ange personnummer för att ta bort kunden:");
                    String personalNumberRemove = scanner.nextLine();
                    Customer customer = customerSystem.getCustomer(personalNumberRemove);

                    if (customerSystem.customerExist(customer)) {
                        customerSystem.removeCustomer(customer);
                    } else {
                        System.out.println("Ingen sådan kund");
                    }
                }

                case 6 -> {
                    System.out.print("Ange personnummer för kunddata: ");
                    String personalNumber = scanner.nextLine();
                    PurchasesHistory purchasesHistory = new PurchasesHistory();


                    double avgPurchases = purchasesHistory.averageAmountPerPurchase(personalNumber);

                    if (avgPurchases > 0) {
                        System.out.println(purchasesHistory.toString()); ///här är fel måste kolla
                    } else {
                        System.out.println("Ingen sådan kund");
                    }
                }

                default -> System.out.println("Ogiltigt!");
            }
            scanner.close();
        }
    }
}
   



