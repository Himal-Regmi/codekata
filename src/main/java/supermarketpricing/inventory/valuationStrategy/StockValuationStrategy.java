package supermarketpricing.inventory.valuationStrategy;

import supermarketpricing.core.Money;
import supermarketpricing.core.Quantity;
import supermarketpricing.inventory.Product;

public interface StockValuationStrategy {
    Money getStockValue(Product product, Quantity quantity);
}
