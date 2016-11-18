package aldat.maze;

enum Characters {
	FULL_LINE,
	EMPTY_LINE,
	END_LINE,
	
	FULL_WALL,
	EMPTY_WALL,
	END_WALL,
	
	TMP
}

public class MazeAsciiFactory {

    public String returnCharacterByType(Characters ch) {
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
