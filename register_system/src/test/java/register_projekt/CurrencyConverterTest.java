package register_projekt;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Map;

public class CurrencyConverterTest {
    private CurrencyConverter converter;

    @Before
    public void setUp() {
        converter = new CurrencyConverter();
    }

    @Test
    public void testSetExchangeRate() {
        Currency usd = Currency.getInstance("USD");
        Currency eur = Currency.getInstance("EUR");

        BigDecimal rate = new BigDecimal("0.85");
        converter.setExchangeRate(usd, eur, rate);

        Map<Currency, Map<Currency, BigDecimal>> exchangeRates = converter.getExchangeRates();

        assertEquals(rate, exchangeRates.get(usd).get(eur));
        assertEquals(BigDecimal.ONE.divide(rate, 4, RoundingMode.HALF_UP), exchangeRates.get(eur).get(usd));
    }

    @Test
    public void testConvert() {
        Currency usd = Currency.getInstance("USD");
        Currency eur = Currency.getInstance("EUR");

        BigDecimal rate = new BigDecimal("0.85");
        converter.setExchangeRate(usd, eur, rate);

        CurrencyMoney money = new CurrencyMoney(100, usd);
        CurrencyMoney converted = converter.convert(money, eur);

        assertEquals(new CurrencyMoney(85, eur), converted);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertIllegalArgumentException() {
        Currency usd = Currency.getInstance("USD");
        Currency eur = Currency.getInstance("EUR");

        CurrencyMoney money = new CurrencyMoney(100, usd);
        converter.convert(money, eur);
    }

    @Test
    public void testUpdateExchangeRate() {
        Currency usd = Currency.getInstance("USD");
        Currency eur = Currency.getInstance("EUR");

        BigDecimal initialRate = new BigDecimal("0.85");
        converter.setExchangeRate(usd, eur, initialRate);

        BigDecimal newRate = new BigDecimal("0.90");
        converter.updateExchangeRate(usd, eur, newRate);

        Map<Currency, Map<Currency, BigDecimal>> exchangeRates = converter.getExchangeRates();

        assertEquals(newRate, exchangeRates.get(usd).get(eur));
        assertEquals(BigDecimal.ONE.divide(newRate, 4, RoundingMode.HALF_UP), exchangeRates.get(eur).get(usd));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateExchangeRateIllegalArgumentException() {
        Currency usd = Currency.getInstance("USD");
        Currency eur = Currency.getInstance("EUR");
        BigDecimal newRate = new BigDecimal("0.90");

        converter.updateExchangeRate(usd, eur, newRate);
    }

    @Test
    public void testRemoveExchangeRate() {
        Currency usd = Currency.getInstance("USD");
        Currency eur = Currency.getInstance("EUR");

        BigDecimal rate = new BigDecimal("0.85");
        converter.setExchangeRate(usd, eur, rate);

        converter.removeExchangeRate(usd, eur);

        Map<Currency, Map<Currency, BigDecimal>> exchangeRates = converter.getExchangeRates();

        assertFalse(exchangeRates.get(usd).containsKey(eur));
        assertFalse(exchangeRates.get(eur).containsKey(usd));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveExchangeRateIllegalArgumentException() {
        Currency usd = Currency.getInstance("USD");
        Currency eur = Currency.getInstance("EUR");

        converter.removeExchangeRate(usd, eur);
    }
}
