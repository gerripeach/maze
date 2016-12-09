package aldat.maze;

public class Main {

	public static void main(String[] args) {
		Settings settings = new Settings();
		MazePrintFactory maf = new MazePrintFactory(settings);

		boolean[][] testMazePrototype = { { false, true, true, true }, { false, true, true, false },
				{ false, true, false, true }, { true, false, true, true }, { false, false, false, true }, };
		Maze maze = new Maze(testMazePrototype);

		maf.printMazeIntoFile("test", maze, MazePrintFactory.FileType.TYPE_TXT);

		MazeFactory mf = new MazeFactory(settings);
		
		maze = mf.randomMazeWithPath(50, 50, 0.5f);
		
		//System.err.println("Needed " + _count + "try(s) to generate correct Maze");
		maf.printMazeIntoFile("filename ", maze, MazePrintFactory.FileType.TYPE_PBM);
	}
}
