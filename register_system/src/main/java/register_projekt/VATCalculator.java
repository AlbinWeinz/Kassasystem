package register_projekt;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class VATCalculator {
    private Map<Currency, BigDecimal> totalVAT;

    public VATCalculator() {
        totalVAT = new HashMap<>();
    }

    public void resetTotalVAT(Currency currency) {
        totalVAT.put(currency, BigDecimal.ZERO);
    }

    public void resetAllTotalVAT() {
        totalVAT.clear();
    }

    public BigDecimal getTotalVAT(Currency currency) {
        return totalVAT.getOrDefault(currency, BigDecimal.ZERO);
    }

    public Map<Currency, BigDecimal> getAllTotalVAT() {
        return new HashMap<>(totalVAT);
    }

    public BigDecimal calculateVAT(CurrencyMoney price, double vatPercentage) {
        if (vatPercentage < 0) {
            throw new IllegalArgumentException("VAT percentage cannot be negative");
        }
        BigDecimal totalVATForCurrency = getTotalVAT(price.getCurrency());
        BigDecimal vatAmount = BigDecimal.valueOf(price.getAmount())
                .multiply(BigDecimal.valueOf(vatPercentage).divide(new BigDecimal(100)))
                .setScale(2, RoundingMode.HALF_UP);
        totalVATForCurrency = totalVATForCurrency.add(vatAmount);
        totalVAT.put(price.getCurrency(), totalVATForCurrency);
        return vatAmount;
    }

    public CurrencyMoney calculatePriceIncludingVAT(CurrencyMoney price, double vatPercentage) {
        BigDecimal vatAmount = calculateVAT(price, vatPercentage);
        return new CurrencyMoney(price.getAmount() + vatAmount.doubleValue(), price.getCurrency());
    }
}