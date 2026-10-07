## Money Ledger

My own version of Money tracker. It lets you record your expenses and incomes in an
organize way. 

### Ongoing Project

### Design and Structure

model/ folder contains the model that was used
in the program. 

service/ on the other hand uses the model, It is the main logic of the application.

ui/ contains the UI for the application, inside is console/ and gui/
    console/ is for console based interface 
    gui/ which is not yet implement is for future GUI 

### Where did I apply the lessons I learned in OOP

- **Encapsulation:** Model fields are private, and classes like how `Account` and `Budget` validate and manage their own state. And a lot of properties in this program are private.
- **Abstraction:** This application hides a lot of complexity such as `Transaction` is an abstract base class for shared transaction data and behavior contracts. `TransactionCategory` defines the shared display-name contract implemented by the category enums.
- **Inheritance:** `IncomeTransaction` and `ExpenseTransaction` inherit their common fields and behavior from `Transaction`. And other class to do inheritance.
- **Polymorphism and dynamic dispatch:** `FinanceService` stores both subclasses as `Transaction` objects and calls `applyTo(account)`. The runtime transaction subtype determines whether the account receives a deposit or withdrawal.
- **Composition:** A transaction has a category and wallet, and the finance service works with account, transaction, and budget objects.

`TransactionType` describes whether a transaction is income or expense, while `IncomeCategory` and `ExpenseCategory` describe the source or purpose. They are separate concepts; the typed transaction subclasses ensure that an income transaction cannot be created with an expense category.

Thank you

The diagram can be found here: https://drive.google.com/file/d/1Y5AZRuI9DPAGbZnGucgRVSqvKlzpJBDW/view?usp=sharing