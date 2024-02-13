import java.util.HashSet;
import java.util.PriorityQueue;

public class ForwardAStar {

    //State will likely just be a MazeBox datatype that also holds stuff like search(state) variable and heuristic data
    //We can also just change MazeGenerator to generate a bunch of states and not mazeboxes

    private MazeBox[][] grid;
    private PriorityQueue<MazeBox> openSet;
    private boolean[][] closedSet;
    private int width, height;
    private MazeBox start, goal;

    static int counter = 0;  // Counter to track A* searches
    public ForwardAStar(MazeBox[][] grid, MazeBox start, MazeBox goal) {
        this.grid = grid;
        this.start = start;
        this.goal = goal;
        this.width = grid[0].length;
        this.height = grid.length;
        this.openSet = new PriorityQueue<>();
        this.closedSet = new boolean[height][width];
    }

    //this was written entirely with chat gpt idk if it works ill check tomorow
    
    public void computePath() {
        openSet.add(start);

        while (!openSet.isEmpty()) {
            MazeBox current = openSet.poll();

            if (current.equals(goal)) {
                reconstructPath(goal);
                return;
            }

            closedSet[current.y][current.x] = true;

            for (int[] direction : new int[][]{{0,1}, {1,0}, {0,-1}, {-1,0}}) {
                int newX = current.x + direction[0];
                int newY = current.y + direction[1];

                if (newX >= 0 && newX < width && newY >= 0 && newY < height && !grid[newY][newX].isObstacle && !closedSet[newY][newX]) {
                    MazeBox neighbor = grid[newY][newX];

                    double tentativeGScore = current.g + 1; // assuming cost between adjacent cells is 1

                    if (!openSet.contains(neighbor) || tentativeGScore < neighbor.g) {
                        neighbor.g = tentativeGScore;
                        neighbor.h = calculateHeuristic(neighbor, goal);
                        neighbor.f = neighbor.g + neighbor.h;
                        neighbor.previous = current;

                        if (!openSet.contains(neighbor)) {
                            openSet.add(neighbor);
                        }
                    }
                }
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

        while (!startState.equals(goalState)){
            counter++;
            startState.g = 0; //sets g value as 0
            startState.search = counter; //sets search value in start as counter

            goalState.g = Integer.MAX_VALUE;
            goalState.search = counter;

            openList.clear();
            closedSet.clear();
            openList.add(startState);

            computePath(); // computes A star for this start state

            //We should have path now so move along path until we hit an obstacle
            move();
        }
    }
}
