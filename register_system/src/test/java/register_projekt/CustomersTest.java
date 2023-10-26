package register_projekt;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CustomersTest {
    static Customers customersList;
    @BeforeAll
    static void setUpCustomers(){
        customersList = new Customers();
        customersList.addCustomer("Kalle Karlsson", "200008143528", 23, 15.6, "Bronze","kalle@exempel.com");
        customersList.addCustomer("Göran Smith", "200308143528", 20, 0.6, "New member","goran@exempel.com");
    }



    @Test
    void removeCustomerTest(){
        Customer expected = customersList.addCustomer("Stina Stensson", "200108143528", 23, 15.6, "Bronze","kalle@exempel.com");
        customersList.removeCustomer(expected);
        assertNull(Customers.nameSearch("Stina Stensson"));
    }

    @Test
    void nameSearchTest(){
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
            String addedCustomersByEmail= Customers.emailSearch("kalle@exempel.com");
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
        String addedCustomersByPersonalNumber= Customers.emailSearch("kalle@exempel.com");
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
        List<String> result = Customers.getAllCustomers();
        List<String> expected = new ArrayList<>();
        expected.add("Ditt medlemskap: \nNamn: Kalle Karlsson\nPersonnummer: 200008143528\nÅlder: 23\nMejladress: kalle@exempel.com\nMedlemskap: Bronze\nPoäng: 15.6\nRabatt: 10% rabatt");
        expected.add("Ditt medlemskap: \nNamn: Göran Smith\nPersonnummer: 200308143528\nÅlder: 20\nMejladress: goran@exempel.com\nMedlemskap: New member\nPoäng: 0.6\nRabatt: 5% rabatt");
        assertEquals(expected, result);
    }



}
