import java.io.*;
import java.util.*;

import javax.lang.model.util.ElementScanner14;

public class MazeSearcher {

  // private static final int[][] STEPS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0
  // } }; // (dx, dy)
  // // DOWN RIGHT UP LEFT \
  private Coord Spath;// To save the route

  public MazeSearcher(Maze maze) {
    ArrayList<Coord> path = new ArrayList<>();
    Coord entry = maze.getStart();
    if (explore(maze, entry.getX(), entry.getY(), path)) {
      maze.printPath(path);
    } else
      System.out.println("No path found");
  } // end of MazeSearcher()

  private boolean explore(Maze maze, int x, int y, ArrayList<Coord> path) {
    if (maze.isExit(x, y)) // Is it the end?
      return true;
    if (maze.isStart(x, y)) // Is that the starting point? If yes, set that already walked and walk down
    {
      maze.setVisited(x, y);
      return explore(maze, x + 1, y, path);
    }
    if (maze.isWall(x, y))// Is it a wall? If yes, it's insurmountable.
      return false;
    if (maze.wasVisited(x, y))// Is it a path that I have walked? If so, it cannot be passed.
      return false;
    if (maze.isValidLoc(x, y))// Beyond the wall? If not, can walk on? If too much can not walk

    {
      maze.setVisited(x, y);
      if (explore(maze, x, y + 1, path) || explore(maze, x + 1, y, path) || explore(maze, x, y - 1, path) // Trap 4
                                                                                                          // routes
                                                                                                          // where you
                                                                                                          // can go If
                                                                                                          // no path is
                                                                                                          // possible,
                                                                                                          // -1 will be
                                                                                                          // returned.
          || explore(maze, x - 1, y, path)) {
        Set_path(maze, x, y, path); // Function to set the path to the end
        return true;
      } else
        return false;
    } else
      return false;
  } // end of explore()

  private void Set_path(Maze maze, int x, int y, ArrayList<Coord> path) {// Function to set the path to the end
    Spath = new Coord(x, y);
    path.add(Spath);
  }
  // Students: you can add other functions if you wish

  // --------------------------------------------

  public static void main(String[] args) throws Exception {
    if (args.length != 1)
      System.out.println("Usage: java MazeSearcher <maze textfile>");
    else {
      Maze maze = new Maze(new File(args[0]));
      MazeSearcher dfs = new MazeSearcher(maze);
    }
  }

} // end of MazeSearcher class
