public class Main {
    public static final int ROWS_AND_COLS = 101;
    //project requires 101x101 so 101, and 50 grids.
    public static void main(String[] args) {
        MazeGenerator mazeGenerator = new MazeGenerator(ROWS_AND_COLS);
        mazeGenerator.generateMaze();

        System.out.println("RAW MAZE\n" + mazeGenerator.getRawMaze());
        System.out.println("SYMBOLIC MAZE\n" + mazeGenerator.getSymbolicMaze());
    }
}