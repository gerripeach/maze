package aldat.maze;

public class Main {
	
    public static void main( String[] args ) {
    	Settings settings = new Settings();
    	MazeAsciiFactory maf = new MazeAsciiFactory(settings);
    	
        boolean[][] testMazePrototype = {
                {false, true, true, true},
                {false, true, true, false},
                {false, true, false, true},
                {true, false, true, true},
                {false, false, false, true},
        };
        Maze maze = new Maze(testMazePrototype);
        System.out.println(maf.printMaze(maze));

        MazeFactory mf = new MazeFactory();
        maze = mf.randomMaze(100, 100, .3f);
        maf.printMazeIntoFile("filename", maze);
    }
}
