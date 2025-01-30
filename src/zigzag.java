public class zigzag {
    public static void zigZagTraversal(int[][] matrix) {
        int n = matrix.length;    // Number of rows
        int m = matrix[0].length; // Number of columns

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                // Left to right traversal for even-indexed rows
                for (int j = 0; j < m; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
            } else {
                // Right to left traversal for odd-indexed rows
                for (int j = m - 1; j >= 0; j--) {
                    System.out.print(matrix[i][j] + " ");
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        zigZagTraversal(matrix); // Output: 1 2 3 6 5 4 7 8 9 
    }
}
