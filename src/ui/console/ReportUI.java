package ui.console;

import model.ExpenseCategory;
import service.FinanceService;

import java.math.BigDecimal;
import java.util.Map;

public class ReportUI {
    private final FinanceService financeService;

    public ReportUI(FinanceService financeService) 
    {
        this.financeService = financeService;
    }

    public void displayReports() 
    {
        System.out.println();
        System.out.println("======= FINANCIAL REPORT =======");
        System.out.printf("Total income:  $ %,.2f%n", financeService.getTotalIncome());
        System.out.printf("Total expenses: $ %,.2f%n", financeService.getTotalExpenses());
        System.out.printf("Net balance:   $ %,.2f%n", financeService.getBalance());
        System.out.println();

        System.out.println("Expense totals by category:");
        Map<ExpenseCategory, BigDecimal> totals = financeService.getExpensesByCategory();

        if (totals.isEmpty()) 
        {
            System.out.println("No category totals yet.");
            return;
        }

        for (Map.Entry<ExpenseCategory, BigDecimal> entry : totals.entrySet()) 
        {
            System.out.printf("- %-15s $ %,.2f%n", entry.getKey(), entry.getValue());
        }
    }
}
