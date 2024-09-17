import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;


public class Anil {
    public static List<Integer> sortWithRestrictions(List<Integer> input) {
        
        List<Integer> positives = input.stream()
                .filter(n -> n >= 0)
                .distinct()
                .sorted()
                .collect(toList());

        List<Integer> negatives = input.stream()
                .filter(n -> n < 0)
                .distinct()
                .sorted()
                .collect(toList());

        List<Integer> result = new ArrayList<>();
        int posIndex = 0, negIndex = 0;

        while (posIndex < positives.size() || negIndex < negatives.size()) {
            if (posIndex < positives.size()) {
                result.add(positives.get(posIndex++));
            }
            if (negIndex < negatives.size()) {
                result.add(negatives.get(negIndex++));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> input = Arrays.asList(-1, -8, -3, 3, 0, 1, 5, 4, 11, -2, -11, -7, -6, -5, -4);
        List<Integer> output = sortWithRestrictions(input);
        System.out.println("Input: " + input);
        System.out.println("Output: " + output);
    }
}
