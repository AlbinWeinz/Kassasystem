package register_projekt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
///Det är bara hur långt jag har kommit, tänker mig ett medlemsystem som personalen använder
public class Customers {
    protected String personalNumber;
    private String name;
    private String email;
    private int age; // använder inte riktigt ålder, men har kvar den om den behövs i andra klasser
    protected double points;
    private String membership;
    protected int amountOfPurchases;
    protected int amountSpent;
    protected int yearsAsMember;
    private List<Customers> customersList=new ArrayList<>();


    public Customers( String name, String personalNumber, int age, double points, String membership, String email){
        this.name=name;
        this.age=age;
        this.personalNumber=personalNumber;
        this.membership=membership;
        this.points=points;
        this.email=email;
    }

    public Customers(){ ///kanske inte behövs
        this.amountSpent=amountSpent;
        this.amountOfPurchases=amountOfPurchases;
        this.yearsAsMember=yearsAsMember;
    }

    public String getPersonalNumber(){
        return personalNumber;
    }

   public String getName(){
        return name;
   }

   public String getEmail(){
        return email;
   }

    public double calculatePoints(int amountSpent, int amountOfPurchases, int yearsAsMember){
        points=(amountOfPurchases * 0.1) * (amountSpent * 0.05) * ( yearsAsMember * 0.2);
        return points;
    }

    public String membershipStatus(double points){
        //double points = calculatePoints(amountSpent, amountOfPurchases, yearsAsMember);
        if (points>30){
            membership="Gold";
            return membership;
        }else if (points>20){
            membership="Silver";
            return membership;
        }else if(points>10){
            membership="Bronze";
            return membership;
        }else if (points>0){
            membership="New membership";
            return membership;
        }else{
            return "Not a member";
        }

    }

    public String calculateDiscount(String membership){
        int discount;
        discount = switch (membership) {
            case "Bronze" -> 10;
            case "Silver" -> 20;
            case "Gold" -> 30;
            case "New member" -> 5;
            default -> 0;
        };
        return discount + "% rabatt"; // kanske onödigt att skriva ut %
    }
    public void addCustomer(String name, String personalNumber, int age, double points, String membership, String email){
        Customers customer =new Customers(name, personalNumber, age, points, membership, email);
        customersList.add(customer);
    }
    public void removeCustomer(String name, String personalNumber, int age, double points, String membership, String email){
        Customers customer =new Customers(name, personalNumber, age, points, membership, email);
        customersList.remove(customer);
    }


    public String nameSearch(String name){
        for (Customers customer: customersList){
            if (customer.getName().equalsIgnoreCase(name)){
                return customer.toString();
            }
        }
        return null;
    }

    public String emailSearch(String email){
        for (Customers customer: customersList){
            if (customer.getEmail().equalsIgnoreCase(email)){
                return customer.toString();
            }
        }
        return null;
    }

    public String personalNumberSearch(String personalNumber){
        for (Customers customer: customersList){
            if (customer.getPersonalNumber().equalsIgnoreCase(personalNumber)){
                return customer.toString();
            }
        }
        return null;
    }

    public List<String> getAllCustomers() {
        List<String> allCustomersList=new ArrayList<>();
        for(Customers customer:customersList){
            allCustomersList.add(customer.toString());
        }
        return Collections.singletonList(allCustomersList.toString());  //fungerar detta?
    }

    @Override
    public String toString(){
        return "Ditt medlemskap: " +
                "\nNamn: " + name +
                "\nPersonnummer: " + personalNumber +
                "\nÅlder: " + age +
                "\nMejladress: " + email +
                "\nMedlemskap: " + membership +
                "\nPoäng: " + points +
                "\nRabatt:" + calculateDiscount(membership) + "%";
    }

}
