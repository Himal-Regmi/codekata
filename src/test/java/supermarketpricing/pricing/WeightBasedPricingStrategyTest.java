package supermarketpricing.pricing;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Assertions;
import supermarketpricing.core.Currency;
import supermarketpricing.core.Money;
import supermarketpricing.core.Quantity;
import supermarketpricing.core.QuantityType;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class WeightBasedPricingStrategyTest {

    @ParameterizedTest
    @MethodSource("validTestCases")
    public void testWeightBasedPricingWithValidWeightInputs(
            BigDecimal baseUnitWeight,
            BigDecimal quantityValue,
            Money basePrice,
            Money expectedTotal) {

        WeightBasedPricingStrategy strategy = new WeightBasedPricingStrategy(baseUnitWeight);
        Quantity quantity = new Quantity(quantityValue, QuantityType.WEIGHT);

        Assertions.assertEquals(
                expectedTotal,
                strategy.calculateFinalPrice(quantity, basePrice).money()
        );
    }

    @ParameterizedTest
    @MethodSource("invalidTestCases")
    public void testWeightBasedPricingThrowsExceptionForCountType(
            BigDecimal baseUnitWeight,
            BigDecimal quantityValue) {

        WeightBasedPricingStrategy strategy = new WeightBasedPricingStrategy(baseUnitWeight);
        Quantity invalidQuantity = new Quantity(quantityValue, QuantityType.COUNT);
        Money basePrice = new Money(BigDecimal.valueOf(1), Currency.USD);

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> strategy.calculateFinalPrice(invalidQuantity, basePrice)
        );
    }

    private static Stream<Arguments> validTestCases() {
        Money unitPrice = new Money(BigDecimal.valueOf(5), Currency.USD);
        return Stream.of(
                // baseUnitWeight, quantityValue, basePrice, expectedTotal
                Arguments.of(BigDecimal.valueOf(1), BigDecimal.valueOf(3), unitPrice,
                        new Money(BigDecimal.valueOf(15), Currency.USD)),
                Arguments.of(BigDecimal.valueOf(0.5), BigDecimal.valueOf(1.5), unitPrice,
                        new Money(BigDecimal.valueOf(15), Currency.USD)),
                Arguments.of(BigDecimal.valueOf(2), BigDecimal.valueOf(5), unitPrice,
                        new Money(BigDecimal.valueOf(12.5), Currency.USD)),
                Arguments.of(BigDecimal.valueOf(0.1), BigDecimal.valueOf(0.3), unitPrice,
                        new Money(BigDecimal.valueOf(15), Currency.USD)),
                Arguments.of(BigDecimal.valueOf(3), BigDecimal.valueOf(1), unitPrice,
                        new Money(BigDecimal.valueOf(1.67), Currency.USD))
        );
    }

    private static Stream<Arguments> invalidTestCases() {
        return Stream.of(
                // baseUnitWeight, quantityValue (count type)
                Arguments.of(BigDecimal.valueOf(1), BigDecimal.valueOf(3)),
                Arguments.of(BigDecimal.valueOf(0.5), BigDecimal.valueOf(10)),
                Arguments.of(BigDecimal.valueOf(2), BigDecimal.valueOf(1))
        );
    }
}