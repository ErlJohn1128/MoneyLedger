package model;

import java.math.BigDecimal;

public class Transaction {
    private final int id;
    private final String description;
    private final BigDecimal amount;
    private final TransactionType type;
    private final ExpenseCategory category;
    private final PaymentMethod paymentMethod;

    public Transaction(int id, String description, BigDecimal amount, TransactionType type,
                      ExpenseCategory category, PaymentMethod paymentMethod) {
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
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Payment method is required.");
        }

        this.id = id;
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.paymentMethod = paymentMethod;
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

    public ExpenseCategory getCategory() {
        return category;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public String toString() {
        return id + " | " + type + " | " + description + " | " + category + " | " + paymentMethod + " | " + amount;
    }
}