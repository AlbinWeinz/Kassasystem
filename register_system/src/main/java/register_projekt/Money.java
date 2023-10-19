package register_projekt;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
    private BigDecimal amount;

    public Money() {
        amount = BigDecimal.ZERO;
    }

    public Money(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
        if (BigDecimal.valueOf(amount).scale() > 2) {
            throw new IllegalArgumentException("Ören must be between 0 and 99");
        }
        this.amount = BigDecimal.valueOf(amount);
        // .setScale(2, RoundingMode.HALF_UP);
    }

    public Money(long kronor, long oren) {
        if (oren < 0 || oren > 99) {
            throw new IllegalArgumentException("Ören must be between 0 and 99");
        }
        this.amount = BigDecimal.valueOf(kronor).add(BigDecimal.valueOf(oren).divide(BigDecimal.valueOf(100)));
        // .setScale(2, RoundingMode.HALF_UP);
        if (this.amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
    }

    public long getKronor() {
        return amount.longValue();
    }

    public long getOren() {
        return (amount.remainder(BigDecimal.ONE).multiply(BigDecimal.valueOf(100)).longValue());
    }

    public double getAmount() {
        return amount.doubleValue();
    }

    public Money sum(Money other) {
        return new Money(amount.add(other.amount).doubleValue());
    }

    public Money subtract(Money other) {
        return new Money(amount.subtract(other.amount).doubleValue());
    }

    public Money multiply(double n) {
        return new Money(amount.multiply(BigDecimal.valueOf(n)).doubleValue());
    }

    public Money round() {
        return new Money(amount.setScale(0, RoundingMode.HALF_UP).doubleValue());
    }

    @Override
    public String toString() {
        return String.format("%d.%02d kr", getKronor(), getOren());
    }

}