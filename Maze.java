import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Maze {
    private int height;
    private int width;
    private int[][] maze;

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        this.maze = new int[height][width];
    }

    public int[][] generateMaze() {        // Initialize
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                maze[i][j] = 1;

        Random rand = new Random();
        // r for row, c for column
        // Generate random r
        int r = rand.nextInt(height);
        while (r % 2 == 0) {
            r = rand.nextInt(height);
        }
        // Generate random c
        int c = rand.nextInt(width);
        while (c % 2 == 0) {
            c = rand.nextInt(width);
        }
        // Starting cell
        maze[r][c] = 0;

        // Allocate the maze with recursive method
        recursion(r, c);

        return maze;
    }

    private void recursion(int r, int c) {
        // 4 random directions
        Integer[] randDirs = generateRandomDirections();
        // Examine each direction
        for (int i = 0; i < randDirs.length; i++) {

            switch (randDirs[i]) {
                case 1: // Up
                    if (r - 2 <= 0)
                        continue;
                    if (maze[r - 2][c] != 0) {
                        maze[r - 2][c] = 0;
                        maze[r - 1][c] = 0;
                        recursion(r - 2, c);
                    }
                    break;
                case 2: // Right
                    if (c + 2 >= width - 1)
                        continue;
                    if (maze[r][c + 2] != 0) {
                        maze[r][c + 2] = 0;
                        maze[r][c + 1] = 0;
                        recursion(r, c + 2);
                    }
                    break;
                case 3: // Down
                    if (r + 2 >= height - 1)
                        continue;
                    if (maze[r + 2][c] != 0) {
                        maze[r + 2][c] = 0;
                        maze[r + 1][c] = 0;
                        recursion(r + 2, c);
                    }
                    break;
                case 4: // Left
                    if (c - 2 <= 0)
                        continue;
                    if (maze[r][c - 2] != 0) {
                        maze[r][c - 2] = 0;
                        maze[r][c - 1] = 0;
                        recursion(r, c - 2);
                    }
                    break;
            }
        }
    }

    public void printMaze() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Generate an array with random directions 1-4
     *
     * @return Array containing 4 directions in random order
     */
    private Integer[] generateRandomDirections() {
        ArrayList<Integer> randoms = new ArrayList<Integer>();
        for (int i = 0; i < 4; i++)
            randoms.add(i + 1);
        Collections.shuffle(randoms);

        return randoms.toArray(new Integer[4]);
    }

    public static void main(String[] args) {
        Maze mazeGenerator = new Maze(10, 10);
        int[][] generatedMaze = mazeGenerator.generateMaze();
        mazeGenerator.printMaze();
    }
}
