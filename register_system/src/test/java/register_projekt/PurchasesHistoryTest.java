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
    void averageAmountPerPurchaseTest1(){
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
    void averageAmountPerPurchaseTest(){
        String personalNumber="";
        int amountSpent=0;
        int amountOfPurchases=0;
        int yearAsMember=0;
        PurchasesHistory purchasesHistory=new PurchasesHistory(personalNumber);
        purchasesHistory.updatePurchasesHistory(personalNumber,yearAsMember, amountSpent,amountOfPurchases );
        double averageAmountPerPurchase=purchasesHistory.averageAmountPerPurchase(personalNumber);
        assertEquals(0.0, averageAmountPerPurchase);
    }

    @Test
    void toStringTest(){
        String personalNumber="199908112536";
        int amountSpent=1000;
        int yearsAsMember=2;
        int amountOfPurchases=2;
        PurchasesHistory purchasesHistory=new PurchasesHistory(personalNumber, amountSpent,amountOfPurchases, yearsAsMember);
        String toString=purchasesHistory.toString();
        assertEquals("Personnummer: 199908112536" +
                "\nHur mycket kunden spenderat: 1000" +
                "\nHur många köp kunden har gjort: 2" +
                "\nAntalet köp per år: 2"  +
                "\nMedelvärdet av hur mycket kunden spenderat per köp: 500.0", toString);

    }
}





