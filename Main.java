
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
        int w = 5;
        int h = 5;
        MazeGenerator generator = new MazeGenerator(w, h, false);
        int maze[][] = generator.generate(0);
        w = maze[0].length;
        h = maze.length;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.print(maze[i][j]);
                if (j < w - 1) System.out.print(" ");
            }
            System.out.print("\n");
//3 is obstacle, 0 is good, 1 is start, 2 is end
        }
        System.out.println(generator.getStartPosition().x +" "+generator.getStartPosition().y);
        System.out.println("-------------------------------------");
        ForwardAStar forwardAStar = new ForwardAStar(generator.getMazeBoxes(), generator.getStartPosition(), generator.getEndPosition());
        forwardAStar.run();
//(0: empty, 1: start, 2: end, 3: obstacle)
    }

}