import java.util.Arrays;

public class Bubble {
        public static void main(String[] args) {
            int[] arr ={4,2,6,3,5,1};
            for(int i=0;i<arr.length;i++){
                for(int j=1;j< arr.length-i;j++){
                    if(arr[j]<arr[j-1]){
                        int temp=arr[j];
                        arr[j]=arr[j-1];
                        arr[j-1]=temp;
                    }
                }
            }
            System.out.println(Arrays.toString(arr));
        }
}

