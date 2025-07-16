package supermarketpricing.core;

public enum Currency {
    GBP(2, "£"), USD(2, "$"), YEN(2, "¥");

    private final int decimals;
    private final String symbol;

    Currency(int decimals, String symbol) {
        this.decimals = decimals;
        this.symbol = symbol;
    }

    public int getDecimals() {
        return decimals;
    }

    public String getSymbol() {
        return symbol;
    }
}
