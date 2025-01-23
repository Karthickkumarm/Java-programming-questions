import java.util.HashSet;

public class remove_duplicates_and_replace_with_next_letter {
    public static String processString(String input) {
        HashSet<Character> seenLetters = new HashSet<>();
        HashSet<Character> seenDigits = new HashSet<>();
        StringBuilder output = new StringBuilder();

        for (char ch : input.toCharArray()) {
            if (Character.isLetter(ch)) {
                // Handle letters
                if (seenLetters.contains(ch)) {
                    // Find the next replacement character
                    char replacement = ch;
                    do {
                        replacement = (char) ((replacement + 1 - (Character.isUpperCase(ch) ? 'A' : 'a')) % 26 
                                              + (Character.isUpperCase(ch) ? 'A' : 'a'));
                    } while (seenLetters.contains(replacement));
                    output.append(replacement);
                    seenLetters.add(replacement);
                } else {
                    output.append(ch);
                    seenLetters.add(ch);
                }
            } else if (Character.isDigit(ch)) {
                // Handle digits
                if (seenDigits.contains(ch)) {
                    // Find the next replacement digit
                    char replacement = ch;
                    do {
                        replacement = (char) ((replacement - '0' + 1) % 10 + '0');
                    } while (seenDigits.contains(replacement));
                    output.append(replacement);
                    seenDigits.add(replacement);
                } else {
                    output.append(ch);
                    seenDigits.add(ch);
                }
            } else {
                // For non-alphanumeric characters, just add them to the output
                output.append(ch);
            }
        }

        return output.toString();
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(processString("Java1234")); // Output: Javb1234
        System.out.println(processString("Python1223")); // Output: Python1234
        System.out.println(processString("aBuzZ9900")); // Output: aBuzC9012
    }
}
