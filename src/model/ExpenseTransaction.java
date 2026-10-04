package model;

import java.math.BigDecimal;

public class ExpenseTransaction extends Transaction {
    private final ExpenseCategory category;

    public ExpenseTransaction(int id, String description, BigDecimal amount,
                              ExpenseCategory category, Wallet wallet) {
        super(id, description, amount, wallet);
        if (category == null) {
            throw new IllegalArgumentException("Category is required.");
        }
        this.category = category;
    }

    @Override
    public TransactionType getType() {
        return TransactionType.EXPENSE;
    }

    @Override
    public ExpenseCategory getCategory() {
        return category;
    }

    @Override
    public void applyTo(Account account) {
        account.withdraw(getAmount());
    }
}
