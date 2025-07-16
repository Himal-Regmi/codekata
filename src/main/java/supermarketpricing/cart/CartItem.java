package supermarketpricing.cart;

import supermarketpricing.core.Money;
import supermarketpricing.core.Quantity;
import supermarketpricing.inventory.Product;
import supermarketpricing.pricing.PricingResult;

import java.util.List;

public final class CartItem {
    private final Product product;
    private final Quantity quantity;
    private final PricingResult pricingResult;

    public CartItem(Product product, Quantity quantity) {
        this.product = product;
        this.quantity = quantity;
        this.pricingResult = product.getPricingResult(quantity);
    }

    public Money getPrice(){
        return this.pricingResult.money();
    }

    public List<String> getAuditTrail(){
        return this.pricingResult.auditTrail();
    }

    public Product getProduct(){
        return this.product;
    }

    public Quantity getQuantity(){
        return this.quantity;
    }
}
