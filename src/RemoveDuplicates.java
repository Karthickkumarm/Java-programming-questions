import java.util.HashSet;

public class RemoveDuplicates {
    public static String removeDuplicates(String input) {
        HashSet<Character> seen = new HashSet<>();
        StringBuilder result = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (!seen.contains(c)) {
                // If the character is not a duplicate, add it to the result
                result.append(c);
                seen.add(c);
            } else {
                // If the character is a duplicate, replace it with the next available character/digit
                char replacement = getNextAvailableChar(c, seen);
                result.append(replacement);
                seen.add(replacement);
            }
        }

        return result.toString();
    }

    private static char getNextAvailableChar(char c, HashSet<Character> seen) {
        if (Character.isDigit(c)) {
            // Handle digits (0-9)
            int num = c-'0';
            while (true) {
                num = (num + 1) % 10; // Wrap around after 9
                char nextChar = (char) (num+'0');
                if (!seen.contains(nextChar)) {
                    return nextChar;
                }
            }
        } else if (Character.isLetter(c)) {
            // Handle letters (a-z, A-Z)
            char nextChar = c;
            while (true) {
                nextChar++;
                if (nextChar > 'z' && c >= 'a' && c <= 'z') {
                    nextChar = 'A'; // Wrap around to 'A' after 'z'
                } else if (nextChar > 'Z' && c >= 'A' && c <= 'Z') {
                    nextChar = 'a'; // Wrap around to 'a' after 'Z'
                }
                if (!seen.contains(nextChar)) {
                    return nextChar;
                }
            }
        } else {
            // Handle other characters (no replacement)
            return c;
        }
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(removeDuplicates("Java1234"));    // Output: Javb1234
        System.out.println(removeDuplicates("Python1223"));  // Output: Python1234
        System.out.println(removeDuplicates("aBuzZ9900"));   // Output: aBuzC9012
    }
}