package register_projekt;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money implements Comparable<Money> {
    private BigDecimal amount;

    public Money() {
        amount = BigDecimal.ZERO;
    }

    public Money(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
        if (BigDecimal.valueOf(amount).scale() > 2) {
            throw new IllegalArgumentException("Fractional part must be between 0 and 99");
        }
        this.amount = BigDecimal.valueOf(amount);
        // .setScale(2, RoundingMode.HALF_UP);
    }

    public Money(long integer, long fractional) {
        if (fractional < 0 || fractional > 99) {
            throw new IllegalArgumentException("Fractional part must be between 0 and 99");
        }
        this.amount = BigDecimal.valueOf(integer).add(BigDecimal.valueOf(fractional).divide(BigDecimal.valueOf(100)));
        // .setScale(2, RoundingMode.HALF_UP);
        if (this.amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
    }

    public long getInteger() {
        return amount.longValue();
    }

    public long getFractional() {
        return (amount.remainder(BigDecimal.ONE).multiply(BigDecimal.valueOf(100)).longValue());
    }

    public double getAmount() {
        return amount.doubleValue();
    }

    public Money add(Money other) {
        return new Money(amount.add(other.amount).doubleValue());
    }

    public Money subtract(Money other) {
        return new Money(amount.subtract(other.amount).doubleValue());
    }

    public Money multiply(double n) {
        return new Money(amount.multiply(BigDecimal.valueOf(n)).setScale(2, RoundingMode.HALF_UP).doubleValue());
    }

    public Money round() {
        return new Money(amount.setScale(0, RoundingMode.HALF_UP).doubleValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Money other = (Money) o;
        return this.amount.equals(other.amount);
    }

    @Override
    public int compareTo(Money other) {
        return this.amount.compareTo(other.amount);
    }

    @Override
    public String toString() {
        return String.format("%d.%02d", getInteger(), getFractional());
    }
}