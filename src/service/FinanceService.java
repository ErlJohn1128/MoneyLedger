package service;

import model.Account;
import model.Budget;
import model.ExpenseCategory;
import model.MoneyCategory;
import model.PaymentMethod;
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

    public void addTransaction(Transaction transaction) {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction is required.");
        }

        transactions.add(transaction);

        if (transaction.getType() == TransactionType.INCOME) {
            account.deposit(transaction.getAmount());
        } 
        else {
            account.withdraw(transaction.getAmount());
        }

        if (transaction.getType() == TransactionType.EXPENSE && transaction.getCategory() instanceof ExpenseCategory) {
            ExpenseCategory expenseCategory = (ExpenseCategory) transaction.getCategory();
            for (Budget budget : budgets) {
                if (budget.getCategory() == expenseCategory) {
                    budget.addExpense(transaction.getAmount());
                }
            }
        }
    }

    public Transaction createTransaction(String description, BigDecimal amount, TransactionType type, MoneyCategory category, PaymentMethod paymentMethod) {
        Transaction transaction = new Transaction(nextId++, description, amount, type, category, paymentMethod);
        addTransaction(transaction);
        return transaction;
    }

    public List<Transaction> getTransactions() {
        List<Transaction> copy = new ArrayList<>(transactions);
        return Collections.unmodifiableList(copy);
    }

    public BigDecimal getTotalIncome() {
        BigDecimal total = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.INCOME) {
                total = total.add(transaction.getAmount());
            }
        }
        return total;
    }

    public BigDecimal getTotalExpenses() {
        BigDecimal total = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.EXPENSE) {
                total = total.add(transaction.getAmount());
            }
        }
        return total;
    }

    public BigDecimal getBalance() {
        return getTotalIncome().subtract(getTotalExpenses());
    }

    public int getTransactionCount() {
        return transactions.size();
    }

    public List<Budget> getBudgets() {
        return Collections.unmodifiableList(budgets);
    }

    public void addBudget(Budget budget) {
        if (budget == null) {
            throw new IllegalArgumentException("Budget is required.");
        }
        budgets.add(budget);
    }

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

    public BigDecimal getCategoryTotal(ExpenseCategory category) {
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

    public BigDecimal getCashFlow() {
        return getTotalIncome().subtract(getTotalExpenses());
    }

    public Account getAccount() {
        return account;
    }
}
