package model;

public enum ExpenseCategory implements MoneyCategory {
    FOOD("Food"),
    TRANSPORT("Transport"),
    BILLS("Bills"),
    SHOPPING("Shopping"),
    ENTERTAINMENT("Entertainment"),
    HEALTHCARE("Healthcare"),
    EDUCATION("Education"),
    OTHER("Other");

    private final String displayName;

    ExpenseCategory(String displayName) {
        this.displayName = displayName;
    }

    @Override 
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}