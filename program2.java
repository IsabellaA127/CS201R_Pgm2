import java.util.Scanner;

/*
  Name: Isabella Armstrong
 Assignment: Program 2 - Number Conversions
 Date: 9/26/26
 Description: Interactive console program to convert between binary and decimal numbers
 *              without using built-in Java conversion functions!!
 */
public class pr {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char choice;

        do {
            choice = displayMenu(scanner);

            if (choice == 'a') {
                int status = binToDec(scanner);
                if (status == -1) {
                    System.out.println("Error: Invalid binary input. Conversion aborted.");
                }
            } else if (choice == 'b') {
                int status = decToBin(scanner);
                if (status == -1) {
                    System.out.println("Error: Invalid decimal input. Conversion aborted.");
                }
            }
        } while (choice != 'c');

        System.out.println("Exiting program. Goodbye!");
        scanner.close();
    }

    /**
     * PRE-condition: Scanner must be initialized and open for console input.
     * POST-condition: Returns a valid menu choice ('a', 'b', or 'c').
     */
    public static char displayMenu(Scanner scanner) {
        String input;
        char choice = ' ';
        boolean valid = false;

        while (!valid) {
            System.out.println("\n==================================");
            System.out.println("         NUMBER CONVERSIONS       ");
            System.out.println("==================================");
            System.out.println("a. Convert from binary to decimal");
            System.out.println("b. Convert a decimal number into binary");
            System.out.println("c. Exit");
            System.out.print("Enter your choice (a-c): ");

            input = scanner.nextLine().trim().toLowerCase();

            if (input.length() == 1) {
                choice = input.charAt(0);
                if (choice == 'a' || choice == 'b' || choice == 'c') {
                    valid = true;
                } else {
                    System.out.println("Invalid option. Please enter 'a', 'b', or 'c'.");
                }
            } else {
                System.out.println("Invalid option. Please enter a single character ('a', 'b', or 'c').");
            }
        }

        return choice;
    }

    public static int binToDec(Scanner scanner) {
        System.out.print("Enter a binary value: ");
        String input = scanner.nextLine().trim();

        // Validate string contains only '0's and '1's
        if (!isBinary(input)) {
            return -1;
        }

        // Calculate decimal equivalent using base-2 power expansion
        int decimalValue = 0;
        int base = 1; // 2^0

        for (int i = input.length() - 1; i >= 0; i--) {
            if (input.charAt(i) == '1') {
                decimalValue += base;
            }
            base *= 2;
        }

        System.out.println("Binary: " + input + " -> Decimal: " + decimalValue);
        return 1;
    }

    /**
     * PRE-condition: Scanner must be initialized and open.
     * POST-condition: Validates decimal input string, calculates binary value without
     *                built-in conversion functions, displays output, and returns 1 (or -1 if invalid).
     */
    public static int decToBin(Scanner scanner) {
        System.out.print("Enter a positive decimal value: ");
        String input = scanner.nextLine().trim();

        // Validate string contains only digits 0-9
        if (!isDigits(input)) {
            return -1;
        }

        // Convert string to integer manually
        int decimalValue = 0;
        for (int i = 0; i < input.length(); i++) {
            decimalValue = decimalValue * 10 + (input.charAt(i) - '0');
        }

        // Handle edge case 0
        if (decimalValue == 0) {
            System.out.println("Decimal: 0 -> Binary: 0");
            return 1;
        }

        // Calculate binary equivalent using successive division by 2
        String binaryString = "";
        int temp = decimalValue;

        while (temp > 0) {
            int remainder = temp % 2;
            binaryString = remainder + binaryString;
            temp /= 2;
        }

        System.out.println("Decimal: " + decimalValue + " -> Binary: " + binaryString);
        return 1;
    }

    /**
     * PRE-condition: Takes any string input.
     * POST-condition: Returns true if string is non-empty and contains only digits ('0'-'9'), false otherwise.
     */
    public static boolean isDigits(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch < '0' || ch > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * PRE-condition: Takes any string input.
     * POST-condition: Returns true if string is non-empty and contains only '0' or '1', false otherwise.
     */
    public static boolean isBinary(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch != '0' && ch != '1') {
                return false;
            }
        }
        return true;
    }
}