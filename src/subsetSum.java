import java.util.ArrayList;
import java.util.List;

public class subsetSum {

    public static void findSubsets(int[] arr, int target) {
        List<Integer> currentSubset = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        findSubsetsHelper(arr, target, 0, currentSubset, result);

        // Print the result
        for (List<Integer> subset : result) {
            System.out.println(subset);
        }
    }

    private static void findSubsetsHelper(int[] arr, int target, int index, List<Integer> currentSubset, List<List<Integer>> result) {
        // Base case: if target is 0, add the current subset to the result
        if (target == 0) {
            result.add(new ArrayList<>(currentSubset));
            return;
        }

        // Base case: if target becomes negative or we've processed all elements
        if (target < 0 || index == arr.length) {
            return;
        }

        // Include the current element in the subset
        currentSubset.add(arr[index]);
        findSubsetsHelper(arr, target - arr[index], index + 1, currentSubset, result);

        // Exclude the current element from the subset
        currentSubset.remove(currentSubset.size() - 1);
        findSubsetsHelper(arr, target, index + 1, currentSubset, result);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int target = 6;
        findSubsets(arr, target);
    }
}
