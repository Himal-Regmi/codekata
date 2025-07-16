package supermarketpricing.pricing;

import supermarketpricing.core.Money;
import supermarketpricing.core.Quantity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class WeightBasedPricingStrategy implements PricingStrategy {
    private final BigDecimal baseUnitWeight;

    public WeightBasedPricingStrategy(BigDecimal weight) {
        this.baseUnitWeight = weight;
    }

    @Override
    public PricingResult calculateFinalPrice(Quantity quantity, Money basePrice) {
        if (quantity.isCountType()){
            throw new IllegalArgumentException("Require Weight Quantity");
        }

        BigDecimal quantityInBaseUnit = quantity.value().divide(baseUnitWeight, 10, RoundingMode.HALF_UP);
        Money totalPrice = basePrice.multiply(quantityInBaseUnit);

        return new PricingResult(totalPrice,
                List.of("Weight Pricing: "+ quantity.value() + " units @ " + basePrice + " per " + baseUnitWeight + " unit" + " = " + totalPrice));
    }
}
