package model;

public enum ExpenseCategory {
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

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}