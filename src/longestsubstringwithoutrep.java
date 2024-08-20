import java.util.*;
public class longestsubstringwithoutrep {
        public static String longestSubstring(String s) {
            int n = s.length();
            int[] lastIndex = new int[256];
            Arrays.fill(lastIndex, -1);

            int start = 0, maxLength = 0, maxStart = 0,maxEnd=0;

            for (int i = 0; i < n; i++) {
                char ch = s.charAt(i);
                int index = ch;
                start = Math.max(start, lastIndex[index] + 1);
                lastIndex[index] = i;
                maxLength = Math.max(maxLength, i - start + 1);
                if (maxLength > maxEnd - maxStart) {
                    maxStart = start;
                    maxEnd = i;
                }
            }

            return s.substring(maxStart, maxEnd + 1);
        }

        public static void main(String[] args) {
            String s = "aweeacwerjkl";
            String longestSubstring = longestSubstring(s);
            System.out.println(longestSubstring);
        }

}
