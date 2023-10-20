package register_projekt;

public class Customer {
    protected String personalNumber;
    private String name;
    private String email;
    private int age; // använder inte riktigt ålder, men har kvar den om den behövs i andra klasser
    protected double points;
    private String membership;
    protected int amountOfPurchases;
    protected int amountSpent;
    protected int yearsAsMember;

    public Customer( String name, String personalNumber, int age, double points, String membership, String email){
        this.name=name;
        this.age=age;
        this.personalNumber=personalNumber;
        this.membership=membership;
        this.points=points;
        this.email=email;
    }

    public Customer(){
        this.amountSpent=amountSpent;
        this.amountOfPurchases=amountOfPurchases;
        this.yearsAsMember=yearsAsMember;
    }

    public Customer(int amountSpent,int amountOfPurchases, int yearsAsMember){
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
    @Override
    public String toString(){
        return "Ditt medlemskap: " +
                "\nNamn: " + name +
                "\nPersonnummer: " + personalNumber +
                "\nÅlder: " + age +
                "\nMejladress: " + email +
                "\nMedlemskap: " + membership +
                "\nPoäng: " + points +
                "\nRabatt: " + calculateDiscount(membership);

    }
    public void setEmail(String newEmail) {
        this.email=newEmail;
    }

    public void setName(String newName) {
        this.name=name;
    }
}
