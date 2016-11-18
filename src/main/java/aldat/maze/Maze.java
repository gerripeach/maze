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

    public Maze(boolean[][] maze) {
        this.maze = maze;
        maxRows = (maze.length / 2) + 1;
        maxCols = maze[0].length;

        System.out.println(hasWall(0, 0, Direction.NORTH));
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
                return col == maxCols ? true : false;
            case SOUTH:
                return row == maxRows ? true : false;
            case WEST:
                return col == 0 ? true : false;
            default:
                return false;
        }
    }

    private int getArrayIndexOutOfRow(int row) {
        if (row > maxRows)
            return 0;
        return row * 2;
    }

    private boolean isEvenNumber(int i) {
        return i % 2 == 0 ? true : false;
    }

    // ungerade zahl = odd number
    private boolean isOddNumber(int i) {
        return i % 2 == 1 ? true : false;
    }

}
