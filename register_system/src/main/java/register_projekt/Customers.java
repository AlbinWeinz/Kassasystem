package register_projekt;

import java.util.ArrayList;

import java.util.List;
///Det är bara hur långt jag har kommit, tänker mig ett medlemsystem som personalen använder
public class Customers {
    private List<Customer> customersList=new ArrayList<>();
    public Customer addCustomer(String name, String personalNumber, int age, double points, String membership, String email){
        Customer customer = new Customer(name, personalNumber, age, points, membership, email);
        customersList.add(customer);
        return customer;
    }
    public void removeCustomer(Customer customer){
        customersList.remove(customer);
    }

    public Customer updateCustomerEmail(String personalNumber, String newEmail){
       for (Customer customer: customersList){
           if(customer.getPersonalNumber().equals(personalNumber)){
               customer.setEmail(newEmail);
               return customer;
           }
       }
        return null;
    }

    public Customer updateCustomerName(String personalNumber, String newName){
        for (Customer customer: customersList){
            if(customer.getPersonalNumber().equals(personalNumber)){
                customer.setName(newName);
                return customer;
            }
        }
        return null;
    }

    public String nameSearch(String name){
        for (Customer customer: customersList){
            if (customer.getName().equalsIgnoreCase(name)){
                return customer.toString();
            }
        }
        return null;
    }

    public String emailSearch(String email){
        for (Customer customer: customersList){
            if (customer.getEmail().equalsIgnoreCase(email)){
                return customer.toString();
            }
        }
        return null;
    }

    public String personalNumberSearch(String personalNumber){
        for (Customer customer: customersList){
            if (customer.getPersonalNumber().equalsIgnoreCase(personalNumber)){
                return customer.toString();
            }
        }
        return null;
    }

    public List<String> getAllCustomers() {
        List<String> allCustomersList = new ArrayList<>();
        for (Customer customer : customersList) {
            allCustomersList.add(customer.toString());
        }
        return allCustomersList;  //fungerar detta?
    }

    /*public Customer getCustomer(String personalNumber){
        //loopa igenom, jämför , hitta, retrunera kund

    }*/

}
