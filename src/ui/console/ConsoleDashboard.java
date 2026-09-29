package ui.console;

import service.FinanceService;

import java.math.BigDecimal;

public class ConsoleDashboard {
    private final FinanceService financeService;

    public ConsoleDashboard(FinanceService financeService) {
        this.financeService = financeService;
    }

    public void display() {
        BigDecimal income = financeService.getTotalIncome();
        BigDecimal expenses = financeService.getTotalExpenses();
        BigDecimal balance = financeService.getBalance();

        System.out.println();
        System.out.println("================================");
        System.out.println("           DASHBOARD");
        System.out.println("================================");
        System.out.printf("Balance     : PHP %,.2f%n", balance);
        System.out.printf("Income      : PHP %,.2f%n", income);
        System.out.printf("Expenses    : PHP %,.2f%n", expenses);
        System.out.printf("Transactions: %d%n", financeService.getTransactionCount());
        System.out.println("================================");
    }
}