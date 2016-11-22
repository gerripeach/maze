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
		
		INDEX_LINE
	}
	
	private Settings settings;
	
	public MazeAsciiFactory(Settings settings) {
		this.settings = settings;
	}
	
	public void printMazeIntoFile(String filename, Maze maze) {
		BufferedWriter writer = null;
		try {
		    writer = new BufferedWriter(new FileWriter(settings.getCollectionTxtPath() + filename + ".txt"));
		    writer.write(printMaze(maze));

		}
		catch (IOException e) {
			System.out.println("ERROR: writing maze into file!");
		}
		finally	{
		    try {
		        if (writer != null)
		        	writer.close( );
		    }
		    catch (IOException e) {
		    	System.out.println("ERROR: closing writer!");
		    }
		}
	}
	
	public String printMaze(Maze maze) {
        int maxCols = maze.getMaxCols();
        int maxRows = maze.getMaxRows();
        String output = "";
		
        for (int i = 0; i < maxCols; ++i) {
        	if (maze.hasWall(0, i, Direction.NORTH)) {
        		output += returnCharacterByType(Characters.FULL_LINE);
        	}
        }
        output += returnCharacterByType(Characters.END_LINE);
        
        for (int i = 0; i < maxRows; ++i) {
        	
            for (int j = 0; j < maxCols; ++j) {
            	if (j == 0)
            		if (maze.hasWall(i, j, Direction.WEST)) {
            			output += returnCharacterByType(Characters.FULL_WALL);
            		}

                if (maze.hasWall(i, j, Direction.EAST)) {
                	if (j == (maxCols - 1))
                		output += returnCharacterByType(Characters.END_WALL, i);
                	else
                		output += returnCharacterByType(Characters.FULL_WALL);
                }
                else
                	output += returnCharacterByType(Characters.EMPTY_WALL);
            }
            
            for (int j = 0; j < maxCols; ++j) {            	
                if (maze.hasWall(i, j, Direction.SOUTH))
                	output += returnCharacterByType(Characters.FULL_LINE);
                else
                	output += returnCharacterByType(Characters.EMPTY_LINE);

            }
            output += returnCharacterByType(Characters.END_LINE);
            
        }
        
        for (int i = 0; i < maxCols; ++i) {  
        	output += returnCharacterByType(Characters.INDEX_LINE, i);
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
                return "+\n";
                
            case FULL_WALL:
                return "|  ";
            case EMPTY_WALL:
            	return "   ";
            default:
            	return ":(";
        }
    }
    
    private String returnCharacterByType(Characters ch, int index) {
        switch (ch) {
	        case END_WALL:
	        	return "| " + index + "\n";
	        case INDEX_LINE:
	        	return index + "  ";
	        default:
	        	return ":(";
        }
    }
    
}
