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

                closedSet.add(current); //idk if necessary
                int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
                for (int[] direction : directions) {
                    int newX = current.x + direction[0];
                    int newY = current.y + direction[1];

                    //Doesnt go off the grid
                    if (newX >= 0 && newX < width && newY >= 0 && newY < height ) {
                        MazeBox neighbor = grid[newY][newX];
                        if (neighbor.search < counter && !obstacleSet.contains(neighbor)) {
                            neighbor.g = Integer.MAX_VALUE;
                            neighbor.search = counter;
                        }
                        if(neighbor.g > current.g + 1 && !obstacleSet.contains(neighbor)){
                            neighbor.g = current.g + 1;
                            neighbor.previous = current;
                            //treemap will fail
                          //  treeMap.put(current, neighbor);
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




    private void reconstructPath(MazeBox end) {
        // Start from the goal and go backwards through the 'previous' pointers to reconstruct the path
        // This method should update the grid or a path list to reflect the path found from start to goal
    }

    private double calculateHeuristic(MazeBox a, MazeBox b) {
        // Use Manhattan distance as heuristic
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
    public void run(){
        //while loop
        counter++;
        start.g = 0;
        start.search = counter;
        goal.g = Integer.MAX_VALUE;
        goal.search = counter;
        openSet.clear();
        treeMap.clear();
        closedSet.clear();
        //checkObstacles(start);
        start.h = calculateHeuristic(start, goal);
        start.f = start.h + start.g;
        openSet.add(start);

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
        computePath();

        //NOT FINISHED
        List<MazeBox> path = new ArrayList<>();
        MazeBox current = goal;
        while (current != null) {
            path.add(current);
            current = current.previous;
        }
        Collections.reverse(path); // Optional, if you want the path from start to goal
        System.out.println(path);
    }
    public static void main(String[] args){


        //add initial set of obstacles to obstacle set


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
