public class LongestContinuousTF {
    public static void main(String[] args) {
        String ansKey = "TFTFTTF";
        int maxOps = 2;
        int maxLen = Math.max(longestSequence(ansKey, 'T', maxOps), 
                              longestSequence(ansKey, 'F', maxOps));
        System.out.println(maxLen); // Output: 3
    }
    public static int longestSequence(String s, char target, int k) {
        int left = 0, right = 0, maxLen = 0, changes = 0;

        while (right < s.length()) {
            if (s.charAt(right) != target) {
                changes++;
            }

            while (changes > k) {
                if (s.charAt(left) != target) {
                    changes--;
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }

        return maxLen;
    }
}
