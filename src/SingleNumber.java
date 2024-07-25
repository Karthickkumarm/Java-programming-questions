public class SingleNumber {
    public static void main(String[] args) {
        int[] a={2,3,4,1,3,4,2};
        int ans=a[0];
        for(int i=1;i<a.length;i++){
            ans^=a[i];
        }
        System.out.println(ans);
    }
}
