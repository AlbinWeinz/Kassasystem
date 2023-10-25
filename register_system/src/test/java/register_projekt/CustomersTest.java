package register_projekt;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CustomersTest {



    @Test
    void removeCustomerTest(){
        Customers customers=new Customers();
        Customer expected = customers.addCustomer("Kalle Karlsson", "200008143528", 23, 15.6, "Bronze","kalle@exempel.com");
        customers.addCustomer("Göran Smith", "200308143528", 20, 0.6, "New member","goran@exempel.com");

        customers.removeCustomer(expected); //kanske använd getCustomer
        assertNull(Customers.nameSearch("Kalle Karlsson"));
    }

    @Test
    void nameSearchTest(){
        Customers customers=new Customers();
        customers.addCustomer("Kalle Karlsson", "200008143528", 23, 15.6, "Bronze","kalle@exempel.com");
        customers.addCustomer("Göran Smith", "200308143528", 20, 0.6, "New member","goran@exempel.com");
        String addedCustomers= Customers.nameSearch("Kalle Karlsson");
        String expected = "Ditt medlemskap: " +
                "\nNamn: " + "Kalle Karlsson" +
                "\nPersonnummer: " + "200008143528" +
                "\nÅlder: " + "23" +
                "\nMejladress: " + "kalle@exempel.com" +
                "\nMedlemskap: " + "Bronze" +
                "\nPoäng: " + "15.6" +
                "\nRabatt: " + "10" + "% rabatt";
                assertEquals(addedCustomers, expected);
    }

    @Test
    void emailSearchTest(){
            Customers customers=new Customers();
            customers.addCustomer("Kalle Karlsson", "200008143528", 23, 15.6, "Bronze","kalle@exempel.com");
            customers.addCustomer("Göran Smith", "200308143528", 20, 0.6, "New member","goran@exempel.com");
            String addedCustomersByEmail= customers.emailSearch("kalle@exempel.com");
            String expectedByEmail = "Ditt medlemskap: " +
                    "\nNamn: " + "Kalle Karlsson" +
                    "\nPersonnummer: " + "200008143528" +
                    "\nÅlder: " + "23" +
                    "\nMejladress: " + "kalle@exempel.com" +
                    "\nMedlemskap: " + "Bronze" +
                    "\nPoäng: " + "15.6" +
                    "\nRabatt: " + "10" + "% rabatt";
        assertEquals(addedCustomersByEmail, expectedByEmail);
        }

    @Test
    void personalNumberSearch(){
        Customers customers=new Customers();
        customers.addCustomer("Kalle Karlsson", "200008143528", 23, 15.6, "Bronze","kalle@exempel.com");
        customers.addCustomer("Göran Smith", "200308143528", 20, 0.6, "New member","goran@exempel.com");
        String addedCustomersByPersonalNumber= customers.emailSearch("kalle@exempel.com");
        String expectedByPersonalNumber = "Ditt medlemskap: " +
                "\nNamn: " + "Kalle Karlsson" +
                "\nPersonnummer: " + "200008143528" +
                "\nÅlder: " + "23" +
                "\nMejladress: " + "kalle@exempel.com" +
                "\nMedlemskap: " + "Bronze" +
                "\nPoäng: " + "15.6" +
                "\nRabatt: " + "10" + "% rabatt";
        assertEquals(addedCustomersByPersonalNumber, expectedByPersonalNumber);
    }

    @Test
    void getAllCustomers(){
        Customers customers=new Customers();
        customers.addCustomer("Kalle Karlsson", "200008143528", 23, 15.6, "Bronze","kalle@exempel.com");
        customers.addCustomer("Göran Smith", "200308143528", 20, 0.6, "New member","goran@exempel.com");
        List<String> result = Customers.getAllCustomers();
        List<String> expected = new ArrayList<>();
        expected.add("Ditt medlemskap: \nNamn: Kalle Karlsson\nPersonnummer: 200008143528\nÅlder: 23\nMejladress: kalle@exempel.com\nMedlemskap: Bronze\nPoäng: 15.6\nRabatt: 10% rabatt");
        expected.add("Ditt medlemskap: \nNamn: Göran Smith\nPersonnummer: 200308143528\nÅlder: 20\nMejladress: goran@exempel.com\nMedlemskap: New member\nPoäng: 0.6\nRabatt: 5% rabatt");
        assertEquals(expected, result);
    }

}
