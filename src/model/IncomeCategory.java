package model;

public enum IncomeCategory {
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

    String getDisplayName () {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
