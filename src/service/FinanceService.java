package service;

import model.Account;
import model.Budget;
import model.ExpenseCategory;
import model.IncomeCategory;
import model.MoneyCategory;
import model.Wallet;
import model.Transaction;
import model.TransactionType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        // If transaction is income, we DEPOSIT, else we WITHDRAW
        if (transaction.getType() == TransactionType.INCOME) {
            account.deposit(transaction.getAmount());
        } 
        else {
            account.withdraw(transaction.getAmount());
        }

        // Adds the transaction if EXPENSE to the List<Budget> 
        if (transaction.getType() == TransactionType.EXPENSE && transaction.getCategory() instanceof ExpenseCategory) {
            ExpenseCategory expenseCategory = (ExpenseCategory) transaction.getCategory();
            
            for (Budget budget : budgets) {
                if (budget.getCategory() == expenseCategory) {
                    budget.addExpense(transaction.getAmount());
                }
            }
        }
    }

    // Creates transaction, uses addTransaction as helper function
    public Transaction createTransaction(String description, BigDecimal amount, TransactionType type, MoneyCategory category, Wallet paymentMethod) {
        Transaction transaction = new Transaction(nextId++, description, amount, type, category, paymentMethod);
        addTransaction(transaction);
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
            if (transaction.getType() == TransactionType.EXPENSE && transaction.getCategory() instanceof ExpenseCategory) {
                ExpenseCategory category = (ExpenseCategory) transaction.getCategory();
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
                    && transaction.getCategory() instanceof ExpenseCategory
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
                    && transaction.getCategory() instanceof IncomeCategory
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
