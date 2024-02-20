
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
        int numOfRuns = 50;
        double totalTime = 0; // Accumulate total time here
        double totalNodesExplored = 0;

        for (int run = 0; run < numOfRuns; run++) {
            MazeGenerator generator = new MazeGenerator(w, h, false);
            int[][] maze = generator.generate(0);
            w = maze[0].length;
            h = maze.length;



        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("----------------------------------");


            double time = 0;
            double nodesExplored = 0;
            //runs backwards a Star
//            BackwardAStar backwardAStar = new BackwardAStar(generator.getMazeBoxes(), generator.getStartPosition(), generator.getEndPosition());
//            time = backwardAStar.run();
//            nodesExplored = backwardAStar.nodesExplored;


            //Runs forward A star
//            ForwardAStar forwardAStar = new ForwardAStar(generator.getMazeBoxes(), generator.getStartPosition(), generator.getEndPosition());
//            time = forwardAStar.run(); // Make sure run returns long indicating the time
//            nodesExplored = forwardAStar.nodesExplored;

            //runs adaptive a
            AdaptiveAStar adaptiveAStar = new AdaptiveAStar(generator.getMazeBoxes(), generator.getStartPosition(), generator.getEndPosition());
            time = adaptiveAStar.run();
            nodesExplored = adaptiveAStar.nodesExplored;

            totalTime += time; // Add the time of this run to the total time
            totalNodesExplored += nodesExplored;
            // Optional: Print time for each run
            System.out.println("Run " + (run + 1) + " took: " + (time) + " seconds.");
            System.out.println("Run " + (run + 1) + " explored: " + (nodesExplored) + " nodes.");
        }


        double averageTime = totalTime / numOfRuns; // Compute average time
        System.out.println("Average time over "+numOfRuns+" runs: " + (averageTime) + " seconds.");
        double averageExplored = totalNodesExplored / numOfRuns;
        System.out.println("Average nodes explored over "+numOfRuns+" runs: " + (averageExplored) + " explored.");
    }


}