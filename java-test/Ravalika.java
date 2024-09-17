/*
 Sort an array in ascending order taking care of following restrictions:
 - Odd cells should be used to store positive numbers and zero;
 - Even cells should be used to store negative values;
 - Repeated values ​​should be ignored;
 - If there are more negative numbers than positive numbers or vice versa, 
   the remaining numbers must be added to the end of the array in ascending order.

Examples:
1:
 Input: [-5, 1, 2, 10, -3, 1]
 Output: [1, -5, 2, -3, 10]

2:
 Input: [-1, -8, -3, 3, 0, 1]
 Output: [0, -8, 1, -3, 3, -1]

3:
 Entry: [-1, -8, -3, 3, 0, 1, 5, 4, 4, 4, 10, 11]
 Output: [0, -8, 1, -3, 3, -1, 4, 5, 10, 11]

4:
 Entry: [-1, -8, -3, 3, 0, 1, 5, 4, 11, -2, -11, -7, -6, -5, -4]
 Output: [0, -11, 1, -8, 3, -7, 4, -6, 5, -5, 11, -4, -3, -2, -1]

*/

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Ravalika {
  public static void main(String[] args) {
        int[] input1 = {-5, 1, 2, 10, -3, 1};
        int[] input2 = {-1, -8, -3, 3, 0, 1};
        int[] input3 = {-1, -8, -3, 3, 0, 1, 5, 4, 4, 4, 10, 11};
        int[] input4 = {-1, -8, -3, 3, 0, 1, 5, 4, 11, -2, -11, -7, -6, -5, -4};

        System.out.println(Arrays.toString(sortArray(input1))); 
        System.out.println(Arrays.toString(sortArray(input2))); 
        System.out.println(Arrays.toString(sortArray(input3))); 
        System.out.println(Arrays.toString(sortArray(input4))); 
    }

    private static int[] sortArray(int[] input) {
     
        List<Integer> list = Arrays.stream(input).distinct().sorted().boxed().collect(Collectors.toList());
        
        List<Integer> positives = new ArrayList<>();
        List<Integer> negatives = new ArrayList<>();

     
        for (int num : list) {
            if (num >= 0) {
                positives.add(num);
            } else {
                negatives.add(num);
            }
        }

     
        int[] result = new int[positives.size() + negatives.size()];

        int posIndex = 0, negIndex = 0, index = 0;
        while (posIndex < positives.size() && negIndex < negatives.size()) {
            if (index % 2 == 0) {
                result[index++] = positives.get(posIndex++);
            } else {
                result[index++] = negatives.get(negIndex++);
            }
        }


        while (posIndex < positives.size()) {
            result[index++] = positives.get(posIndex++);
        }

     
        while (negIndex < negatives.size()) {
            result[index++] = negatives.get(negIndex++);
        }

        return result;
    }

}