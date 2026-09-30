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

This whole structure follows a clean design.
1. Classes being in a package
2. Access modifiers usage
3. Inheritance: Can be seen inside model/, this files: ExpenseCategory.java (Child), IncomeCategory.java (Child), and MoneyCategory (Parent)
4. It is abstracted (logics are hidden and simplified)
5. I also do dynamic dispatch, it is through MoneyCategory. Users defines if it would be ExpenseCategory or IncomeCategory in runtime.