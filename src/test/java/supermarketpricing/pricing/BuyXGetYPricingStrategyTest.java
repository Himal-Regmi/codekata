package supermarketpricing.pricing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import supermarketpricing.core.Currency;
import supermarketpricing.core.Money;
import supermarketpricing.core.Quantity;
import supermarketpricing.core.QuantityType;

import java.math.BigDecimal;
import java.util.stream.Stream;


public class BuyXGetYPricingStrategyTest {

    @ParameterizedTest
    @MethodSource("testCasesForBuyXGetYPricingStrategy")
    public void testBuyXGetYPricingStrategy(int buyCount, int freeCount, BigDecimal quantity,
                                            BigDecimal basePrice, BigDecimal expectedPrice) {
        BuyXGetYPricingStrategy buy2Get1Free = new BuyXGetYPricingStrategy(buyCount, freeCount);

        Quantity quantityOfProduct = new Quantity(quantity, QuantityType.COUNT);

        Money basePriceProduct = new Money(basePrice, Currency.USD);
        Money expectedTotalPrice = new Money(expectedPrice, Currency.USD);

        Assertions.assertEquals(
                buy2Get1Free.calculateFinalPrice(quantityOfProduct, basePriceProduct).money(),
                expectedTotalPrice
        );
    }

    private static Stream<Arguments> testCasesForBuyXGetYPricingStrategy() {
        return Stream.of(
                Arguments.of(2, 1, BigDecimal.valueOf(15), BigDecimal.valueOf(5), BigDecimal.valueOf(50)),  // Buy 2 Get 1 with exact items
                Arguments.of(2, 1, BigDecimal.valueOf(16), BigDecimal.valueOf(5), BigDecimal.valueOf(55)),  // Buy 2 Get 1 with extra item
                Arguments.of(2, 2, BigDecimal.valueOf(20), BigDecimal.valueOf(5), BigDecimal.valueOf(50)),  // Buy 2 Get 2 with exact items
                Arguments.of(2, 2, BigDecimal.valueOf(23), BigDecimal.valueOf(5), BigDecimal.valueOf(60))   // Buy 2 Get 2 with extra items
        );
    }

}

