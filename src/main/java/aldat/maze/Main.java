package aldat.maze;

public class Main {
	
    public static void main( String[] args ) {
    	Settings settings = new Settings();
    	MazePrintFactory maf = new MazePrintFactory(settings);
    	
        boolean[][] testMazePrototype = {
                {false, true, true, true},
                {false, true, true, false},
                {false, true, false, true},
                {true, false, true, true},
                {false, false, false, true},
        };
        Maze maze = new Maze(testMazePrototype);


        maf.printMazeIntoFile("test", maze, MazePrintFactory.FileType.TYPE_TXT);


        long start = System.currentTimeMillis();
        MazeFactory mf = new MazeFactory(settings);
        maze = mf.randomMaze(300, 300, .5f);
        maf.printMazeIntoFile("filename", maze, MazePrintFactory.FileType.TYPE_PBM);
        long end = System.currentTimeMillis();
        System.err.println("Needed " + (((float)(end - start)) / 1000) + " seconds to generate the maze.");
        
        
    }
}
