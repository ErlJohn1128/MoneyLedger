package ui.console;

import java.math.BigDecimal;
import java.util.Scanner;

// A easier way rather than to keep on repeating Scanner scanner = new Scanner (System.in);
// also to function as a cleaner for user inputs
public class ConsoleInput {
    private final Scanner scanner = new Scanner(System.in);

    public String readLine(String input) {
        System.out.print(input);
        return scanner.nextLine().trim();
    }

    public int readInt(String input) {
        while (true) {
            String value = readLine(input);
            try {
                return Integer.parseInt(value);
            } 
            catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a whole number.");
            }
        }
    }

    public BigDecimal readBigDecimal(String prompt) {
        while (true) {
            String value = readLine(prompt);
            try {
                return new BigDecimal(value);
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a valid number.");
            }
        }
    }

    public void pause() {
        readLine("Press Enter to continue...");
    }
}