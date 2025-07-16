package supermarketpricing.pricing;

import supermarketpricing.core.Money;
import supermarketpricing.core.Quantity;

import java.math.BigDecimal;
import java.util.List;

public class BuyXGetYPricingStrategy implements PricingStrategy {
    private final int buyCount;
    private final int freeCount;

    public BuyXGetYPricingStrategy(int buyCount, int freeCount) {
        this.buyCount = buyCount;
        this.freeCount = freeCount;
    }

    @Override
    public PricingResult calculateFinalPrice(Quantity quantity, Money basePrice) {
        if (!quantity.isCountType()){
            throw new IllegalArgumentException("Volume Pricing Strategy requires count type");
        }

        int batchOfItems = quantity.value().intValue() / (buyCount + freeCount);
        int remainder = quantity.value().intValue() % (buyCount + freeCount);

        int paidItems = batchOfItems * buyCount + Math.min(remainder, buyCount);
        int freeItems = quantity.value().intValue() - paidItems;

        Money totalPrice = basePrice.multiply(BigDecimal.valueOf(paidItems));

        return new PricingResult(totalPrice,
                List.of("Buy " + buyCount + " get " + freeCount + " free",
                        "Items: "+ paidItems + " @ " + basePrice + " each, " + freeItems + " free",
                        "Total: "+ totalPrice));
    }
}
