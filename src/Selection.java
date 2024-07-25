import java.util.Arrays;

public class Selection {
    public static void main(String[] args) {
        int[] a={3,5,2,4,1};
        /*for(int i=0;i<a.length-1;i++){
            int min=i;
            for(int j=i+1;j<a.length;j++){
                if(a[j]<a[min]){
                    min=j; // finding min
                }
            }
            int t=a[i];
            a[i]=a[min];
            a[min]=t;
        }*/
        for(int i=0;i<a.length;i++){
            int max=0;
            for(int j=0;j<a.length-i;j++){
                if(a[j]>a[max]){
                    max=j; // finding min
                }
            }
            int t=a[a.length-i-1];
            a[a.length-i-1]=a[max];
            a[max]=t;
        }
        System.out.println(Arrays.toString(a));
    }
}
