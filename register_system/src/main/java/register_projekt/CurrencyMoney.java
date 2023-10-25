package register_projekt;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class CurrencyMoney extends Money {
    private Currency currency;

    public CurrencyMoney(Currency currency) {
        super();
        this.currency = currency;
    }

    public CurrencyMoney(double amount, Currency currency) {
        super(amount);
        this.currency = currency;
    }

    public CurrencyMoney(long integer, long fractional, Currency currency) {
        super(integer, fractional);
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    public boolean isSameCurrency(CurrencyMoney other) {
        return this.currency.equals(other.getCurrency());
    }

    public CurrencyMoney add(CurrencyMoney other) {
        if (!isSameCurrency(other)) {
            throw new IllegalArgumentException("Currencies must match.");
        }
        return new CurrencyMoney(getAmount() + other.getAmount(), currency);
    }

    public CurrencyMoney subtract(CurrencyMoney other) {
        if (!isSameCurrency(other)) {
            throw new IllegalArgumentException("Currencies must match.");
        }
        return new CurrencyMoney(getAmount() - other.getAmount(), currency);
    }

    public CurrencyMoney multiply(double n) {
        return new CurrencyMoney(BigDecimal.valueOf(getAmount()).multiply(BigDecimal.valueOf(n))
                .setScale(2, RoundingMode.HALF_UP).doubleValue(), currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CurrencyMoney other = (CurrencyMoney) o;
        return isSameCurrency(other)
                && BigDecimal.valueOf(this.getAmount()).equals(BigDecimal.valueOf(other.getAmount()));
    }

    public int compareTo(CurrencyMoney other) {
        if (!isSameCurrency(other)) {
            throw new IllegalArgumentException("Currencies must match.");
        }
        return BigDecimal.valueOf(this.getAmount()).compareTo(BigDecimal.valueOf(other.getAmount()));
    }

    @Override
    public String toString() {
        if (currency.getCurrencyCode() == "SEK") {
            return String.format("%d.%02d kr", getInteger(), getFractional());
        } else {
            return currency.getSymbol() + String.format("%d.%02d", getInteger(), getFractional());
        }
    }
}