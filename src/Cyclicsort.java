import java.util.Arrays;

public class Cyclicsort {
    public static void main(String[] args) {
        int[] arr = {3,4,2,5,1};
        sort(arr);

    }

    static void sort(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            int correct = arr[i] - 1;
            if (arr[i]>0 && arr[i] != arr[correct]) {
                swap(arr, i , correct);
            } else {
                i++;
            }
            System.out.println(Arrays.toString(arr));
        }
    }

    static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
    
}