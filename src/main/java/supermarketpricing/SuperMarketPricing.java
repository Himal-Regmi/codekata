package supermarketpricing;

import supermarketpricing.cart.ShoppingCart;
import supermarketpricing.core.Currency;
import supermarketpricing.core.Money;
import supermarketpricing.core.Quantity;
import supermarketpricing.core.QuantityType;
import supermarketpricing.inventory.Product;
import supermarketpricing.inventory.valuationStrategy.CostBasedStockValuation;
import supermarketpricing.inventory.valuationStrategy.PriceBasedStockValuation;
import supermarketpricing.pricing.BuyXGetYPricingStrategy;
import supermarketpricing.pricing.SimplePricingStrategy;
import supermarketpricing.pricing.VolumeBasedPricingStrategy;
import supermarketpricing.pricing.WeightBasedPricingStrategy;

import java.math.BigDecimal;

public class SuperMarketPricing {
    public static void main(String[] args) {
        Product bottle = new Product("Bottle",
                new Money(BigDecimal.valueOf(2.50), Currency.GBP),
                new Money(BigDecimal.valueOf(1.5), Currency.GBP),
                new SimplePricingStrategy(),
                new CostBasedStockValuation());

        Product apple = new Product("Apple",
                new Money(BigDecimal.valueOf(0.5), Currency.GBP),
                new Money(BigDecimal.valueOf(0.3), Currency.GBP),
                new VolumeBasedPricingStrategy(6, new Money(BigDecimal.valueOf(2), Currency.GBP)),
                new PriceBasedStockValuation());

        Product banana = new Product("Banana",
                new Money(BigDecimal.valueOf(0.3), Currency.GBP),
                new Money(BigDecimal.valueOf(0.1), Currency.GBP),
                new VolumeBasedPricingStrategy(6, new Money(BigDecimal.valueOf(1), Currency.GBP)),
                new PriceBasedStockValuation());

        Product steak = new Product("Steak",
                new Money(BigDecimal.valueOf(3.99), Currency.GBP),
                new Money(BigDecimal.valueOf(1.99), Currency.GBP),
                new WeightBasedPricingStrategy(BigDecimal.valueOf(1)),
                new CostBasedStockValuation());

        Product chicken = new Product("Chicken",
                new Money(BigDecimal.valueOf(1.99), Currency.GBP),
                new Money(BigDecimal.valueOf(0.99), Currency.GBP),
                new WeightBasedPricingStrategy(BigDecimal.valueOf(1)),
                new CostBasedStockValuation());

        Product water = new Product("Water",
                new Money(BigDecimal.valueOf(1), Currency.GBP),
                new Money(BigDecimal.valueOf(0.5), Currency.GBP),
                new BuyXGetYPricingStrategy(3, 1),
                new PriceBasedStockValuation());

        ShoppingCart cart = new ShoppingCart();
        cart.addItem(bottle, new Quantity(BigDecimal.valueOf(2), QuantityType.COUNT));
        cart.addItem(apple, new Quantity(BigDecimal.valueOf(7), QuantityType.COUNT));
        cart.addItem(banana, new Quantity(BigDecimal.valueOf(12), QuantityType.COUNT));
        cart.addItem(steak, new Quantity(BigDecimal.valueOf(1.67), QuantityType.WEIGHT));
        cart.addItem(chicken, new Quantity(BigDecimal.valueOf(2), QuantityType.WEIGHT));
        cart.addItem(water, new Quantity(BigDecimal.valueOf(8), QuantityType.COUNT));

        cart.printReceipt();

        System.out.println("\n===== STOCK VALUATION =====");
        System.out.println("Bottle stock (100 units): " + bottle.getStockValue(new Quantity(BigDecimal.valueOf(100), QuantityType.COUNT)));
        System.out.println("Apple stock (100 units): " + apple.getStockValue(new Quantity(BigDecimal.valueOf(100), QuantityType.COUNT)));
    }
}
