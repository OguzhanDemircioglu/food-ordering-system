package valueObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {
    private final BigDecimal amount;

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public boolean isGreaterThanZero() {
        return this.amount != null && this.amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isGreaterThan(Money other) {
        return this.amount != null && this.amount.compareTo(other.getAmount()) > 0;
    }

    public Money add(Money other) {
        return new Money(setScale(this.amount.add(other.getAmount())));
    }

    public Money subtract(Money other) {
        return new Money(setScale(this.amount.subtract(other.getAmount())));
    }

    public Money multiply(Money other) {
        return new Money(setScale(this.amount.multiply(other.getAmount())));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }

    private BigDecimal setScale(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_EVEN);
    }
}