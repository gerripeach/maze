package aldat.maze;

public class Main {

    public static void main( String[] args ) {
        boolean[][] hase = {
                {false, true, true, true},
                {false, true, true, false},
                {false, true, false, true},
                {true, false, true, true},
                {false, false, false, true},
        };

        Direction[] dir = { Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST };


        for (int i = 0; i < hase.length; ++i) {
            for (int j = 0; j < hase[0].length; ++j) {
                System.out.print(hase[i][j] + " ");
            }
            System.out.println();
        }

        MazeAsciiFactory maf = new MazeAsciiFactory();


        Maze maze = new Maze(hase);

        int cols = maze.getMaxCols();
        int rows = maze.getMaxRows();

        for (int i = 1; i <= rows; ++i) {
            for (int j = 1; j <= cols; ++j) {
                for (int k = 0; k < dir.length; ++k) {
                    if (maze.hasWall(i, j, dir[k]))
                        System.out.print(maf.returnCharacterByDirection(dir[k]));
                    else
                        System.out.print(" ");
                }
            }
            System.out.println();
        }

        for (int i = 1; i <= rows; ++i) {
            for (int j = 1; j <= cols; ++j) {
                System.out.print(i + " " + j + " ");
                for (int k = 0; k < dir.length; ++k) {
                    System.out.print(dir[k] + ":" + maze.hasWall(i, j, dir[k]) + " ");
                }
                System.out.print(":");
            }
            System.out.println();
        }
    }
}
