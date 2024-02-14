public class MazeBox implements Comparable<MazeBox> {
    public boolean isObstacle;
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
        previous = null;
        g = Double.POSITIVE_INFINITY; // Initially set to infinity
        h = 0;
        f = Double.POSITIVE_INFINITY; // Initially set to infinity
        search = 0;
    }

    // Update the compareTo method to compare based on f-value
    @Override
    public int compareTo(MazeBox o) {
        return Double.compare(this.f, o.f);
    }

    // Method to calculate the Manhattan distance as the heuristic
    public void calculateH(int endX, int endY) {
        this.h = Math.abs(endX - this.x) + Math.abs(endY - this.y);
        this.f = this.g + this.h; // Update f-value whenever h or g changes
    }
}
