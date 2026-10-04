package model;

import java.math.BigDecimal;

public abstract class Transaction {
    private final int id;
    private final String description;
    private final BigDecimal amount;
    private final Wallet wallet;

    protected Transaction(int id, String description, BigDecimal amount, Wallet wallet) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        if (wallet == null) {
            throw new IllegalArgumentException("Wallet is required.");
        }

        this.id = id;
        this.description = description;
        this.amount = amount;
        this.wallet = wallet;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public abstract TransactionType getType();

    public abstract TransactionCategory getCategory();

    public abstract void applyTo(Account account);

    @Override
    public String toString() {
        return id + " | " + getType() + " | " + description + " | " + getCategory() + " | " + wallet + " | " + amount;
    }
}
