package register_projekt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {
    @Test
    void calculatePointsTest(){
        Customer customer = new Customer();
        double pointsTest=customer.calculatePoints(1000, 10,2);
        assertEquals((20.0),pointsTest); //Testing positiv value
    }
    //lägg till test negativ value and error message

    @Test
    void membershipStatusTest1(){
        Customer customer=new Customer();
        double points1=customer.calculatePoints(200, 4,2);
        String membership1=customer.membershipStatus(points1);
        assertEquals("New membership", membership1);
    }

    @Test
    void membershipStatusTest2(){
        Customer customer=new Customer();
        double points2=customer.calculatePoints(2000, 20,4);
        String membership2=customer.membershipStatus(points2);
        assertEquals("Gold", membership2);
    }

    @Test
    void membershipStatusTest3(){
        Customer customer=new Customer();
        double points3=customer.calculatePoints(1500, 6,3);
        String membership2=customer.membershipStatus(points3);
        assertEquals("Silver", membership2);
    }

    @Test
    void membershipStatusTest4(){
        Customer customer=new Customer ();
        double points4=customer.calculatePoints(1000, 15,1);
        String membership4=customer.membershipStatus(points4);
        assertEquals("Bronze", membership4);
    }

    @Test
    void membershipStatusTest5(){
        Customer customer=new Customer();
        double points5=customer.calculatePoints(0, 0,0);
        String membership5=customer.membershipStatus(points5);
        assertEquals("Not a member", membership5);   //på grund av detta måste man automatiskt få över 0 poäng när man blir medlem
    }

    @Test
    void calculateDiscountTest1(){ //vet inte om de här testen är för lätta
        Customer customer=new Customer();
        assertEquals("10% rabatt", customer.calculateDiscount("Bronze"));
    }

    @Test
    void calculateDiscountTest2(){
        Customer customer=new Customer();
        assertEquals("20% rabatt", customer.calculateDiscount("Silver"));
    }

    @Test
    void calculateDiscountTest3(){
        Customer customer=new Customer();
        assertEquals("30% rabatt", customer.calculateDiscount("Gold"));
    }

    @Test
    void calculateDiscountTest4(){
        Customer customer=new Customer();
        assertEquals("5% rabatt", customer.calculateDiscount("New member"));

    }

    @Test
    void calculateDiscountTest5(){
        Customer customer=new Customer();
        assertEquals("0% rabatt", customer.calculateDiscount("Not a member"));
    }

}
