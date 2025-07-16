package supermarketpricing.pricing;

import supermarketpricing.core.Money;
import supermarketpricing.core.Quantity;

public interface PricingStrategy {
    PricingResult calculateFinalPrice(Quantity quantity, Money basePrice);
}
