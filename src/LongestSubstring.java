import java.util.Scanner;
import java.util.HashSet;

// public class LongestSubstring {
    
//     // Function to find the length of the longest substring without repeating characters
//     public static int longestUniqueSubstring(String s) {
//         int maxLength = 0;
//         int left = 0;
//         HashSet<Character> set = new HashSet<>();
        
//         for (int right = 0; right < s.length(); right++) {
//             while (set.contains(s.charAt(right))) {
//                 set.remove(s.charAt(left));
//                 left++;
//             }
//             set.add(s.charAt(right));
//             maxLength = Math.max(maxLength, right - left + 1);
//         }
        
//         return maxLength;
//     }
    
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         System.out.print("Enter a string: ");
//         String input = scanner.nextLine();
//         scanner.close();
        
//         System.out.println("Length of longest substring without repeating characters: " + longestUniqueSubstring(input));
//     }
// }


public class LongestSubstring {
    
    // Function to find the longest substring without repeating characters
    public static String longestUniqueSubstring(String s) {
        int maxLength = 0, left = 0, start = 0;
        HashSet<Character> set = new HashSet<>();
        String longestSubstring = "";
        
        for (int right = 0; right < s.length(); right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                start = left;
            }
        }
        
        longestSubstring = s.substring(start, start + maxLength);
        return longestSubstring;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        scanner.close();
        
        System.out.println("Longest substring without repeating characters: " + longestUniqueSubstring(input));
    }
}
