package aldat.maze;

public class MazeAsciiFactory {
	
	private enum Characters {
		FULL_LINE,
		EMPTY_LINE,
		END_LINE,
		
		FULL_WALL,
		EMPTY_WALL,
		END_WALL,
		
		TMP
	}
	
	public void printMaze(Maze maze) {
        int cols = maze.getMaxCols(); // 4
        int rows = maze.getMaxRows(); // 5
		
        for (int i = 0; i < cols; ++i) {
        	if (maze.hasWall(0, i, Direction.NORTH)) {
        		System.out.print(returnCharacterByType(Characters.FULL_LINE));
        	}
        }
        System.out.print(returnCharacterByType(Characters.END_LINE));
        System.out.println();
        
        for (int i = 0; i < rows; ++i) {
        	
            for (int j = 0; j < cols; ++j) {
            	if (j == 0)
            		if (maze.hasWall(i, j, Direction.WEST)) {
            			System.out.print(returnCharacterByType(Characters.FULL_WALL));
            		}

                if (maze.hasWall(i, j, Direction.EAST)) {
            		System.out.print(returnCharacterByType(Characters.FULL_WALL));
                }
                else
                	System.out.print(returnCharacterByType(Characters.EMPTY_WALL));
            }            
            System.out.println();
            
            for (int j = 0; j < cols; ++j) {            	
                if (maze.hasWall(i, j, Direction.SOUTH))
                	System.out.print(returnCharacterByType(Characters.FULL_LINE));
                else
                	System.out.print(returnCharacterByType(Characters.EMPTY_LINE));

            }
            System.out.print(returnCharacterByType(Characters.END_LINE));
            System.out.println();
            
        }
	}

    private String returnCharacterByType(Characters ch) {
        switch (ch) {
            case FULL_LINE:
                return "+--";
            case EMPTY_LINE:
                return "+  ";
            case END_LINE:
                return "+";
                
            case FULL_WALL:
                return "|  ";
            case EMPTY_WALL:
            	return "   ";
            case END_WALL:
                return "|";
            case TMP:
            	return ":(";
            default:
            	return ":(";
        }
    }
}
