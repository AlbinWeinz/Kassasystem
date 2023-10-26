package register_projekt;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.Currency;

public class ChangeCalculator {
    private Map<Currency, Map<BigDecimal, Integer>> availableDenominations;

    public ChangeCalculator() {
        availableDenominations = new LinkedHashMap<>();
    }

    public Map<BigDecimal, Integer> getAvailableDenominations(Currency currency) {
        return availableDenominations.get(currency);
    }

    public void setAvailableDenomination(Currency currency, BigDecimal denomination, int quantity) {
        if (denomination.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid denomination");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Invalid quantity");
        }
        Map<BigDecimal, Integer> denominations = availableDenominations.computeIfAbsent(currency,
                k -> new LinkedHashMap<>());
        denominations.put(denomination, quantity);
        denominations = denominations.entrySet().stream()
                .sorted(Map.Entry.<BigDecimal, Integer>comparingByKey(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        availableDenominations.put(currency, denominations);
    }

    public void resetAvailableDenominations(Currency currency) {
        Map<BigDecimal, Integer> denominations = availableDenominations.get(currency);
        denominations.clear();
    }

    private void updateAvailableDenominations(Currency currency, Map<BigDecimal, Integer> change) {
        Map<BigDecimal, Integer> denominations = availableDenominations.get(currency);
        /*
         * if (denominations == null) {
         * throw new IllegalArgumentException("Currency not supported");
         * }
         */
        for (Map.Entry<BigDecimal, Integer> entry : change.entrySet()) {
            BigDecimal denomination = entry.getKey();
            int quantity = entry.getValue();
            denominations.put(denomination, denominations.get(denomination) - quantity);
        }
    }

    public Map<BigDecimal, Integer> calculateChange(Currency currency, double payment, double price) {
        BigDecimal amountPaid = BigDecimal.valueOf(payment);
        BigDecimal totalCost = BigDecimal.valueOf(price);
        if (amountPaid.compareTo(totalCost) < 0) {
            throw new IllegalArgumentException("Insufficient payment");
        }
        BigDecimal changeAmount = amountPaid.subtract(totalCost);
        Map<BigDecimal, Integer> change = new LinkedHashMap<>();
        Map<BigDecimal, Integer> denominations = availableDenominations.get(currency);
        if (denominations == null) {
            throw new IllegalArgumentException("Currency not supported");
        }
        for (BigDecimal denomination : denominations.keySet()) {
            if (denominations.get(denomination) > 0 && changeAmount.compareTo(denomination) >= 0) {
                int quantity = changeAmount.divideToIntegralValue(denomination).intValue();
                if (quantity > denominations.get(denomination)) {
                    quantity = denominations.get(denomination);
                }
                change.put(denomination, quantity);
                changeAmount = changeAmount.subtract(denomination.multiply(BigDecimal.valueOf(quantity)));
            }
        }
        if (changeAmount.compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalArgumentException("Exact change cannot be given");
        }
        updateAvailableDenominations(currency, change);
        return change;
    }
}