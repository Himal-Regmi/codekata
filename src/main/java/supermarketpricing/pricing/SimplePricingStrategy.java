package supermarketpricing.pricing;

import supermarketpricing.core.Money;
import supermarketpricing.core.Quantity;

import java.util.List;

public class SimplePricingStrategy implements PricingStrategy {
    @Override
    public PricingResult calculateFinalPrice(Quantity quantity, Money basePrice) {
        Money totalPrice = basePrice.multiply(quantity.value());
        return new PricingResult(totalPrice,
                List.of("Simple Pricing: " + quantity.value() + " @ " + basePrice + " each" + " = " + totalPrice)
        );
    }
}
