package register_projekt;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PurchasesHistoryTest {
    @Test
    void updatePurchasesHistoryTest(){
        Customer customer=new Customer("Anna Carlsson", "200405173826", 30, 25.0, "Silver", "anna@exempel.com");
        PurchasesHistory purchasesHistory=new PurchasesHistory();
        String personalNumber="200405173826";
        int newYearsAsMember = 1;
        int newAmountSpent = 200;
        int newAmountOfPurchases = 5;
        Customer updatedCustomer=purchasesHistory.updatePurchasesHistory(personalNumber, newYearsAsMember, newAmountSpent, newAmountOfPurchases);
        assertEquals(6, updatedCustomer.getAmountOfPurchases());
        assertEquals(200, updatedCustomer.getAmountSpent());
        assertEquals(1, updatedCustomer.getYearsAsMember());
    }

    @Test
    void averageAmountPerCustomerTest(){
        new Customer();
        PurchasesHistory purchasesHistory=new PurchasesHistory();
        String personalNumber="200505173826";
        int amountSpent=100;
        int yearsAsMember=2;
        Customer customer1=purchasesHistory.updatePurchasesHistory(personalNumber, yearsAsMember, amountSpent, 0);
        double averageAmountPerCustomer=purchasesHistory.averageAmountPerCustomer(personalNumber);
        assertEquals(50, averageAmountPerCustomer);
    }

    @Test
    void averageAmountPerPurchaseTest(){
        PurchasesHistory purchasesHistory=new PurchasesHistory();
        String personalNumber="199412011938";
        int amountSpent=100;
        int amountOfPurchases=2;
        int yearAsMember=1;
        purchasesHistory.updatePurchasesHistory(personalNumber,amountOfPurchases, amountSpent, yearAsMember);
        double averageAmountPerPurchase=purchasesHistory.averageAmountPerPurchase(personalNumber);
        assertEquals(50, averageAmountPerPurchase);
    }

    @Test
    void addCustomerDataTest(){
        PurchasesHistory purchasesHistory=new PurchasesHistory();
        String personalNumber="196508256537";
        int amountSpent=100;
        int yearsAsMember=2;
        purchasesHistory.addCustomerData(personalNumber, amountSpent, yearsAsMember);

    }

    @Test
    void getCustomerDataListTest(){
        PurchasesHistory purchasesHistory=new PurchasesHistory();
        String personalNumber="200009256030";
        int amountSpent=100;
        int yearsAsMember=2;
        purchasesHistory.addCustomerData(personalNumber, amountSpent, yearsAsMember);
        List<Customer> customerDataList=purchasesHistory.getCustomerDataList();
        assertEquals(1, customerDataList.size());
    }

    @Test
    void setCustomerDataListTest(){
        List<Customer> customerDataList=new ArrayList<>();
        PurchasesHistory purchasesHistory=new PurchasesHistory();
        String personalNumber="200307182048";
        int amountSpent=100;
        int yearsAsMember=2;
        purchasesHistory.addCustomerData(personalNumber, amountSpent, yearsAsMember);
        purchasesHistory.setCustomerDataList(customerDataList);
        assertEquals(1, customerDataList.size());
    }

    @Test
    void removeCustomerDataTest(){
        PurchasesHistory purchasesHistory=new PurchasesHistory();
        String personalNumber="1234567890";
        int amountSpent=100;
        int yearsAsMember=2;
        purchasesHistory.addCustomerData(personalNumber, amountSpent, yearsAsMember);
        purchasesHistory.removeCustomerData(personalNumber);
        assertEquals(0, purchasesHistory.customerDataList.size());
    }


    @Test
    void toStringTest(){
        PurchasesHistory purchasesHistory=new PurchasesHistory();
        String personalNumber="199908112536";
        int amountSpent=1000;
        int yearsAsMember=2;
        purchasesHistory.addCustomerData(personalNumber, amountSpent, yearsAsMember);
        String toString=purchasesHistory.toString();
        assertEquals("PurchasesHistory{customerDataList=[Customer{personalNumber='1234567890', name='null', age=0, points=0.0, membership='Not a member', amountOfPurchases=0, amountSpent=100, yearsAsMember=2, email='null'}]}", toString);

    }
}





