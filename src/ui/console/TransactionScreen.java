package ui.console;

import model.ExpenseCategory;
import model.IncomeCategory;
import model.TransactionCategory;
import model.Wallet;
import model.TransactionType;
import service.FinanceService;

import java.math.BigDecimal;

public class TransactionScreen {
    private final FinanceService financeService;
    private final ConsoleInput input;

    public TransactionScreen(FinanceService financeService, ConsoleInput input) {
        this.financeService = financeService;
        this.input = input;
    }

    public void addTransaction() {
        System.out.println();
        System.out.println("======= ADD TRANSACTION =======");
        System.out.println("1. Income");
        System.out.println("2. Expense");

        int typeChoice = input.readInt("Select type: ");
        TransactionType type;

        if (typeChoice == 1) {
            type = TransactionType.INCOME;
        } else if (typeChoice == 2) {
            type = TransactionType.EXPENSE;
        } else {
            System.out.println("Invalid transaction type.");
            return;
        }

        String description = input.readLine("Description: ");
        BigDecimal amount = input.readBigDecimal("Amount: ");

        if (amount.signum() <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        System.out.println("Choose category:");
        if (type == TransactionType.EXPENSE) {
            for (ExpenseCategory category : ExpenseCategory.values()) {
                System.out.println((category.ordinal() + 1) + ". " + category.getDisplayName());
            }
        }
        else {
            for (IncomeCategory category : IncomeCategory.values()) {
                System.out.println((category.ordinal() + 1) + ". " + category.getDisplayName());
            }
        }
        

        int categoryChoice = input.readInt("Category: ");
        TransactionCategory category;

        if (type == TransactionType.EXPENSE) {
            try {
                category = ExpenseCategory.values()[categoryChoice - 1];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid category selection.");
                return;
            }
        }
        else {
            try {
                category = IncomeCategory.values()[categoryChoice - 1];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid category selection.");
                return;
            }
        }
        

        System.out.println("Choose wallet:");
        for (Wallet walletMethod : Wallet.values()) {
            System.out.println((walletMethod.ordinal() + 1) + ". " + walletMethod.getDisplayName());
        }

        int paymentChoice = input.readInt("Wallet: ");
        Wallet paymentMethod;

        try {
            paymentMethod = Wallet.values()[paymentChoice - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid payment method selection.");
            return;
        }

        try {
            if (category instanceof ExpenseCategory expenseCategory) {
                financeService.createExpenseTransaction(description, amount, expenseCategory, paymentMethod);
            } else if (category instanceof IncomeCategory incomeCategory) {
                financeService.createIncomeTransaction(description, amount, incomeCategory, paymentMethod);
            } else {
                throw new IllegalStateException("Unsupported transaction category.");
            }
            System.out.println("Transaction added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}