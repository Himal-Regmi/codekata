package supermarketpricing.cart;


import org.junit.jupiter.api.Test;
import supermarketpricing.core.Currency;
import supermarketpricing.core.Money;
import supermarketpricing.core.Quantity;
import supermarketpricing.core.QuantityType;
import supermarketpricing.inventory.Product;
import supermarketpricing.inventory.valuationStrategy.CostBasedStockValuation;
import supermarketpricing.pricing.SimplePricingStrategy;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartTest {

    @Test
    public void testCartTotal_RoundingAccumulationImpact() {
        Product productA = new Product("Product A",
                new Money(new BigDecimal("0.495"), Currency.GBP),
                new Money(BigDecimal.ZERO, Currency.GBP),
                new SimplePricingStrategy(),
                new CostBasedStockValuation());

        Product productB = new Product("Product B",
                new Money(new BigDecimal("0.495"), Currency.GBP),
                new Money(BigDecimal.ZERO, Currency.GBP),
                new SimplePricingStrategy(),
                new CostBasedStockValuation());

        ShoppingCart cart = new ShoppingCart();

        cart.addItem(productA, new Quantity(BigDecimal.ONE, QuantityType.COUNT));
        cart.addItem(productB, new Quantity(BigDecimal.ONE, QuantityType.COUNT));

        Money total = cart.getTotalPrice();

        // Verify the rounding accumulation impact
        Money expectedTotal = new Money(new BigDecimal("1.00"), Currency.GBP);
        assertEquals(expectedTotal, total,
                "Rounding accumulation should result in £1.00 instead of £0.99");
    }
}