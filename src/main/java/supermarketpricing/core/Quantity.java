package supermarketpricing.core;

import java.math.BigDecimal;

public record Quantity(BigDecimal value, QuantityType type) {

    public Quantity {
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("value cannot be negative");
        }
    }

    public boolean isCountType() {
        return this.type == QuantityType.COUNT;
    }
}
