package model;

import java.math.BigDecimal;

public class Budget {
    private final ExpenseCategory category;
    private final BigDecimal limit;
    private BigDecimal spent;

    public Budget(ExpenseCategory category, BigDecimal limit) {
        if (category == null) {
            throw new IllegalArgumentException("Category is required.");
        }
        if (limit == null || limit.signum() <= 0) {
            throw new IllegalArgumentException("Budget limit must be greater than zero.");
        }

        this.category = category;
        this.limit = limit;
        this.spent = BigDecimal.ZERO;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public BigDecimal getSpent() {
        return spent;
    }

    public BigDecimal getRemaining() {
        return limit.subtract(spent);
    }

    public void addExpense(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Expense amount must be greater than zero.");
        }
        spent = spent.add(amount);
    }

    public boolean isOverBudget() {
        return spent.compareTo(limit) > 0;
    }
}
