package model;

// This implements MoneyCategory for dynamic dispatch
public enum ExpenseCategory implements MoneyCategory {

    // Types of possiblie expense category for users
    FOOD("Food"),
    TRANSPORT("Transport"),
    BILLS("Bills"),
    SHOPPING("Shopping"),
    ENTERTAINMENT("Entertainment"),
    HEALTHCARE("Healthcare"),
    EDUCATION("Education"),
    OTHER("Other");

    private final String displayName;

    // Constructor
    ExpenseCategory(String displayName) {
        this.displayName = displayName;
    }

    @Override 
    public String getDisplayName() {
        return displayName;
    }

    // To show Expense Category not as object but rather a string
    @Override
    public String toString() {
        return displayName;
    }
}