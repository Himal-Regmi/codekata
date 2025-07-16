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

public class SimplePricingStrategyTest {
    @ParameterizedTest
    @MethodSource("testCasesForSimplePricingStrategy")
    public void testSimplePricingStrategy(BigDecimal quantity, BigDecimal basePrice, BigDecimal expectedPrice) {
        SimplePricingStrategy simplePricingStrategy = new SimplePricingStrategy();

        Quantity quantityOfProduct = new Quantity(quantity, QuantityType.COUNT);

        Money basePriceProduct = new Money(basePrice, Currency.USD);
        Money expectedTotalPrice = new Money(expectedPrice, Currency.USD);

        Assertions.assertEquals(
                simplePricingStrategy.calculateFinalPrice(quantityOfProduct, basePriceProduct).money(),
                expectedTotalPrice
        );
    }

    private static Stream<Arguments> testCasesForSimplePricingStrategy() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(15), BigDecimal.valueOf(5), BigDecimal.valueOf(75)),
                Arguments.of(BigDecimal.valueOf(12.5), BigDecimal.valueOf(2), BigDecimal.valueOf(25)),
                Arguments.of(BigDecimal.valueOf(0.99), BigDecimal.valueOf(5), BigDecimal.valueOf(4.95)),
                Arguments.of(BigDecimal.valueOf(1.33), BigDecimal.valueOf(3), BigDecimal.valueOf(3.99))
        );
    }
}
