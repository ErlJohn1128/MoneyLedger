package ui.console;

import model.ExpenseCategory;
import model.IncomeCategory;
import model.MoneyCategory;
import model.PaymentMethod;
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
        MoneyCategory category;

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
        

        System.out.println("Choose payment method:");
        for (PaymentMethod paymentMethod : PaymentMethod.values()) {
            System.out.println((paymentMethod.ordinal() + 1) + ". " + paymentMethod.getDisplayName());
        }

        int paymentChoice = input.readInt("Payment method: ");
        PaymentMethod paymentMethod;

        try {
            paymentMethod = PaymentMethod.values()[paymentChoice - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid payment method selection.");
            return;
        }

        try {
            financeService.createTransaction(description, amount, type, category, paymentMethod);
            System.out.println("Transaction added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}