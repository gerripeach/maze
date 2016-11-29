package aldat.maze;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MazePrintFactory {
	
	private enum Characters {
		FULL_LINE,
		EMPTY_LINE,
		END_LINE,
		
		FULL_WALL,
		EMPTY_WALL,
		END_WALL,
		
		INDEX_LINE
	}

	public enum FileType {
		TYPE_TXT,
		TYPE_PBM
	}
	
	private Settings settings;
	
	public MazePrintFactory(Settings settings) {
		this.settings = settings;
	}
	
	public void printMazeIntoFile(String filename, Maze maze, FileType ft) {
		BufferedWriter writer = null;
		try {
			String mazeString = "";
			String path = "";
			switch (ft) {
				case TYPE_TXT:
					mazeString = mazeToAsciiArt(maze);
					path = settings.getCollectionTxtPath() + filename + ".txt";
					break;
				case TYPE_PBM:
					mazeString = mazeToPBM(maze);
					path = settings.getCollectionPbmPath() + filename + ".pbm";
					break;
				default:
					break;
			}
		    writer = new BufferedWriter(new FileWriter(path));
		    writer.write(mazeString);

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
	
	public String mazeToAsciiArt(Maze maze) {
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

	public String mazeToPBM(Maze maze) {
		int maxCols = maze.getMaxCols();
		int maxRows = maze.getMaxRows();
		String output = "P1\n" + maxCols * 3 + " " + (maxRows * 3 + 1) + "\n";

		for (int i = 0; i < maxCols; ++i) {
			if (maze.hasWall(0, i, Direction.NORTH)) {
				output += "1 1 1 "; // one space to much
			}
		}
		output += "\n";

		for (int i = 0; i < maxRows; ++i) {
			String colString = "";
			for (int j = 0; j < maxCols; ++j) {
				if (j == 0)
					if (maze.hasWall(i, j, Direction.WEST)) {
						colString += "1 0 0 ";
						continue;
					}

				if (maze.hasWall(i, j, Direction.EAST)) {
					if (j == (maxCols - 1))
						colString += "0 0 1\n";
					else
						colString += "0 0 1 ";
				}
				else
					colString += "0 0 0 ";
			}
			output += colString + colString;

			for (int j = 0; j < maxCols; ++j) {
				if (maze.hasWall(i, j, Direction.SOUTH)) {
					if (j == (maxCols - 1))
						output += "1 1 1\n";
					else
						output += "1 1 1 ";
				} else if (j == (maxCols - 1))
					output += "0 0 1\n";
				else if (j == 0)
					output += "1 0 0 ";
				else
					output += "0 0 1 ";

			}
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
