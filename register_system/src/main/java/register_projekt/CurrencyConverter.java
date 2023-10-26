package register_projekt;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    private Map<Currency, Map<Currency, BigDecimal>> exchangeRates;
    private ExchangeRateService exchangeRateService;

    public CurrencyConverter() {
        this.exchangeRates = new HashMap<>();
    }

    public CurrencyConverter(ExchangeRateService exchangeRateService) {
        this.exchangeRates = new HashMap<>();
        this.exchangeRateService = exchangeRateService;
    }

    public Map<Currency, Map<Currency, BigDecimal>> getExchangeRates() {
        return exchangeRates;
    }

    public BigDecimal getExchangeRateFromService(Currency fromCurrency, Currency toCurrency) {
        return exchangeRateService.getExchangeRate(fromCurrency, toCurrency);
    }

    public void setExchangeRate(Currency fromCurrency, Currency toCurrency, BigDecimal rate) {
        exchangeRates.computeIfAbsent(fromCurrency, k -> new HashMap<>()).put(toCurrency, rate);
        exchangeRates.computeIfAbsent(toCurrency, k -> new HashMap<>()).put(fromCurrency,
                BigDecimal.ONE.divide(rate, 4, RoundingMode.HALF_UP));
    }

    public void setExchangeRateService(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    public CurrencyMoney convert(CurrencyMoney money, Currency toCurrency) {
        if (exchangeRates.containsKey(money.getCurrency())) {
            BigDecimal exchangeRate = exchangeRates.get(money.getCurrency()).get(toCurrency);
            return new CurrencyMoney(money.multiply(exchangeRate.doubleValue()).getAmount(), toCurrency);
        }
        throw new IllegalArgumentException("No exchange rate between the given currencies exits");

    }

    public void updateExchangeRate(Currency fromCurrency, Currency toCurrency, BigDecimal newRate) {
        if (exchangeRates.containsKey(fromCurrency) && exchangeRates.get(fromCurrency).containsKey(toCurrency)) {
            exchangeRates.get(fromCurrency).put(toCurrency, newRate);
            exchangeRates.get(toCurrency).put(fromCurrency, BigDecimal.ONE.divide(newRate, 4, RoundingMode.HALF_UP));
        } else {
            throw new IllegalArgumentException("No exchange rate between the given currencies exits");
        }
    }

    public void removeExchangeRate(Currency fromCurrency, Currency toCurrency) {
        if (exchangeRates.containsKey(fromCurrency) && exchangeRates.get(fromCurrency).containsKey(toCurrency)) {
            exchangeRates.get(fromCurrency).remove(toCurrency);
            exchangeRates.get(toCurrency).remove(fromCurrency);
        } else {
            throw new IllegalArgumentException("No exchange rate between the given currencies exits");
        }
    }
}