
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
        int w = 6;
        int h = 6;
        int numOfRuns = 1;
        double totalTime = 0; // Accumulate total time here

        for (int run = 0; run < numOfRuns; run++) {
            MazeGenerator generator = new MazeGenerator(w, h, false);
            int[][] maze = generator.generate(0);
            w = maze[0].length;
            h = maze.length;

            // If you don't need to print the maze every time, you can comment out this loop

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("----------------------------------");

            //runs backwards a Star
            BackwardAStar backwardAStar = new BackwardAStar(generator.getMazeBoxes(), generator.getStartPosition(), generator.getEndPosition());
            double time = backwardAStar.run();
//Runs forward A star
//            ForwardAStar forwardAStar = new ForwardAStar(generator.getMazeBoxes(), generator.getStartPosition(), generator.getEndPosition());
//            double time = forwardAStar.run(); // Make sure run returns long indicating the time

            totalTime += time; // Add the time of this run to the total time

            // Optional: Print time for each run
            System.out.println("Run " + (run + 1) + " took: " + (time) + " seconds.");
        }


        double averageTime = totalTime / numOfRuns; // Compute average time
        System.out.println("Average time over "+numOfRuns+" runs: " + (averageTime) + " seconds.");
    }


}