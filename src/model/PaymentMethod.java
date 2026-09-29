package model;

public enum PaymentMethod {
    CASH("Cash"),
    DEBIT_CARD("Debit Card"),
    CREDIT_CARD("Credit Card"),
    BANK_TRANSFER("Bank Transfer"),
    DIGITAL_WALLET("Digital Wallet");

    private final String displayName;

    PaymentMethod(String displayName) {
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