package aldat.maze;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
	
	public void printMazeIntoFile(String filename, Maze maze) {
		BufferedWriter writer = null;
		try {
		    writer = new BufferedWriter( new FileWriter(filename + ".txt"));
		    writer.write(printMaze(maze));

		}
		catch (IOException e) {
		}
		finally	{
		    try {
		        if (writer != null)
		        	writer.close( );
		    }
		    catch (IOException e) {
		    }
		}
	}
	
	public String printMaze(Maze maze) {
        int cols = maze.getMaxCols(); // 4
        int rows = maze.getMaxRows(); // 5
        String output = "";
		
        for (int i = 0; i < cols; ++i) {
        	if (maze.hasWall(0, i, Direction.NORTH)) {
        		output += returnCharacterByType(Characters.FULL_LINE);
        	}
        }
        output += returnCharacterByType(Characters.END_LINE);
        output += "\n";
        
        for (int i = 0; i < rows; ++i) {
        	
            for (int j = 0; j < cols; ++j) {
            	if (j == 0)
            		if (maze.hasWall(i, j, Direction.WEST)) {
            			output += returnCharacterByType(Characters.FULL_WALL);
            		}

                if (maze.hasWall(i, j, Direction.EAST)) {
                	output += returnCharacterByType(Characters.FULL_WALL);
                }
                else
                	output += returnCharacterByType(Characters.EMPTY_WALL);
            }
            output += "\n";
            
            for (int j = 0; j < cols; ++j) {            	
                if (maze.hasWall(i, j, Direction.SOUTH))
                	output += returnCharacterByType(Characters.FULL_LINE);
                else
                	output += returnCharacterByType(Characters.EMPTY_LINE);

            }
            output += returnCharacterByType(Characters.END_LINE);
            output += "\n";
            
        }
        return output;
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
