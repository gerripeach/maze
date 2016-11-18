package aldat.maze;

enum Direction
{
    SOUTH,
    EAST,
    NORTH,
    WEST
}

public class Maze
{
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
            System.out.println("ERROR: wrong input!");
            return false;
        }

        switch (dir) {
            case NORTH:
                return row == 0 ? true : false;
            case EAST:
            	if (col == maxCols)
            		return true;

            	int tmpE = row * 2;
            	return maze[tmpE][col];
            case SOUTH:
            	if (row == maxRows - 1)
            		return true;
            	
            	int tmpS = row == 0 ? 1 : (row * 2) + 1;
            	return maze[tmpS][col];
            case WEST:
                return col == 0 ? true : false;
            default:
                return false;
        }
    }
}
