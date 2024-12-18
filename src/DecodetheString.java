import java.util.Stack;

public class DecodetheString {
    public static String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                // Build the number (may have multiple digits)
                k = k * 10 + (c - '0');
            } else if (c == '[') {
                // Push current number and string to stacks
                countStack.push(k);
                stringStack.push(currentString);
                // Reset for the next substring
                currentString = new StringBuilder();
                k = 0;
            } else if (c == ']') {
                // Pop from stacks and decode
                int repeatCount = countStack.pop();
                StringBuilder decodedString = stringStack.pop();
                for (int i = 0; i < repeatCount; i++) {
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            } else {
                // Append characters to the current string
                currentString.append(c);
            }
        }

        return currentString.toString();
    }

    public static void main(String[] args) {
        String input = "3[a2[c]]";
        System.out.println("Decoded string: " + decodeString(input)); // Output: "aaabcbc"
    }
}
