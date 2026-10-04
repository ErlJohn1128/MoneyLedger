package model;

import java.math.BigDecimal;

public class IncomeTransaction extends Transaction {
    private final IncomeCategory category;

    public IncomeTransaction(int id, String description, BigDecimal amount,
                             IncomeCategory category, Wallet wallet) {
        super(id, description, amount, wallet);
        if (category == null) {
            throw new IllegalArgumentException("Category is required.");
        }
        this.category = category;
    }

    @Override
    public TransactionType getType() {
        return TransactionType.INCOME;
    }

    @Override
    public IncomeCategory getCategory() {
        return category;
    }

    @Override
    public void applyTo(Account account) {
        account.deposit(getAmount());
    }
}
