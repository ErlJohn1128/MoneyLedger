package ui.console;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleInput {
    private final Scanner scanner = new Scanner(System.in);

    public String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int readInt(String prompt) {
        while (true) {
            String value = readLine(prompt);
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
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