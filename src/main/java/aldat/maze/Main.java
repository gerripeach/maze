package aldat.maze;

public class Main {

    public static void main( String[] args ) {
        boolean[][] hase = {
                {false, true, true, true},
                {false, true, true, false},
                {false, true, false, true},
                {true, false, true, true},
                {false, false, false, true},
        };

        Maze maze = new Maze(hase);
        MazeAsciiFactory maf = new MazeAsciiFactory();

        System.out.println(maf.printMaze(maze));
        
        MazeFactory mf = new MazeFactory();
        maze = mf.randomMaze(100, 100, .3f);
        
        //System.out.println(maze.getMaxCols());
        //System.out.println(maze.getMaxRows());
       
        
        maf.printMazeIntoFile("filename", maze);
        System.out.println(maf.printMaze(maze));
    }
}
