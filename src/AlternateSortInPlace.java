import java.util.Arrays;

public class AlternateSortInPlace {
    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4, 5, 6, 7};
        alternateSort(input);
        System.out.println("Output: " + Arrays.toString(input));
    }

    public static void alternateSort(int[] arr) {
        Arrays.sort(arr); // Sort the array in ascending order
        int n = arr.length;
        int max = arr[n - 1] + 1; // A value larger than the maximum element in the array

        int left = 0, right = n - 1;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                // Place the largest element
                arr[i] = arr[i] + (arr[right] % max) * max;
                right--;
            } else {
                // Place the smallest element
                arr[i] = arr[i] + (arr[left] % max) * max;
                left++;
            }
            System.out.println("Output: " + arr[i]);
        }

        // Decode the array to get the final result
        for (int i = 0; i < n; i++) {
            arr[i] = arr[i] / max;
        }
    }
}