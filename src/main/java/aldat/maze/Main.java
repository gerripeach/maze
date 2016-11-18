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

        MazeAsciiFactory maf = new MazeAsciiFactory();


        Maze maze = new Maze(hase);

        int cols = maze.getMaxCols(); // 4
        int rows = maze.getMaxRows(); // 5
        
        //System.out.print(rows);

        
        for (int i = 0; i < cols; ++i) {
        	if (maze.hasWall(0, i, Direction.NORTH)) {
        		System.out.print(maf.returnCharacterByType(Characters.FULL_LINE));
        	}
        }
        System.out.print(maf.returnCharacterByType(Characters.END_LINE));
        System.out.println();
        
        for (int i = 0; i < rows; ++i) {
        	
            for (int j = 0; j < cols; ++j) {
            	if (j == 0)
            		if (maze.hasWall(i, j, Direction.WEST)) {
            			System.out.print(maf.returnCharacterByType(Characters.FULL_WALL));
            		}

                if (maze.hasWall(i, j, Direction.EAST)) {
            		System.out.print(maf.returnCharacterByType(Characters.FULL_WALL));
                }
                else
                	System.out.print(maf.returnCharacterByType(Characters.EMPTY_WALL));
            }            
            System.out.println();
            
            for (int j = 0; j < cols; ++j) {            	
                if (maze.hasWall(i, j, Direction.SOUTH))
                	System.out.print(maf.returnCharacterByType(Characters.FULL_LINE));
                else
                	System.out.print(maf.returnCharacterByType(Characters.EMPTY_LINE));

            }
            System.out.print(maf.returnCharacterByType(Characters.END_LINE));
            System.out.println();
            
        }

    }
}
