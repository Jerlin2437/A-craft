
import java.util.ArrayList;

/**
 *
 * @author Chris Samarinas
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        int w = 101;
        int h = 101;
        double totalTime = 0; // Accumulate total time here

        for (int run = 0; run < 50; run++) {
            MazeGenerator generator = new MazeGenerator(w, h, false);
            int[][] maze = generator.generate(0);
            w = maze[0].length;
            h = maze.length;

            // If you don't need to print the maze every time, you can comment out this loop
        /*
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        */

            ForwardAStar forwardAStar = new ForwardAStar(generator.getMazeBoxes(), generator.getStartPosition(), generator.getEndPosition());
            double time = forwardAStar.run(); // Make sure run returns long indicating the time
            totalTime += time; // Add the time of this run to the total time

            // Optional: Print time for each run
            System.out.println("Run " + (run + 1) + " took: " + (time) + " seconds.");
        }
//Run 1 took 7.03633 secs on average, this prefered larger g values
//Run 2 took 9.384355004 secs, preferred smaller g values

        double averageTime = totalTime / 50.0; // Compute average time
        System.out.println("Average time over 50 runs: " + (averageTime) + " seconds.");
    }


}