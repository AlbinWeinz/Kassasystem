package register_projekt;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;

public class VATCalculatorTest {
    private VATCalculator calculator;

    @Before
    public void setUp() {
        calculator = new VATCalculator();
    }

    @Test
    public void testCalculateVAT() {
        CurrencyMoney price = new CurrencyMoney(100.0, Currency.getInstance("USD"));
        double vatPercentage = 10.0;
        BigDecimal vatAmount = calculator.calculateVAT(price, vatPercentage);
        assertEquals(new BigDecimal("10.00"), vatAmount);
        assertEquals(new BigDecimal("10.00"), calculator.getTotalVAT(price.getCurrency()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateVATNegativeVATPercentage() {
        CurrencyMoney price = new CurrencyMoney(100.0, Currency.getInstance("USD"));
        double vatPercentage = -10.0;
        calculator.calculateVAT(price, vatPercentage);
    }

    @Test
    public void testCalculatePriceIncludingVAT() {
        CurrencyMoney price = new CurrencyMoney(100.0, Currency.getInstance("EUR"));
        double vatPercentage = 20.0;
        CurrencyMoney priceWithVAT = calculator.calculatePriceIncludingVAT(price, vatPercentage);
        assertEquals(new CurrencyMoney(120.0, Currency.getInstance("EUR")), priceWithVAT);
    }

    @Test
    public void testTotalVAT() {
        CurrencyMoney price1 = new CurrencyMoney(50.0, Currency.getInstance("USD"));
        CurrencyMoney price2 = new CurrencyMoney(75.0, Currency.getInstance("USD"));
        double vatPercentage = 10.0;
        BigDecimal vatAmount1 = calculator.calculateVAT(price1, vatPercentage);
        BigDecimal vatAmount2 = calculator.calculateVAT(price2, vatPercentage);
        assertEquals(new BigDecimal("5.00"), vatAmount1);
        assertEquals(new BigDecimal("7.50"), vatAmount2);
        assertEquals(new BigDecimal("12.50"), calculator.getTotalVAT(price1.getCurrency()));
        assertEquals(new BigDecimal("12.50"), calculator.getTotalVAT(price2.getCurrency()));
    }

    @Test
    public void testAllTotalVAT() {
        CurrencyMoney price1 = new CurrencyMoney(50.0, Currency.getInstance("USD"));
        CurrencyMoney price2 = new CurrencyMoney(75.0, Currency.getInstance("EUR"));
        double vatPercentage = 10.0;
        BigDecimal vatAmount1 = calculator.calculateVAT(price1, vatPercentage);
        BigDecimal vatAmount2 = calculator.calculateVAT(price2, vatPercentage);
        assertEquals(new BigDecimal("5.00"), vatAmount1);
        assertEquals(new BigDecimal("7.50"), vatAmount2);
        assertEquals(new BigDecimal("5.00"), calculator.getTotalVAT(price1.getCurrency()));
        assertEquals(new BigDecimal("7.50"), calculator.getTotalVAT(price2.getCurrency()));
    }

    @Test
    public void testResetTotalVAT() {
        CurrencyMoney price1 = new CurrencyMoney(50.0, Currency.getInstance("USD"));
        CurrencyMoney price2 = new CurrencyMoney(75.0, Currency.getInstance("EUR"));
        double vatPercentage = 10.0;
        calculator.calculateVAT(price1, vatPercentage);
        calculator.calculateVAT(price2, vatPercentage);
        assertEquals(new BigDecimal("5.00"), calculator.getTotalVAT(price1.getCurrency()));
        assertEquals(new BigDecimal("7.50"), calculator.getTotalVAT(price2.getCurrency()));
        calculator.resetTotalVAT(price1.getCurrency());
        assertEquals(BigDecimal.ZERO, calculator.getTotalVAT(price1.getCurrency()));
        assertEquals(new BigDecimal("7.50"), calculator.getTotalVAT(price2.getCurrency()));
        /*
         * Map<Currency, BigDecimal> allTotalVAT = calculator.getAllTotalVAT();
         * allTotalVAT.keySet().stream()
         * .filter(currency -> !currency.equals(price1.getCurrency()) &&
         * !currency.equals(price2.getCurrency()))
         * .forEach(currency -> assertEquals(BigDecimal.ZERO,
         * calculator.getTotalVAT(currency)));
         */
    }

    @Test
    public void testResetAllTotalVAT() {
        CurrencyMoney price1 = new CurrencyMoney(50.0, Currency.getInstance("USD"));
        CurrencyMoney price2 = new CurrencyMoney(75.0, Currency.getInstance("EUR"));
        double vatPercentage = 10.0;
        calculator.calculateVAT(price1, vatPercentage);
        calculator.calculateVAT(price2, vatPercentage);
        assertEquals(new BigDecimal("5.00"), calculator.getTotalVAT(price1.getCurrency()));
        assertEquals(new BigDecimal("7.50"), calculator.getTotalVAT(price2.getCurrency()));
        calculator.resetAllTotalVAT();
        Map<Currency, BigDecimal> allTotalVAT = calculator.getAllTotalVAT();
        allTotalVAT.values().forEach(vat -> assertEquals(BigDecimal.ZERO, vat));
    }
}