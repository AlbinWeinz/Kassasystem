import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class CustomersTestfall {

    @Test
    void calculatePointsTest(){
        Customers customers=new Customers();
        double pointsTest=customers.calculatePoints(1000, 10,2);
        assertEquals((20.0),pointsTest); //Testing positiv value
    }
    //lägg till test negativ value and error message

    @Test
    void membershipStatusTest1(){
        Customers customers=new Customers();
        double points1=customers.calculatePoints(200, 4,2);
        String membership1=customers.membershipStatus(points1);
        assertEquals("New membership", membership1);
    }

    @Test
    void membershipStatusTest2(){
        Customers customers=new Customers();
        double points2=customers.calculatePoints(2000, 20,4);
        String membership2=customers.membershipStatus(points2);
        assertEquals("Gold", membership2);
    }

    @Test
    void membershipStatusTest3(){
        Customers customers=new Customers();
        double points3=customers.calculatePoints(1500, 6,3);
        String membership2=customers.membershipStatus(points3);
        assertEquals("Silver", membership2);
    }

    @Test
    void membershipStatusTest4(){
        Customers customers=new Customers();
        double points4=customers.calculatePoints(1000, 15,1);
        String membership4=customers.membershipStatus(points4);
        assertEquals("Bronze", membership4);
    }

    @Test
    void membershipStatusTest5(){
        Customers customers=new Customers();
        double points5=customers.calculatePoints(0, 0,0);
        String membership5=customers.membershipStatus(points5);
        assertEquals("Not a member", membership5);   //på grund av detta måste man automatiskt få över 0 poäng när man blir medlem
    }

    @Test
    void calculateDiscountTest1(){ //vet inte om de här testen är för lätta
        Customers customers=new Customers();
        assertEquals("10% rabatt", customers.calculateDiscount("Bronze"));
    }

    @Test
    void calculateDiscountTest2(){
        Customers customers=new Customers();
        assertEquals("20% rabatt", customers.calculateDiscount("Silver"));
    }

    @Test
    void calculateDiscountTest3(){
        Customers customers=new Customers();
        assertEquals("30% rabatt", customers.calculateDiscount("Gold"));
    }

    @Test
    void calculateDiscountTest4(){
        Customers customers=new Customers();
        assertEquals("5% rabatt", customers.calculateDiscount("New member"));
    }

    @Test
    void calculateDiscountTest5(){
        Customers customers=new Customers();
        assertEquals("0% rabatt", customers.calculateDiscount("Not a member"));
    }

    @Test ///får jobba vidare på denna eller kanske inte behöver denna
    void addCustomerTest(){
        Customers customers=new Customers();
        customers.addCustomer("Anna Carlsson", "199308143528", 30, 25.0, "Silver","anna@exempel.com");
        String customerAdded="Anna Carlsson, 199908143528, 30, 25.0, Silver,anna@exempel.com";
        String customerAddedThroughMethod= customers.getAllCustomers().toString();
        assertEquals(customerAdded,customerAddedThroughMethod);
    }
    @Test
    void removeCustomer(){

    }

    @Test
    void nameSearchTest(){
        Customers customers=new Customers();
        customers.addCustomer("Kalle Karlsson", "200008143528", 23, 15.6, "Bronze","kalle@exempel.com");
        customers.addCustomer("Göran Smith", "200308143528", 20, 0.6, "New member","goran@exempel.com");
    }

    @Test
    void emailSearchTest(){

    }

    @Test
    void personalNumberSearch(){

    }

    @Test
    void getAllCustomers(){

    }

    @Test
    void toStringTest(){

    }





}
