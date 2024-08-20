import java.util.*;

public class anagrams {
    public static void main(String[] args) {
        String[]  s={"eat","tan","tea","ant","nat"};
        Map<String, List<String>> group = new HashMap<>();
        for(String a : s){
            char[] c=a.toCharArray();
            Arrays.sort(c);
            String s1=String.valueOf(c);
            if(!group.containsKey(s1)){
                group.put(s1,new ArrayList<String>());
            }
            group.get(s1).add(a);
        }
        System.out.println(group.values());


    }
}
