// EX20_11.java
// Match Grouping Symbols
// Josh Williams


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class EX20_11 {

    public static boolean debugging = false;

    public static void main(String[] args) throws FileNotFoundException {
        // Verify number of arguments
        if (args.length != 1) {
            System.out.println("Usage: java EX20_11 java_filename");
            System.exit(0);
        }

        // Verify file exists
        File f = new File(args[0]);
        if (!f.exists()) {
            System.out.println("File not found. Exiting...");
            System.exit(1);
        }

        // Create a stack for the symbols to reside in
        Stack<String> symbols = new Stack<>();

        // Search the characters for any matching grouping symbols
        Scanner input = new Scanner(f);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            // Walk each character
            StringTokenizer st = new StringTokenizer(line, "({[]})", true);
            while (st.hasMoreTokens()) {
                String c = st.nextToken();
                if (c.equals("{") || c.equals("(") || c.equals("[")) {
                    // Add to the stack
                    symbols.push(c);
                    if (debugging) {
                        System.out.println("Pushed: " + c);
                        System.out.println("Stack: " + symbols);
                    }
                } else if (c.equals(")") || c.equals("]") || c.equals("}")) {
                    // Ending group character. Pop the top item and compare.
                    String symb = symbols.pop();
                    if ((symb.equals("(") && !c.equals(")")) || (symb.equals("[") && !c.equals("]")) || (symb.equals("{") && !c.equals("}"))) {
                        System.out.println("The file has an error with grouping symbols.");
                        System.out.println("Expected match for: " + symb + " got: " + c);
                        System.out.println("Line: " + line);
                        System.exit(1);
                    }
                }
            }
            // If there are any symbols left, we have an error.
            if (!symbols.empty()) {
                System.out.println("The file has an error with grouping symbols.");
                System.out.println("Expected match for: " + symbols.pop() + " got: nothing");
                System.exit(1);
            }
        }
        System.out.println("The file was processed successfully.");
    }
}