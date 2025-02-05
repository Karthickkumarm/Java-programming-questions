import java.util.ArrayList;
import java.util.List;

public class DecodeTheDigitstoStringBT {
    public static void main(String[] args) {
        String s="1224";
        List<String> result=new ArrayList<>();
        back(s,0,"",result);
        System.out.println(result);
    }
    private static void back(String s,int index,String k,List<String> result){
        if(index==s.length()){
            result.add(k);
            k="";
            return;
        }
        int n=s.charAt(index)-'0';
        back(s, index+1, k+(char)('a'+n-1), result);
        if(index+1<s.length()){
            int m=Integer.parseInt(s.substring(index,index+2));
            if(m<=26){
                back(s, index+2, k+(char)('a'+m-1), result);
            }
        }
    }
}
