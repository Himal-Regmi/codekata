package supermarketpricing.inventory;

import supermarketpricing.core.Money;
import supermarketpricing.core.Quantity;
import supermarketpricing.inventory.valuationStrategy.StockValuationStrategy;
import supermarketpricing.pricing.PricingResult;
import supermarketpricing.pricing.PricingStrategy;

public class Product{
    private final String name;
    private final Money basePrice;
    private final Money costPrice;
    private final PricingStrategy pricingStrategy;
    private final StockValuationStrategy stockValuationStrategy;

    public Product(String name, Money basePrice, Money costPrice, PricingStrategy pricingStrategy, StockValuationStrategy stockValuationStrategy) {
        this.name = name;
        this.basePrice = basePrice;
        this.costPrice = costPrice;
        this.pricingStrategy = pricingStrategy;
        this.stockValuationStrategy = stockValuationStrategy;
    }

    public PricingResult getPricingResult(Quantity quantity){
        return this.pricingStrategy.calculateFinalPrice(quantity, this.basePrice);
    }

    public Money getStockValue(Quantity quantity){
        return this.stockValuationStrategy.getStockValue(this, quantity);
    }

    public Money getCostPrice() {
        return costPrice;
    }

    public String getName() {
        return name;
    }
}
