package src;

public class MazeBox implements Comparable<MazeBox> {
    public boolean isObstacle;
    public boolean hasHValue; //for adaptive a star
    public boolean isVisited;
    public MazeBox previous;
    public int x, y; // Coordinates in the maze
    public double g; // Cost from start to this node
    public double h; // Heuristic estimate from this node to goal
    public double f; // Total cost, g + h
    public double search;

    MazeBox() {
        isObstacle = true;
        isVisited = false;
        hasHValue = false;
        previous = null;
        g = Double.POSITIVE_INFINITY; // Initially set to infinity
        h = 0;
        f = Double.POSITIVE_INFINITY; // Initially set to infinity
        search = 0;
    }

    // Update the compareTo method to compare based on f-value
    @Override
    public int compareTo(MazeBox o) {
        int primary = Double.compare(this.f, o.f);
        if (primary == 0) { // if f-values are the same
            //two options, test both
           // return Double.compare(this.g, o.g); // prefer smaller g-values
            return -Double.compare(this.g, o.g); // prefer larger g-values, hence the negation
        }
        return primary;

 //       final double c = 20000; // Constant larger than any possible g-value in the grid.
//
//        // Calculate priority values incorporating both f and g, favoring larger g-values.
//        double thisPriority = c * this.f + this.g;
//       double otherPriority = c * o.f + o.g;
//
//        // Compare the calculated priorities
//        return Double.compare(thisPriority, otherPriority);
    }

    // Method to calculate the Manhattan distance as the heuristic
    public void calculateH(int endX, int endY) {
        this.h = Math.abs(endX - this.x) + Math.abs(endY - this.y);
        this.f = this.g + this.h; // Update f-value whenever h or g changes
    }

    @Override
    public String toString() {
        return "("+x+","+y+")";
    }
}
