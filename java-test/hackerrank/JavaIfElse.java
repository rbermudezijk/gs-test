import java.util.*;

/**
 * Task
 * Given an integer, , perform the following conditional actions:
 * 
 * If is odd, print Weird
 * If is even and in the inclusive range of 2 to 5, print Not Weird
 * If is even and in the inclusive range of 6 to 20, print Weird
 * If is even and greater than 20, print Not Weird
 * 
 * Complete the stub code provided in your editor to print whether or not is
 * weird.
 * 
 * Input Format
 * 
 * A single line containing a positive integer, .
 * 
 * Constraints
 * 
 * Output Format
 * 
 * Print Weird if the number is weird; otherwise, print Not Weird.
 */
public class JavaIfElse {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        if (n % 2 == 1 || (n >= 6 && n <= 20)) {
            System.out.println("Weird");
        } else {
            System.out.println("Not Weird");
        }
        scanner.close();
    }
}
