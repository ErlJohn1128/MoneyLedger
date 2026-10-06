package model;

import java.math.BigDecimal;

public class Account {
    private final String name;
    private BigDecimal balance;

    public Account(String name, BigDecimal balance) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Account name is required.");
        }
        if (balance == null) {
            throw new IllegalArgumentException("Balance cannot be null.");
        }

        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    // Soon to be implemented, to track balance in different wallet
    public void deposit(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        balance = balance.subtract(amount);
    }
}
