package aldat.maze;

import java.util.Random;

public class MazeFactory implements Runnable {

	private volatile Runner r;
	private Settings settings;
	private String name;

	private int rows;
	private int cols;
	private float p;

	public MazeFactory(Runner r, Settings settings, String name, int rows,
			int cols, float p) {
		this.r = r;
		this.settings = settings;
		this.name = name;
		this.rows = rows;
		this.cols = cols;
		this.p = p;
	}

	public void run() {		
		boolean _finished = false;
		Maze maze = null;
		
		while (!r.isCreating && !_finished) {
			maze = randomMaze();
			r.printCount();

			if (maze.hasPathFromUpperLeftToLowerRight())
				_finished = true;
		}
		
		r.maze = maze;
		r.finishCreatingMaze(name);
	}

	public Maze randomMaze() {
		Random r = new Random();
		long value = 0;
		for (int i = 0; i < name.getBytes().length; i++)
		{
			value = (value << 8) + (name.getBytes()[i] & 0xff);
		}
		value += System.currentTimeMillis();
		r.setSeed(r.nextLong() + value);
		rows = (rows * 2) - 1;
		boolean mazePrototype[][] = new boolean[rows][cols];

		for (int i = 0; i < mazePrototype[0].length; ++i) {
			printStatus(mazePrototype[0].length, i);
			for (int j = 0; j < mazePrototype.length; ++j)
				if (i == mazePrototype[0].length - 1 && isEvenNumber(j))
					mazePrototype[j][i] = true;
				else
					mazePrototype[j][i] = r.nextFloat() < p ? true : false;
		}

		printStatus(1, 1);
		return new Maze(mazePrototype);
	}

	public Maze randomMazeWithPath() {
		boolean _finished = false;
		Maze maze = null;
		while (!_finished) {
			maze = randomMaze();

			if (maze.hasPathFromUpperLeftToLowerRight())
				_finished = true;
		}

		return maze;
	}

	private boolean isEvenNumber(int i) {
		return i % 2 == 0 ? true : false;
	}

	private void printStatus(int max, int now) {
		if (settings.getShowStatus())
			System.out.println("MazeFactory(" + name + "): "
					+ (int) ((((float) now / (float) max)) * 100) + "%");
	}
}
