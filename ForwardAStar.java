import java.util.HashSet;
import java.util.PriorityQueue;

public class ForwardAStar {

    //State will likely just be a MazeBox datatype that also holds stuff like search(state) variable and heuristic data
    //We can also just change MazeGenerator to generate a bunch of states and not mazeboxes

    PriorityQueue<State> openList = new PriorityQueue<>();  // Priority queue for open list
    HashSet<State> closedSet = new HashSet<>();  // Closed set to keep track of expanded states
    int counter = 0;  // Counter to track A* searches

    public void computePath(){

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
