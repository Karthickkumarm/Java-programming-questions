import java.util.Arrays;
import java.util.TreeSet;

public class NextGreatestElement {
    public static void main(String[] args) {
        int[] array = {6, 3, 9, 10, 8, 2, 1, 15, 7};
        int[] result = findSmallestGreaterElements(array);

        // Print the result
        for (int i = 0; i < result.length; i++) {
            if (result[i] == -1) {
                System.out.print("_ ");
            } else {
                System.out.print(result[i] + " ");
            }
        }
    }

    public static int[] findSmallestGreaterElements(int[] array) {
        int n = array.length;
        int[] result = new int[n];
        TreeSet<Integer> treeSet = new TreeSet<>();

        // Add all elements to the TreeSet
        for (int num : array) {
            treeSet.add(num);
        }

        // Find the smallest greater element for each element
        for (int i = 0; i < n; i++) {
            Integer nextGreater = treeSet.higher(array[i]);
            if (nextGreater != null) {
                result[i] = nextGreater;
            } else {
                result[i] = -1; // No greater element found
            }
        }

        return result;
    }
}