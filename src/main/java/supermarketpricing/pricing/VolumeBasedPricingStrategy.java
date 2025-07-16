package supermarketpricing.pricing;

import supermarketpricing.core.Money;
import supermarketpricing.core.Quantity;

import java.math.BigDecimal;
import java.util.List;

public class VolumeBasedPricingStrategy implements PricingStrategy {
    private final int volume;
    private final Money volumePrice;

    public VolumeBasedPricingStrategy(int volume, Money volumePrice) {
        this.volume = volume;
        this.volumePrice = volumePrice;
    }

    @Override
    public PricingResult calculateFinalPrice(Quantity quantity, Money basePrice) {
        if (!quantity.isCountType()){
            throw new IllegalArgumentException("Volume Pricing Strategy requires count type");
        }
        int totalItemCount = quantity.value().intValue();
        int batchOfVolume = totalItemCount / volume;
        int remainingVolume = totalItemCount % volume;

        Money groupPrice = volumePrice.multiply(BigDecimal.valueOf(batchOfVolume));
        Money remainingVolumePrice = basePrice.multiply(BigDecimal.valueOf(remainingVolume));
        Money totalPrice = groupPrice.addMoney(remainingVolumePrice);

        return new PricingResult(totalPrice,
                List.of("Volume Pricing: " + batchOfVolume + " groups of " + volume + " @ " + volumePrice + " = " + groupPrice,
                        "Remaining Items: " + remainingVolume + " @ " + basePrice + " = " + remainingVolumePrice,
                        "Total : " + totalPrice));
    }
}
