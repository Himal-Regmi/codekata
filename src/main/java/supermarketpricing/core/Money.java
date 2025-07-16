package supermarketpricing.core;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(BigDecimal value, Currency currency) {
    public Money(BigDecimal value, Currency currency) {
        this.value = value.setScale(currency.getDecimals(), RoundingMode.HALF_UP);
        this.currency = currency;
    }

    public Money addMoney(Money additionMoney) {
        return new Money(this.value.add(additionMoney.value), this.currency);
    }

    public Money multiply(BigDecimal multiplier) {
        return new Money(this.value.multiply(multiplier), this.currency);
    }

    @Override
    public String toString() {
        return this.currency.getSymbol() + " " + this.value().toString();
    }
}
