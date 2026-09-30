package ui.console;

import service.FinanceService;

public class AccountUI {
    private final FinanceService financeService;

    public AccountUI(FinanceService financeService) {
        this.financeService = financeService;
    }

    public void displayAccountSummary() {
        System.out.println();
        System.out.println("======= ACCOUNT SUMMARY =======");
        System.out.printf("Account name: %s%n", financeService.getAccount().getName());
        System.out.printf("Current balance: $ %,.2f%n", financeService.getAccount().getBalance());
        System.out.printf("Available cash flow: $ %,.2f%n", financeService.getCashFlow());
    }
}
