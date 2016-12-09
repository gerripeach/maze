package aldat.maze;

public class Main {

	public static void main(String[] args) {
		Settings settings = new Settings();
		MazePrintFactory maf = new MazePrintFactory(settings);
		MazeFactory mf = new MazeFactory(settings);
		
		long start = System.currentTimeMillis();
		Maze maze = mf.randomMazeWithPath(1000, 2000, 0.48f);
		long end = System.currentTimeMillis();
		System.err.println("Needed " + (((float) (end - start)) / 1000) + " seconds to generate the maze.");

		maf.printMazeIntoFile("filename ", maze, MazePrintFactory.FileType.TYPE_PBM);
	}
}
