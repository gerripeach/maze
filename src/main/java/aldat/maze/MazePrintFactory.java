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
            writer = null;
			String path = "";
			switch (ft) {
				case TYPE_TXT:
					path = settings.getCollectionTxtPath() + filename + ".txt";
                    writer = new BufferedWriter(new FileWriter(path));
                    mazeToAsciiArt(maze, writer);
					break;
				case TYPE_PBM:
					path = settings.getCollectionPbmPath() + filename + ".pbm";
                    writer = new BufferedWriter(new FileWriter(path));
                    mazeToPBM(maze, writer);
					break;
				default:
					break;
			}
		}
		catch (IOException e) {
			System.err.println("ERROR: writing maze into file!");
		}
		finally	{
		    try {
		        if (writer != null) {
                    printStatus(1, 1);
                    writer.close();
                }
		    }
		    catch (IOException e) {
		    	System.err.println("ERROR: closing writer!");
		    }
		}
	}
	
	private void mazeToAsciiArt(Maze maze, BufferedWriter writer) throws IOException {
        int maxCols = maze.getMaxCols();
        int maxRows = maze.getMaxRows();
		
        for (int i = 0; i < maxCols; ++i) {
        	if (maze.hasWall(0, i, Direction.NORTH)) {
                writer.write(returnCharacterByType(Characters.FULL_LINE));
        	}
        }
        writer.write(returnCharacterByType(Characters.END_LINE));
        
        for (int i = 0; i < maxRows; ++i) {
            printStatus(maxRows, i);
        	
            for (int j = 0; j < maxCols; ++j) {
            	if (j == 0)
            		if (maze.hasWall(i, j, Direction.WEST)) {
                        writer.write(returnCharacterByType(Characters.FULL_WALL));
            		}

                if (maze.hasWall(i, j, Direction.EAST)) {
                	if (j == (maxCols - 1))
                        writer.write(returnCharacterByType(Characters.END_WALL, i));
                	else
                        writer.write(returnCharacterByType(Characters.FULL_WALL));
                }
                else
                    writer.write(returnCharacterByType(Characters.EMPTY_WALL));
            }
            
            for (int j = 0; j < maxCols; ++j) {            	
                if (maze.hasWall(i, j, Direction.SOUTH))
                    writer.write(returnCharacterByType(Characters.FULL_LINE));
                else
                    writer.write(returnCharacterByType(Characters.EMPTY_LINE));

            }
            writer.write(returnCharacterByType(Characters.END_LINE));
            
        }
        
        for (int i = 0; i < maxCols; ++i) {
            writer.write(returnCharacterByType(Characters.INDEX_LINE, i));
        }
	}

	private void mazeToPBM(Maze maze, BufferedWriter writer) throws IOException {
		int maxCols = maze.getMaxCols();
		int maxRows = maze.getMaxRows();
		writer.write("P1\n" + maxCols * 3 + " " + (maxRows * 3 + 1) + "\n");

		for (int i = 0; i < maxCols; ++i) {
			if (maze.hasWall(0, i, Direction.NORTH)) {
                writer.write("1 1 1 "); // one space to much
			}
		}
        writer.write("\n");

		for (int i = 0; i < maxRows; ++i) {
            printStatus(maxRows, i);

            for (int j = 0; j < 2; ++j)
                for (int k = 0; k < maxCols; ++k) {
                    if (k == 0)
                        if (maze.hasWall(i, k, Direction.WEST)) {
                            writer.write("1 0 0 ");
                            continue;
                        }

                    if (maze.hasWall(i, k, Direction.EAST)) {
                        if (k == (maxCols - 1))
                            writer.write("0 0 1\n");
                        else
                            writer.write("0 0 1 ");
                    }
                    else
                        writer.write("0 0 0 ");
                }

			for (int j = 0; j < maxCols; ++j) {
				if (maze.hasWall(i, j, Direction.SOUTH)) {
					if (j == (maxCols - 1))
                        writer.write("1 1 1\n");
					else
                        writer.write("1 1 1 ");
				} else if (j == (maxCols - 1))
                    writer.write("0 0 1\n");
				else if (j == 0)
                    writer.write("1 0 0 ");
				else
                    writer.write("0 0 1 ");

			}
		}
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

    private void printStatus(int max, int now) {
        if (settings.getShowStatus())
            System.out.println("MazePrintFactory: " + (int)(((float)now / (float)max) * 100) + "%");
    }
    
}
