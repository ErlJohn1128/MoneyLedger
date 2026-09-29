package ui.console;

import model.TransactionType;
import service.FinanceService;

public class ConsoleMenu {
    private final ConsoleInput input;
    private final FinanceService financeService;
    private final ConsoleDashboard dashboard;
    private final TransactionScreen transactionScreen;
    private final BudgetUI budgetUI;
    private final ReportUI reportUI;
    private final AccountUI accountUI;
    private boolean running = true;

    public ConsoleMenu() {
        input = new ConsoleInput();
        financeService = new FinanceService();
        dashboard = new ConsoleDashboard(financeService);
        transactionScreen = new TransactionScreen(financeService, input);
        budgetUI = new BudgetUI(financeService);
        reportUI = new ReportUI(financeService);
        accountUI = new AccountUI(financeService);
    }

    public void start() {
        while (running) {
            displayMenu();
            int choice = input.readInt("Choose an option: ");
            handleChoice(choice);
        }
    }

    private void displayMenu() {
        System.out.println();
        System.out.println("================================");
        System.out.println("           MONEY LEDGER         ");
        System.out.println("================================");
        System.out.println("1. Dashboard");
        System.out.println("2. Add Transaction");
        System.out.println("3. Transaction History");
        System.out.println("4. Budget Overview");
        System.out.println("5. Reports");
        System.out.println("6. Account Summary");
        System.out.println("0. Exit");
        System.out.println("================================");
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                dashboard.display();
                input.pause();
                break;
            case 2:
                transactionScreen.addTransaction();
                input.pause();
                break;
            case 3:
                displayTransactionHistory();
                input.pause();
                break;
            case 4:
                budgetUI.displayBudgetOverview();
                input.pause();
                break;
            case 5:
                reportUI.displayReports();
                input.pause();
                break;
            case 6:
                accountUI.displayAccountSummary();
                input.pause();
                break;
            case 0:
                running = false;
                System.out.println("Thank you for using Money Ledger");
                break;
            default:
                System.out.println("Invalid option." );
        }
    }

    private void displayTransactionHistory() {
        System.out.println();
        System.out.println("======= TRANSACTION HISTORY =======");

        if (financeService.getTransactions().isEmpty()) {
            System.out.println("No transactions recorded.");
            return;
        }

        System.out.printf("%-4s %-18s %-10s %-12s %-15s %-10s%n",
                "ID", "Description", "Type", "Category", "Payment", "Amount");
        System.out.println("---------------------------------------------------------------");

        for (var transaction : financeService.getTransactions()) {
            String amountText = (transaction.getType() == TransactionType.INCOME ? "+" : "-") + "$ " + transaction.getAmount();
            System.out.printf("%-4d %-18s %-10s %-12s %-15s %-10s%n",
                    transaction.getId(),
                    truncate(transaction.getDescription(), 18),
                    transaction.getType(),
                    transaction.getCategory(),
                    transaction.getPaymentMethod(),
                    amountText);
        }
    }

    // Cut the description to 18 characters only
    private String truncate(String text, int maxLength) {
        if (text == null) {
            return "";
        }
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength - 3) + "...";
    }
}