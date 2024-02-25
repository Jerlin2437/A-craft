package src;


import java.util.Scanner;

/**
 *
 * @author Chris Samarinas
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        //100 w = 101 width because of indexing
        int w = 10;
        int h = 10;
        int numOfRuns = 1;
        double totalTime = 0; // Accumulate total time here

        double totalNodesExplored = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type 1 for Forward A star, 2 for Backward A Star, 3 for Adaptive A Star: ");
        int choice = scanner.nextInt(); // Read the choice from command line

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
            double nodesExplored = 0;
            double time = 0;

            switch (choice) {
                case 1:
                    // Runs forward A star
                    ForwardAStar forwardAStar = new ForwardAStar(generator.getMazeBoxes(), generator.getStartPosition(), generator.getEndPosition());
                    time = forwardAStar.run();
                    nodesExplored = forwardAStar.nodesExplored;
                    break;
                case 2:
                    // Runs backward A Star
                    BackwardAStar backwardAStar = new BackwardAStar(generator.getMazeBoxes(), generator.getStartPosition(), generator.getEndPosition());
                    time = backwardAStar.run();
                    nodesExplored = backwardAStar.nodesExplored;
                    break;
                case 3:
                    // Runs adaptive A star
                    AdaptiveAStar adaptiveAStar = new AdaptiveAStar(generator.getMazeBoxes(), generator.getStartPosition(), generator.getEndPosition());
                    time = adaptiveAStar.run();
                    nodesExplored = adaptiveAStar.nodesExplored;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                    System.exit(1);
            }
            totalTime += time;
            totalNodesExplored += nodesExplored;
            // Optional: Print time for each run
            System.out.println("Run " + (run + 1) + " took: " + (time) + " seconds.");
        }

        double averageExplored = totalNodesExplored / numOfRuns;
        System.out.println("Average nodes explored over "+numOfRuns+" runs: " + (averageExplored) + " explored.");
        double averageTime = totalTime / numOfRuns; // Compute average time
        System.out.println("Average time over "+numOfRuns+" runs: " + (averageTime) + " seconds.");
    }
}