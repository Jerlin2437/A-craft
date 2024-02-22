/*
import java.util.*;

public class ForwardAStar {

    //State will likely just be a MazeBox datatype that also holds stuff like search(state) variable and heuristic data
    //We can also just change MazeGenerator to generate a bunch of states and not mazeboxes

    private MazeBox[][] grid;
    // seenGrid array -> set obstacleSet
    private HashSet<MazeBox> obstacleSet;
    private HashSet<MazeBox> closedSet;
    private PriorityQueue<MazeBox> openSet;
    private HashMap<MazeBox, MazeBox> treeMap;
    public double nodesExplored = 0;
    private int width, height;
    private MazeBox start, goal;

    static int counter = 0;  // Counter to track A* searches
    public ForwardAStar(MazeBox[][] grid, MazeBox start, MazeBox goal) {
        this.grid = grid;
        this.obstacleSet = new HashSet<>();
        this.start = start;
        this.goal = goal;
        this.width = grid[0].length;
        this.height = grid.length;
        this.openSet = new PriorityQueue<>();
        this.treeMap = new HashMap<>();
        this.closedSet = new HashSet<>();
    }

    //This will be the actual implementation of computePath following the pseudoCode
    public void computePath(){
        boolean pathExists = false;



        while (!openSet.isEmpty() && !pathExists) {

            MazeBox current = openSet.peek(); // Look at the node in OPEN with the smallest f-value without removing it

            //goal.g is infinity until we've found a path
            if (goal.g > current.f) { // f(s) is always an undershoot because heuristic is consistent

                current = openSet.poll();

         //       closedSet.add(current); //idk if necessary
                int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
                for (int[] direction : directions) {
                    int newX = current.x + direction[0];
                    int newY = current.y + direction[1];

                    //Doesnt go off the grid
                    if (newX >= 0 && newX < width && newY >= 0 && newY < height ) {
                        MazeBox neighbor = grid[newY][newX];
                        if (neighbor.search < counter && !obstacleSet.contains(neighbor)) {
                            // if (neighbor.search < counter && !closedSet.contains(neighbor) && !obstacleSet.contains(neighbor))
                            neighbor.g = Integer.MAX_VALUE;
                            neighbor.search = counter;
                        }
                        if(neighbor.g > current.g + 1 && !obstacleSet.contains(neighbor)){
                         //if(neighbor.g > current.g + 1 && !closedSet.contains(neighbor) && !obstacleSet.contains(neighbor))
                            nodesExplored++;
                            neighbor.g = current.g + 1;
                            //treemap could fail
                            treeMap.put(neighbor, current);
                            if (openSet.contains(neighbor)){
                                openSet.remove(neighbor);
                            }
                            neighbor.f = neighbor.g + calculateHeuristic(neighbor, this.goal);
                            openSet.add(neighbor);
                        }
                    }
                    }
            } else {
                pathExists = true; // This condition suggests we've found the path or there's no better path
            }
        }
    }






    private double calculateHeuristic(MazeBox a, MazeBox b) {
        // Use Manhattan distance as heuristic
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
    //returns number of seconds it took to run
    public double run(){
        long startTime = System.nanoTime(); // Start timing

        while (!start.equals(goal)){
            counter++;
            start.g = 0;
            start.search = counter;
            goal.g = Integer.MAX_VALUE;
            goal.search = counter;
            openSet.clear();
            treeMap.clear();
        //only for adaptive A*    closedSet.clear();
            checkObstacles(start);
            start.h = calculateHeuristic(start, goal);
            start.f = start.h + start.g;
            openSet.add(start);
            computePath();
            if (openSet.isEmpty()){
                System.out.println("We cannot reach the target.");
                return (System.nanoTime() - startTime); // Return the duration in nanoseconds
            }
            printMaze();

            List<MazeBox> path = reconstructPath();
            if (path.isEmpty()) {
                System.out.println("We cannot reach the target.");
                return (System.nanoTime() - startTime); // Return the duration in nanoseconds
            } else {
                start = moveAlongPath(path, goal);
            }
        }
        System.out.println("We reached the target.");
        long durationNano = (System.nanoTime() - startTime); // Duration in nanoseconds
        double durationSeconds = durationNano / 1_000_000_000.0; // Convert to seconds
        System.out.println("Duration: " + durationSeconds + " seconds.");
        return durationSeconds; // or return durationSeconds if you change the return type to double
       // return (System.nanoTime() - startTime); // Return the duration in nanoseconds
    }


    private void checkObstacles(MazeBox start) {
       // System.out.println("Checking Obstacles!");
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] direction : directions) {
            int newX = start.x + direction[0];
            int newY = start.y + direction[1];
            if (newX >= 0 && newX < width && newY >= 0 && newY < height ){
                MazeBox neighbor = grid[newY][newX];
                if(neighbor.isObstacle){
                    obstacleSet.add(neighbor);
                }
            }
        }
    }

    //chatgpted- may have issues
    public MazeBox moveAlongPath(List<MazeBox> path, MazeBox goal) {
        for (int i = 0; i < path.size() - 1; i++) { // Stop one step before the end to check for obstacles

            MazeBox current = path.get(i);
            checkObstacles(current);
            MazeBox next = path.get(i + 1);

            if (obstacleSet.contains(next)) {
                System.out.println("Stopped before hitting an obstacle at: (" + next.x + ", " + next.y + ")");
                return current;
            }

            // Move to next; In your actual implementation, this could involve updating the MazeBox state or UI
            System.out.println("Moved to: (" + current.x + ", " + current.y + ")");
            if (current.equals(goal))
                return current;
        }
        return goal;
    }

    //chatgpted- may have issues
    public List<MazeBox> reconstructPath() {
        List<MazeBox> path = new ArrayList<>();
        MazeBox current = goal;
        while (current != null && !current.equals(start)) {
            path.add(0, current); // Add to the beginning of the list
            current = treeMap.get(current);
        }
        path.add(0, start); // Add start at the beginning
        System.out.println("Path: " + path);
        return path; // This path is from start to goal
    }

    public void printMaze() {
        // First, reconstruct the path from the goal to the start
        HashSet<MazeBox> pathSet = new HashSet<>();
        MazeBox current = goal;
        while (current != null) {
            pathSet.add(current);
            current = treeMap.get(current);
        }

        // Print the maze
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                MazeBox box = grid[y][x];
                if (box.equals(start)) {
                    System.out.print("1 "); // Start
                } else if (box.equals(goal)) {
                    System.out.print("2 "); // Goal
                } else if (obstacleSet.contains(box)) {
                    System.out.print("3 "); // Obstacle
                } else if (pathSet.contains(box)) {
                    System.out.print("4 "); // Path
                } else {
                    System.out.print("0 "); // Valid path
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args){


    }
}
*/
