package supermarketpricing.cart;

import supermarketpricing.core.Currency;
import supermarketpricing.core.Money;
import supermarketpricing.core.Quantity;
import supermarketpricing.inventory.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class ShoppingCart {
    private final List<CartItem> cartItems = new ArrayList<>();

    public void addItem(Product product, Quantity quantity){
        this.cartItems.add(new CartItem(product, quantity));
    }

    public Money getTotalPrice(){
        return cartItems.stream()
                .map(CartItem::getPrice)
                .reduce(new Money(BigDecimal.ZERO, Currency.GBP), Money::addMoney);
    }

    public void printReceipt(){
        System.out.println("=============RECEIPT==============");
        cartItems.forEach(item -> {
            System.out.printf("%s x %s: %s\n",
                    item.getProduct().getName(),
                    item.getQuantity().value(),
                    item.getPrice().value());

            item.getAuditTrail().forEach(log -> System.out.println(" > " + log));
        });
        System.out.println("Total Price: " + getTotalPrice());
        System.out.println("================================");
    }
}
