package ui.console;

import model.Budget;
import model.ExpenseCategory;
import model.MoneyCategory;
import service.FinanceService;

import java.math.BigDecimal;

public class BudgetUI {
    private final FinanceService financeService;

    public BudgetUI(FinanceService financeService) {
        this.financeService = financeService;
    }

    public void displayBudgetOverview() {
        System.out.println();
        System.out.println("======= BUDGET OVERVIEW =======");

        if (financeService.getBudgets().isEmpty()) {
            System.out.println("No budgets configured.");
            return;
        }

        for (Budget budget : financeService.getBudgets()) {
            BigDecimal spent = budget.getSpent();
            BigDecimal remaining = budget.getRemaining();
            System.out.printf("%-15s | Limit: PHP %,.2f | Spent: PHP %,.2f | Remaining: PHP %,.2f%n",
                    budget.getCategory(),
                    budget.getLimit(),
                    spent,
                    remaining);
            if (budget.isOverBudget()) {
                System.out.println("  Warning: You are over this budget.");
            }
        }
    }

    public void addBudget(MoneyCategory category, BigDecimal limit) {
        if (!(category instanceof ExpenseCategory expenseCategory)) {
            throw new IllegalArgumentException("Only expense categories can have budgets.");
        }
        financeService.addBudget(new Budget(expenseCategory, limit));
    }
}
