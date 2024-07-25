import java.util.Arrays;

public class Insertion {
    public static void main(String[] args) {
        int[] a={34,12,67,24,122,90};
        for(int i=0;i<a.length-1;i++){
            for(int j=i+1;j>0;j--){
                if(a[j]<a[j-1]){
                    int t=a[j];
                    a[j]=a[j-1];
                    a[j-1]=t;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }
}
