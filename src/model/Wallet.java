package model;

public enum Wallet {
    CASH("Cash"),
    DEBIT_CARD("Debit Card"),
    CREDIT_CARD("Credit Card"),
    DIGITAL_WALLET("Digital Wallet");

    private final String displayName;

    Wallet(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}