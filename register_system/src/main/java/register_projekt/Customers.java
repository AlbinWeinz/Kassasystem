package register_projekt;

import java.util.ArrayList;

import java.util.List;
///Det är bara hur långt jag har kommit, tänker mig ett medlemsystem som personalen använder
public class Customers {
    private static List<Customer> customersList=new ArrayList<>();
    public static Customer addCustomer(String name, String personalNumber, int age, double points, String membership, String email){
        Customer customer = new Customer(name, personalNumber, age, points, membership, email);
        customersList.add(customer);
        return customer;
    }
    public static void removeCustomer(Customer customer){
        customersList.remove(customer);
        String hej = "H";
    }

    public static Customer updateCustomerEmail(String personalNumber, String newEmail){
       for (Customer customer: customersList){
           if(customer.getPersonalNumber().equals(personalNumber)){
               customer.setEmail(newEmail);
               return customer;
           }
       }
        return null;
    }

    public static Customer updateCustomerName(String personalNumber, String newName){
        for (Customer customer: customersList){
            if(customer.getPersonalNumber().equals(personalNumber)){
                customer.setName(newName);
                return customer;
            }
        }
        return null;
    }

    public static String nameSearch(String name){
        for (Customer customer: customersList){
            if (customer.getName().equalsIgnoreCase(name)){
                return customer.toString();
            }
        }
        return null;
    }

    public static String emailSearch(String email){
        for (Customer customer: customersList){
            if (customer.getEmail().equalsIgnoreCase(email)){
                return customer.toString();
            }
        }
        return null;
    }

    public static String personalNumberSearch(String personalNumber){
        for (Customer customer: customersList){
            if (customer.getPersonalNumber().equalsIgnoreCase(personalNumber)){
                return customer.toString();
            }
        }
        return null;
    }

    public static boolean customerExist(Customer customerExist){
        for (Customer customer: customersList){
            if (customer.equals(customerExist)){
                return true;
            }
        }
       return false;
    }
    public static List<String> getAllCustomers() {
        List<String> allCustomersList = new ArrayList<>();
        for (Customer customer : customersList) {
            allCustomersList.add(customer.toString());
        }
        return allCustomersList;  //fungerar detta?
    }


    public static Customer getCustomer(String personalNumber){
        for (Customer customer: customersList){
            if (customer.getPersonalNumber().equalsIgnoreCase(personalNumber)){
                return customer;
            }
        }
        return null;
    }

}
