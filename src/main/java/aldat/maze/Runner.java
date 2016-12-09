package aldat.maze;

import java.util.LinkedList;

public class Runner {

	private volatile Runner r;
	private Settings settings;
	private MazePrintFactory maf;

	public volatile Maze maze = null;
	private LinkedList<Thread> allThreads = new LinkedList<Thread>();

	private long start;
	private long end;
	
	public volatile boolean isCreating = false;
	
	private int count = 0;

	public Runner() {
		r = this;
		settings = new Settings();
		maf = new MazePrintFactory(settings);

		startCreatingMaze();
	}

	public void startCreatingMaze() {
		start = System.currentTimeMillis();

		/*
		for (int i = 0; i < 1; ++i) {
			Thread t = new Thread(new MazeFactory(r, settings, "Thread " + i,
					10, 10, 0.5f));
			t.start();
			allThreads.add(t);
		}
		*/
		new MazeFactory(r, settings, "Thread ",
				10, 10, 0.5f).run();
	}

	public void finishCreatingMaze(String name) {		
		System.err.println("ATTENTION: " + name + " Was successful!");
		
		if (isCreating)
			return;
		isCreating = true;
		
		for (Thread t : allThreads)
			t.interrupt();

		maf.printMazeIntoFile("filename ", maze,
				MazePrintFactory.FileType.TYPE_PBM);

		end = System.currentTimeMillis();
		System.out.println("Needed " + (((float) (end - start)) / 1000)
				+ " seconds to generate the maze.");
	}

	public void printCount() {
		System.out.println(++count);
	}
}
