package service;

import model.Account;
import model.Budget;
import model.ExpenseCategory;
import model.ExpenseTransaction;
import model.IncomeCategory;
import model.IncomeTransaction;
import model.Wallet;
import model.Transaction;
import model.TransactionType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 
    This is the file, the logic of my application. The main features are
    addTransaction to the List, and the generations of reports.
*/
public class FinanceService {
    private final List<Transaction> transactions = new ArrayList<>();
    private final List<Budget> budgets = new ArrayList<>();
    private final Account account = new Account("Main Account", BigDecimal.ZERO);
    private int nextId = 1;

    // Adds transaction to List<Transactions> 
    public void addTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction is required.");
        }

        transactions.add(transaction);

        transaction.applyTo(account);

        // Adds the transaction if EXPENSE to the List<Budget> 
        if (transaction instanceof ExpenseTransaction expenseTransaction) {
            for (Budget budget : budgets) {
                if (budget.getCategory() == expenseTransaction.getCategory()) {
                    budget.addExpense(transaction.getAmount());
                }
            }
        }
    }

    // Creates transaction, uses addTransaction as helper function
    public IncomeTransaction createIncomeTransaction(String description, BigDecimal amount,
                                                     IncomeCategory category, Wallet paymentMethod) {
        IncomeTransaction transaction = new IncomeTransaction(nextId, description, amount, category, paymentMethod);
        addTransaction(transaction);
        nextId++;
        return transaction;
    }

    public ExpenseTransaction createExpenseTransaction(String description, BigDecimal amount,
                                                        ExpenseCategory category, Wallet paymentMethod) {
        ExpenseTransaction transaction = new ExpenseTransaction(nextId, description, amount, category, paymentMethod);
        addTransaction(transaction);
        nextId++;
        return transaction;
    }

    // Getter: return the List<Transaction> array
    public List<Transaction> getTransactions() {
        List<Transaction> copy = new ArrayList<>(transactions);
        return Collections.unmodifiableList(copy);
    }

    // Calculates the total income by iterating to List<Transactions>
    public BigDecimal getTotalIncome() {
        BigDecimal total = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.INCOME) {
                total = total.add(transaction.getAmount());
            }
        }
        return total;
    }

    // Calculate the total expenses by iterating in List<Transactions>
    public BigDecimal getTotalExpenses() {
        BigDecimal total = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.EXPENSE) {
                total = total.add(transaction.getAmount());
            }
        }
        return total;
    }

    // Calculates balance throught getTotalIncome() - getTotalExpense()
    public BigDecimal getBalance() {
        return getTotalIncome().subtract(getTotalExpenses());
    }

    // Gets the transaction count by getting the size of the array
    public int getTransactionCount() {
        return transactions.size();
    }

    // Getter: returns the List<Budget> array
    public List<Budget> getBudgets() {
        return Collections.unmodifiableList(budgets);
    }

    // Addition of possible budget category
    public void addBudget(Budget budget) {
        if (budget == null) {
            throw new IllegalArgumentException("Budget is required.");
        }
        budgets.add(budget);
    }

    // Return an array containing pairs which is <ExpenseCategory, $MoneySpent>
    public Map<ExpenseCategory, BigDecimal> getExpensesByCategory() {
        Map<ExpenseCategory, BigDecimal> totals = new HashMap<>();
        for (Transaction transaction : transactions) {
            if (transaction instanceof ExpenseTransaction expenseTransaction) {
                ExpenseCategory category = expenseTransaction.getCategory();
                BigDecimal current = totals.getOrDefault(category, BigDecimal.ZERO);
                totals.put(category, current.add(transaction.getAmount()));
            }
        }
        return totals;
    }

    /* 
        The following functions: getExpenseCategoryTotal() and getIncomeCategoryTotal()
        calculates the total money poured into each other category
    */
    public BigDecimal getExpenseCategoryTotal (ExpenseCategory category) {
        BigDecimal total = BigDecimal.ZERO;

        for (Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.EXPENSE
                    && transaction.getCategory() == category) {
                total = total.add(transaction.getAmount());
            }
        }
        return total;
    }

    public BigDecimal getIncomeCategoryTotal (IncomeCategory category) {
        BigDecimal total = BigDecimal.ZERO;

        for (Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.INCOME
                    && transaction.getCategory() == category) {
                total = total.add(transaction.getAmount());
            }
        }
        return total;
    }

    public BigDecimal getCashFlow() {
        return getTotalIncome().subtract(getTotalExpenses());
    }

    public Account getAccount() {
        return account;
    }
}
