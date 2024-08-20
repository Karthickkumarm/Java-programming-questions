public class zoho_rotation {
        public static String rotateString(String s, String r, int n) {
            int len = s.length();
            n = n % len; // Handle rotations greater than string length
            char[] charArray = s.toCharArray();

            if (r.equals("L")) {
                // Left rotation
                reverse(charArray, 0, n - 1);
                reverse(charArray, n, len - 1);
                reverse(charArray, 0, len - 1);
            } else if (r.equals("R")) {
                // Right rotation is equivalent to left rotation by len - n
                n = len - n;
                reverse(charArray, 0, n - 1);
                reverse(charArray, n, len - 1);
                reverse(charArray, 0, len - 1);
            } else {
                throw new IllegalArgumentException("Invalid rotation direction");
            }

            return new String(charArray);
        }
        private static void reverse(char[] charArray, int start, int end) {
            while (start < end) {
                char temp = charArray[start];
                charArray[start] = charArray[end];
                charArray[end] = temp;
                start++;
                end--;
            }
        }

        public static void main(String[] args) {
            String s = "ZOHOCORPORATION";
            String r = "R";
            int n = 4;
            String rotatedString = rotateString(s, r, n);
            System.out.println(rotatedString);
        }

}
