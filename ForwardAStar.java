import java.util.HashSet;
import java.util.PriorityQueue;

public class ForwardAStar {

    //State will likely just be a MazeBox datatype that also holds stuff like search(state) variable and heuristic data
    //We can also just change MazeGenerator to generate a bunch of states and not mazeboxes

    private MazeBox[][] grid;
    private MazeBox[][] seenGrid;
    private PriorityQueue<MazeBox> openSet;
    private HashSet<MazeBox> closedSet;
    private int width, height;
    private MazeBox start, goal;

    static int counter = 0;  // Counter to track A* searches
    public ForwardAStar(MazeBox[][] grid, MazeBox start, MazeBox goal) {
        this.grid = grid;
        this.seenGrid = new MazeBox[grid.length][grid[0].length];
        this.start = start;
        this.goal = goal;
        this.width = grid[0].length;
        this.height = grid.length;
        this.openSet = new PriorityQueue<>();
        this.closedSet = new HashSet<>();
    }

    //This will be the actual implementation of computePath following the pseudoCode
    public void computePath(){
        boolean pathExists = false;



        while (!openSet.isEmpty() && !pathExists) {

            MazeBox current = openSet.peek(); // Look at the node in OPEN with the smallest f-value without removing it

            //goal.g is infinity until we've found a path
            if (goal.g > current.f) { // current.f is the minimum of g(s0) + h(s0) in OPEN


                current = openSet.poll(); // removes the node with smallest f value;
                closedSet.add(current);
                int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
                for (int[] direction : directions) {
                    int newX = current.x + direction[0];
                    int newY = current.y + direction[1];


                    if (newX >= 0 && newX < width && newY >= 0 && newY < height) {
                        MazeBox neighbor = grid[newY][newX]; // Assume grid is your MazeBox[][]
                        if (neighbor.search < counter) {
                            neighbor.g = Integer.MAX_VALUE; //IN PROGRESS
                            //HERE WE WANT TO ADD OBSTACLES TO THE CLOSED LIST
                            //A STAR DOES NOT RUN STATE IF IT IS IN CLOSED LIST
                            //ONLY ADD INITIAL PERIPHERAL FOV STATES TO THE CLOSED LIST

                            // Skip if neighbor is an obstacle or already in CLOSED
                            if (!closedSet.contains(neighbor)) {
                                double tentativeG = current.g + 1; // Assuming cost to move to a neighbor is 1

                            }
                        }
                    }
                    }
            } else {
                pathExists = true; // This condition suggests we've found the path or there's no better path
            }
        }
    }




    private void reconstructPath(MazeBox end) {
        // Start from the goal and go backwards through the 'previous' pointers to reconstruct the path
        // This method should update the grid or a path list to reflect the path found from start to goal
    }

    private double calculateHeuristic(MazeBox a, MazeBox b) {
        // Use Manhattan distance as heuristic
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
    public static void main(String[] args){





        // State.search should be defaulted to 0

//        while (!startState.equals(goalState)){
//            counter++;
//            startState.g = 0; //sets g value as 0
//            startState.search = counter; //sets search value in start as counter
//
//            goalState.g = Integer.MAX_VALUE;
//            goalState.search = counter;
//
//            openList.clear();
//            closedSet.clear();
//            openList.add(startState);
//
//            computePath(); // computes A star for this start state
//
//            //We should have path now so move along path until we hit an obstacle
//            move();
//        }
    }
}
