package aldat.maze;

import aldat.unionfind.UF;

enum Direction {
	SOUTH, EAST, NORTH, WEST
}

public class Maze {
	private boolean[][] maze;
	private int maxRows;
	private int maxCols;

	public Maze(boolean[][] arr) {
		this.maze = arr;
		maxRows = (maze.length / 2) + 1;
		maxCols = maze[0].length;
	}

	public int getMaxRows() {
		return maxRows;
	}

	public int getMaxCols() {
		return maxCols;
	}

	public boolean hasWall(int row, int col, Direction dir) {
		if (row > maxRows || col > maxCols) {
			System.err.println("ERROR: wrong input!");
			return false;
		}

		switch (dir) {
		case NORTH:
			return row == 0 ? true : false;
		case EAST:
			if (col == maxCols)
				return true;

			row *= 2;
			return maze[row][col];
		case SOUTH:
			if (row == maxRows - 1)
				return true;

			row = row == 0 ? 1 : (row * 2) + 1;
			return maze[row][col];
		case WEST:
			return col == 0 ? true : false;
		default:
			return false;
		}
	}

	public int unionFindId(int row, int col) {
		return (row * maxCols) + col;
	}

	public boolean hasPathFromUpperLeftToLowerRight() {
		UF uf = new UF(maxCols * maxRows);
		for (int i = 0; i < maxRows; i++) {
			for (int j = 0; j < maxCols; j++) {
				if (!hasWall(i, j, Direction.EAST))
					uf.union(unionFindId(i, j), unionFindId(i, j + 1));

				if (!hasWall(i, j, Direction.SOUTH))
					uf.union(unionFindId(i, j), unionFindId(i + 1, j));
			}
		}
		return uf.connected(0, unionFindId(maxRows - 1, maxCols - 1));
	}
}
