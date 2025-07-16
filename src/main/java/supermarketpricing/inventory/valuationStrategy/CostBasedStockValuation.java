package supermarketpricing.inventory.valuationStrategy;

import supermarketpricing.core.Money;
import supermarketpricing.core.Quantity;
import supermarketpricing.inventory.Product;

public class CostBasedStockValuation implements StockValuationStrategy{

    @Override
    public Money getStockValue(Product product, Quantity quantity) {
        return product.getCostPrice().multiply(quantity.value());
    }
}
