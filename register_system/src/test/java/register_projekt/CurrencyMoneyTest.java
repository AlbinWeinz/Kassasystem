package register_projekt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Currency;

import org.junit.Assert;
import org.junit.Test;

public class CurrencyMoneyTest {

    @Test
    public void instantiationWithOneArgument() {
        CurrencyMoney money = new CurrencyMoney(Currency.getInstance("SEK"));
        Assert.assertEquals(0, money.getInteger());
        Assert.assertEquals(0, money.getFractional());
        Assert.assertEquals(0.00, money.getAmount(), 0.01);
        Assert.assertEquals("0.00 kr", money.toString());
    }

    @Test
    public void instantiationWithTwoArguments() {
        CurrencyMoney money = new CurrencyMoney(10.50, Currency.getInstance("SEK"));
        Assert.assertEquals(10, money.getInteger());
        Assert.assertEquals(50, money.getFractional());
        Assert.assertEquals(10.50, money.getAmount(), 0.01);
        Assert.assertEquals("10.50 kr", money.toString());
    }

    @Test
    public void instantiationWithThreeArguments() {
        CurrencyMoney money = new CurrencyMoney(10, 50, Currency.getInstance("SEK"));
        Assert.assertEquals(10, money.getInteger());
        Assert.assertEquals(50, money.getFractional());
        Assert.assertEquals(10.50, money.getAmount(), 0.01);
        Assert.assertEquals("10.50 kr", money.toString());
    }

    @Test
    public void correctToStringSEK() {
        CurrencyMoney money = new CurrencyMoney(10.50, Currency.getInstance("SEK"));
        assertEquals("10.50 kr", money.toString());
    }

    @Test
    public void correctToStringUSD() {
        CurrencyMoney money = new CurrencyMoney(10.50, Currency.getInstance("USD"));
        assertEquals("US$10.50", money.toString());
    }

    @Test
    public void correctToStringEUR() {
        CurrencyMoney money = new CurrencyMoney(10.50, Currency.getInstance("EUR"));
        assertEquals("€10.50", money.toString());
    }

    @Test
    public void getCurrencyReturnsCorrectValue() {
        CurrencyMoney money = new CurrencyMoney(10, 50, Currency.getInstance("SEK"));
        Assert.assertEquals(Currency.getInstance("SEK"), money.getCurrency());
    }

    @Test
    public void isSameCurrencyReturnsCorrectValue() {
        CurrencyMoney money1 = new CurrencyMoney(100, Currency.getInstance("SEK"));
        CurrencyMoney money2 = new CurrencyMoney(50, Currency.getInstance("SEK"));
        CurrencyMoney money3 = new CurrencyMoney(50, Currency.getInstance("USD"));
        CurrencyMoney money4 = new CurrencyMoney(100, Currency.getInstance("EUR"));

        assertTrue(money1.isSameCurrency(money2));
        assertFalse(money1.isSameCurrency(money3));
        assertFalse(money1.isSameCurrency(money4));
    }

    @Test
    public void additionIsCorrectlyCalculated() {
        CurrencyMoney money1 = new CurrencyMoney(10.50, Currency.getInstance("SEK"));
        CurrencyMoney money2 = new CurrencyMoney(5.25, Currency.getInstance("SEK"));
        CurrencyMoney sum = money1.add(money2);
        Assert.assertEquals(15, sum.getInteger());
        Assert.assertEquals(75, sum.getFractional());
        Assert.assertEquals(15.75, sum.getAmount(), 0.01);
        Assert.assertEquals("15.75 kr", sum.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void additionBetweenDifferentCurrenciesThrowsException() {
        CurrencyMoney money1 = new CurrencyMoney(10.50, Currency.getInstance("SEK"));
        CurrencyMoney money2 = new CurrencyMoney(5.25, Currency.getInstance("USD"));
        money1.add(money2);
    }

    @Test
    public void subtractionIsCorrectlyCalculated() {
        CurrencyMoney money1 = new CurrencyMoney(10.50, Currency.getInstance("SEK"));
        CurrencyMoney money2 = new CurrencyMoney(5.25, Currency.getInstance("SEK"));
        CurrencyMoney difference = money1.subtract(money2);
        Assert.assertEquals(5, difference.getInteger());
        Assert.assertEquals(25, difference.getFractional());
        Assert.assertEquals(5.25, difference.getAmount(), 0.01);
        Assert.assertEquals("5.25 kr", difference.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void subtractionBetweenDifferentCurrenciesThrowsException() {
        CurrencyMoney money1 = new CurrencyMoney(10.50, Currency.getInstance("SEK"));
        CurrencyMoney money2 = new CurrencyMoney(5.25, Currency.getInstance("EUR"));
        money1.subtract(money2);
    }

    @Test
    public void equalsReturnsReturnsCorrectValue() {
        CurrencyMoney money1 = new CurrencyMoney(10, 50, Currency.getInstance("SEK"));
        CurrencyMoney money2 = new CurrencyMoney(10.50, Currency.getInstance("SEK"));
        CurrencyMoney money3 = new CurrencyMoney(1050, Currency.getInstance("SEK"));
        CurrencyMoney money4 = new CurrencyMoney(10.50, Currency.getInstance("USD"));
        CurrencyMoney money5 = new CurrencyMoney(10, 50, Currency.getInstance("EUR"));

        assertTrue(money1.equals(money2));
        assertFalse(money1.equals(money3));
        assertFalse(money1.equals(money4));
        assertFalse(money1.equals(money5));
    }

    @Test
    public void compareToReturnsCorrectValues() {
        CurrencyMoney money1 = new CurrencyMoney(10, 50, Currency.getInstance("SEK"));
        CurrencyMoney money2 = new CurrencyMoney(10.25, Currency.getInstance("SEK"));
        CurrencyMoney money3 = new CurrencyMoney(10.50, Currency.getInstance("SEK"));
        CurrencyMoney money4 = new CurrencyMoney(10.75, Currency.getInstance("SEK"));
        assertTrue(money1.compareTo(money2) > 0);
        assertTrue(money1.compareTo(money3) == 0);
        assertTrue(money1.compareTo(money4) < 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void comparingDifferentCurrenciesThrowsException() {
        CurrencyMoney money1 = new CurrencyMoney(10.50, Currency.getInstance("USD"));
        CurrencyMoney money2 = new CurrencyMoney(5.25, Currency.getInstance("EUR"));
        money1.compareTo(money2);
    }
}
