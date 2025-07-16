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

public class VolumeBasedPricingStrategyTest {
    @ParameterizedTest
    @MethodSource("testCasesForVolumeBasedPricingStrategy")
    public void testVolumeBasedPricingStrategy(int volume, BigDecimal volumePrice, 
                                               BigDecimal quantity, BigDecimal basePrice, BigDecimal expectedPrice) {
        VolumeBasedPricingStrategy volumeBasedStrategy = 
                new VolumeBasedPricingStrategy(volume, new Money(volumePrice, Currency.USD));

        Quantity quantityOfProduct = new Quantity(quantity, QuantityType.COUNT);

        Money basePriceProduct = new Money(basePrice, Currency.USD);
        Money expectedTotalPrice = new Money(expectedPrice, Currency.USD);

        Assertions.assertEquals(
                volumeBasedStrategy.calculateFinalPrice(quantityOfProduct, basePriceProduct).money(),
                expectedTotalPrice
        );
    }

    private static Stream<Arguments> testCasesForVolumeBasedPricingStrategy() {
        return Stream.of(
                Arguments.of(6, BigDecimal.valueOf(5), BigDecimal.valueOf(24), BigDecimal.valueOf(5), BigDecimal.valueOf(20)),  // Buy 2 Get 1 with exact items
                Arguments.of(2, BigDecimal.valueOf(1), BigDecimal.valueOf(11), BigDecimal.valueOf(2), BigDecimal.valueOf(7)),  // Buy 2 Get 1 with extra item
                Arguments.of(10, BigDecimal.valueOf(6), BigDecimal.valueOf(6), BigDecimal.valueOf(1), BigDecimal.valueOf(6)),  // Buy 2 Get 2 with exact items
                Arguments.of(5, BigDecimal.valueOf(1), BigDecimal.valueOf(8), BigDecimal.valueOf(0.5), BigDecimal.valueOf(2.5))   // Buy 2 Get 2 with extra items
        );
    }
}
