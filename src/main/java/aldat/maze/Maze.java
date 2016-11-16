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
    public static void main( String[] args )
    {
        boolean[][] hase = {
                { false, true, true, true },
                { false, true, true, false },
                { false, true, false, true },
                { true, false, true, true },
                { false, false, false, true }
        };


        for (int i = 0; i < hase.length; ++i) {
            for (int j = 0; j < hase[0].length; ++j) {
                System.out.print(hase[i][j] + " ");
            }
            System.out.println();
        }

        hasWall(0, 0, Direction.NORTH, hase);
    }

    private static int getMazeRowLenght(boolean[][] arr) {
        return (arr.length / 2) + 1;
    }

    private static int getMazeColLength(boolean[][] arr) {
        return arr[0].length;
    }

    private static boolean hasWall(int row, int col, Direction dir, boolean[][] arr) {

        int mazeRowLength = getMazeRowLenght(arr);
        int mazeColLength = getMazeColLength(arr);

        if (row > mazeRowLength || col > mazeColLength) {
            System.out.println("ERROR: wrong input!");
            return false;
        }
        if (row == 0 && dir == Direction.NORTH) {
            return true;
        }

        if (col == 0 && dir == Direction.WEST) {
            return true;
        }




return true;
    }


}
