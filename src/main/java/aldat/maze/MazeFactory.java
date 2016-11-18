package aldat.maze;

import java.util.Random;

public class MazeFactory {

	Maze randomMaze(int rows, int cols, float p) {
		Random r = new Random();		

		rows = (rows * 2) - 1;		
		boolean mazePrototype[][] = new boolean[rows][cols];

		for (int i = 0; i < mazePrototype[0].length; ++i)
			for (int j = 0; j < mazePrototype.length; ++j)
				if (i == mazePrototype[0].length - 1 && isEvenNumber(j))
					mazePrototype[j][i] = true;
				else
					mazePrototype[j][i] = r.nextFloat() < p ? true : false;

		return new Maze(mazePrototype);
	}
	
    private boolean isEvenNumber(int i) {
        return i % 2 == 0 ? true : false;
    }
}
