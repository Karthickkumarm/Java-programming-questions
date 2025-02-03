import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GridSearch {

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right

    public static void main(String[] args) {
        char[][] grid = {
            {'A', 'Z', 'T', 'E', 'C'},
            {'O', 'Z', 'O', 'N', 'E'},
            {'H', 'O', 'M', 'E', 'S'},
            {'S', 'O', 'L', 'I', 'D'}
        };

        String target = "ZOHO";
        List<int[]> shortestPath = findShortestPath(grid, target);

        if (shortestPath.isEmpty()) {
            System.out.println("The word \"" + target + "\" was not found in the grid.");
        } else {
            System.out.println("The shortest path for \"" + target + "\" is:");
            for (int[] pos : shortestPath) {
                System.out.println("(" + pos[0] + ", " + pos[1] + ")");
            }
        }
    }

    public static List<int[]> findShortestPath(char[][] grid, String target) {
        int rows = grid.length;
        int cols = grid[0].length;

        List<int[]> startPoints = findStartPoints(grid, target.charAt(0));
        List<int[]> shortestPath = null;

        for (int[] start : startPoints) {
            List<int[]> path = bfs(grid, target, start);
            if (path != null) {
                if (shortestPath == null || path.size() < shortestPath.size()) {
                    shortestPath = path;
                }
            }
        }

        return shortestPath == null ? new ArrayList<>() : shortestPath;
    }

    private static List<int[]> bfs(char[][] grid, String target, int[] start) {
        int rows = grid.length;
        int cols = grid[0].length;
        int targetLen = target.length();

        Queue<List<int[]>> queue = new LinkedList<>();
        queue.offer(new ArrayList<>(Arrays.asList(start)));

        while (!queue.isEmpty()) {
            List<int[]> currentPath = queue.poll();
            int[] lastPos = currentPath.get(currentPath.size() - 1);
            int currentRow = lastPos[0];
            int currentCol = lastPos[1];

            if (currentPath.size() == targetLen) {
                return currentPath; // Found the target string
            }

            char nextChar = target.charAt(currentPath.size());

            for (int[] dir : DIRECTIONS) {
                int nextRow = currentRow + dir[0];
                int nextCol = currentCol + dir[1];

                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols && grid[nextRow][nextCol] == nextChar) {
                    List<int[]> newPath = new ArrayList<>(currentPath);
                    newPath.add(new int[]{nextRow, nextCol});
                    queue.offer(newPath);
                }
            }
        }

        return null; // No path found for this start point
    }

    private static List<int[]> findStartPoints(char[][] grid, char startChar) {
        List<int[]> startPoints = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == startChar) {
                    startPoints.add(new int[]{i, j});
                }
            }
        }
        return startPoints;
    }
}