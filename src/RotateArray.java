import java.util.Arrays;

public class RotateArray {
    public static void leftRotate(int[] arr, int k) {
        int n = arr.length;
        k = k % n; // Handle cases where k > n
        
        reverse(arr, 0, k - 1);   // Reverse first k elements
        reverse(arr, k, n - 1);   // Reverse remaining elements
        reverse(arr, 0, n - 1);   // Reverse the whole array
    }

    private static void reverse(int[] arr, int left, int right) {
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5,6,7};
        int k = 3;
        
        leftRotate(arr, k);
        System.out.println(Arrays.toString(arr)); // Output: [3, 4, 5, 1, 2]
    }
}

