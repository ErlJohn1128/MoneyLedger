package model;


// This implements TransactionCategory for dynamic dispatch
public enum IncomeCategory implements TransactionCategory {

    // Types of possible income source for users
    ALLOWANCE("Allowance"),
    EARNINGS("Earnings"),
    SALARY("Salary"),
    REWARDS("Rewards"),
    FOUND("Found"),
    FREELANCE("Freelance");

    private final String displayName;

    // Constructor
    IncomeCategory (String displayName) {
        this.displayName = displayName;
    }

    @Override 
    public String getDisplayName () {
        return displayName;
    }

    // To show Income Category not as object but rather a string
    @Override
    public String toString() {
        return displayName;
    }
}
