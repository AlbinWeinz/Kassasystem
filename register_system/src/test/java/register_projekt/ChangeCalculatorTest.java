package register_projekt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

public class ChangeCalculatorTest {
    private ChangeCalculator changeCalculator;
    private Currency usdCurrency;
    private Currency eurCurrency;

    @Before
    public void setUp() {
        changeCalculator = new ChangeCalculator();
        usdCurrency = Currency.getInstance("USD");
        eurCurrency = Currency.getInstance("EUR");

        // Set available denominations for USD
        changeCalculator.setAvailableDenomination(usdCurrency, new BigDecimal("100"), 10);
        changeCalculator.setAvailableDenomination(usdCurrency, new BigDecimal("50"), 20);
        changeCalculator.setAvailableDenomination(usdCurrency, new BigDecimal("20"), 30);
        changeCalculator.setAvailableDenomination(usdCurrency, new BigDecimal("10"), 40);

        // Set available denominations for EUR
        changeCalculator.setAvailableDenomination(eurCurrency, new BigDecimal("100"), 5);
        changeCalculator.setAvailableDenomination(eurCurrency, new BigDecimal("50"), 10);
        changeCalculator.setAvailableDenomination(eurCurrency, new BigDecimal("20"), 20);
        changeCalculator.setAvailableDenomination(eurCurrency, new BigDecimal("10"), 25);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetAvailableDenominationIncorrectDenomination() {
        changeCalculator.setAvailableDenomination(usdCurrency, new BigDecimal("0"), 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetAvailableDenominationIncorrectQuantity() {
        changeCalculator.setAvailableDenomination(usdCurrency, new BigDecimal("0"), -1);
    }

    @Test
    public void testGetAvailableDenominations() {
        Map<BigDecimal, Integer> denominations = changeCalculator.getAvailableDenominations(eurCurrency);
        assertEquals(4, denominations.size());
        assertEquals(5, denominations.get(new BigDecimal("100")).intValue());
        assertEquals(10, denominations.get(new BigDecimal("50")).intValue());
        assertEquals(20, denominations.get(new BigDecimal("20")).intValue());
        assertEquals(25, denominations.get(new BigDecimal("10")).intValue());
    }

    @Test
    public void testResetAvailableDenominations() {
        changeCalculator.resetAvailableDenominations(usdCurrency);
        Map<BigDecimal, Integer> denominations = changeCalculator.getAvailableDenominations(usdCurrency);
        assertEquals(0, denominations.size());
    }

    @Test
    public void testUpdateAvailableDenominations() {
        Map<BigDecimal, Integer> denominations = changeCalculator.getAvailableDenominations(usdCurrency);
        assertEquals(10, denominations.get(new BigDecimal("100")).intValue());
        assertEquals(20, denominations.get(new BigDecimal("50")).intValue());
        double payment = 500.0;
        double price = 350.0;
        changeCalculator.calculateChange(usdCurrency, payment, price);
        assertEquals(9, denominations.get(new BigDecimal("100")).intValue());
        assertEquals(19, denominations.get(new BigDecimal("50")).intValue());
    }

    @Test
    public void testCalculateChange() {
        double payment = 500.0;
        double price = 350.0;
        Map<BigDecimal, Integer> change = changeCalculator.calculateChange(usdCurrency, payment, price);
        assertEquals(1, change.get(new BigDecimal("100")).intValue());
        assertEquals(1, change.get(new BigDecimal("50")).intValue());
    }

    @Test
    public void testCalculateChangeExactChange() {
        double payment = 350.0;
        double price = 350.0;
        Map<BigDecimal, Integer> change = changeCalculator.calculateChange(usdCurrency, payment, price);
        assertTrue(change.isEmpty());
    }

    @Test
    public void testCalculateLargeChange() {
        double payment = 2000.0;
        double price = 500.0;
        Map<BigDecimal, Integer> change = changeCalculator.calculateChange(eurCurrency, payment, price);
        assertEquals(5, change.get(new BigDecimal("100")).intValue());
        assertEquals(10, change.get(new BigDecimal("50")).intValue());
        assertEquals(20, change.get(new BigDecimal("20")).intValue());
        assertEquals(10, change.get(new BigDecimal("10")).intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateChangeInsufficientPayment() {
        double payment = 200.0;
        double price = 350.0;
        changeCalculator.calculateChange(usdCurrency, payment, price);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateChangeUnsupportedCurrency() {
        double payment = 500.0;
        double price = 350.0;
        Currency unsupportedCurrency = Currency.getInstance("SEK");
        changeCalculator.calculateChange(unsupportedCurrency, payment, price);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateChangeExactChangeNotPossible() {
        double payment = 500.0;
        double price = 35.0;
        changeCalculator.calculateChange(usdCurrency, payment, price);
    }
}