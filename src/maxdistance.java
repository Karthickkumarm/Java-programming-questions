import java.util.*;
public class maxdistance{
        public static int maxDistance(String s) {
            int[] lastIndex = new int[256]; // Assuming ASCII characters
            Arrays.fill(lastIndex, -1);
            int maxDistance = 0;

            for (int i = 0; i < s.length(); i++) {
                int charIndex = s.charAt(i);
                if (lastIndex[charIndex] != -1) {
                    maxDistance = Math.max(maxDistance, i - lastIndex[charIndex]);
                }
                lastIndex[charIndex] = i;
            }

            return maxDistance-1;
        }

        public static void main(String[] args) {
            String s = "abcabcdefag";
            int result = maxDistance(s);
            System.out.println(result);
        }


}
