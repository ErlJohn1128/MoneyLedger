package model;

import java.math.BigDecimal;

public class Transaction {
    private final int id;
    private final String description;
    private final BigDecimal amount;
    private final TransactionType type;
    private final MoneyCategory category;
    private final Wallet wallet;

    public Transaction(int id, String description, BigDecimal amount, TransactionType type,
                      MoneyCategory category, Wallet wallet) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        if (type == null) {
            throw new IllegalArgumentException("Type is required.");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category is required.");
        }
        if (wallet == null) {
            throw new IllegalArgumentException("Wallet is required.");
        }

        this.id = id;
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.category = category;
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

    public TransactionType getType() {
        return type;
    }

    public MoneyCategory getCategory() {
        return category;
    }

    public Wallet getWallet() {
        return wallet;
    }

    @Override
    public String toString() {
        return id + " | " + type + " | " + description + " | " + category + " | " + wallet + " | " + amount;
    }
}