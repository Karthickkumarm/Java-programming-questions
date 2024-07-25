public class Binary {
    public static void main(String[] args) {
        int[] a={12,13,14,25,26,36,47,56,58,60,70,80};
        int t=0;
        int s=0,e=a.length-1;
        while(s<=e){
            int mid=s+(e-s)/2;
            if(t==a[mid]){
                System.out.println("Found");
                break;
            } else if (t<a[mid]) {
                e=mid-1;
            }else{
                s=mid+1;
            }
        }
        System.out.println("Not Found");
    }
}
