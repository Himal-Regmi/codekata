package supermarketpricing.pricing;

import supermarketpricing.core.Money;

import java.util.List;

public record PricingResult(Money money, List<String> auditTrail) {
}
