import java.util.*;

public class SubstringsEval {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.next();
        int start = in.nextInt();
        int end = in.nextInt();
        in.close();
        System.out.println(input.substring(start, end));
    }
}