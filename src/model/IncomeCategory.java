package model;

public enum IncomeCategory implements MoneyCategory {
    ALLOWANCE("Allowance"),
    EARNINGS("Earnings"),
    SALARY("Salary"),
    REWARDS("Rewards"),
    FOUND("Found"),
    FREELANCE("Freelance");

    private final String displayName;

    IncomeCategory (String displayName) {
        this.displayName = displayName;
    }

    @Override 
    public String getDisplayName () {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
