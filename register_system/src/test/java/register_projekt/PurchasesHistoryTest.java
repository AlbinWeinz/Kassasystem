package register_projekt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PurchasesHistoryTest {

    @Test
    void updatePurchasesHistoryTest(){
        String personalNumber="200405173826";
        PurchasesHistory purchasesHistory=new PurchasesHistory(personalNumber,100,1,1);
        int newYearsAsMember = 1;
        int newAmountSpent = 200;
        int newAmountOfPurchases = 5;
        purchasesHistory.updatePurchasesHistory(personalNumber, newYearsAsMember, newAmountSpent, newAmountOfPurchases);
        assertEquals(6, purchasesHistory.getAmountOfPurchases());
        assertEquals(300, purchasesHistory.getAmountSpent());
        assertEquals(2, purchasesHistory.getYearsAsMember());
    }

    @Test
    void averageAmountPerPurchaseTest(){
        String personalNumber="199412011938";
        int amountSpent=100;
        int amountOfPurchases=2;
        int yearAsMember=1;
        PurchasesHistory purchasesHistory=new PurchasesHistory(personalNumber);
        purchasesHistory.updatePurchasesHistory(personalNumber,yearAsMember, amountSpent,amountOfPurchases );
        double averageAmountPerPurchase=purchasesHistory.averageAmountPerPurchase(personalNumber);
        assertEquals(50, averageAmountPerPurchase);
    }

    @Test
    void toStringTest(){
        String personalNumber="199908112536";
        int amountSpent=1000;
        int yearsAsMember=2;
        int amountOfPurchases=1;
        PurchasesHistory purchasesHistory=new PurchasesHistory(personalNumber, amountSpent,amountOfPurchases, yearsAsMember);
        String toString=purchasesHistory.toString();
        assertEquals("PurchasesHistory{customerDataList=[Customer{personalNumber='1234567890', name='null', age=0, points=0.0, membership='Not a member', amountOfPurchases=0, amountSpent=100, yearsAsMember=2, email='null'}]}", toString);

    }
}





