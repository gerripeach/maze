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
        System.out.println(maf.mazeToAsciiArt(maze));
        System.out.println(maf.mazeToPBM(maze));


        maf.printMazeIntoFile("test", maze, MazePrintFactory.FileType.TYPE_PBM);

        MazeFactory mf = new MazeFactory();
        maze = mf.randomMaze(1000, 1000, .5f);
        maf.printMazeIntoFile("filename", maze, MazePrintFactory.FileType.TYPE_PBM);
    }
}
