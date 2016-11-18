package aldat.maze;

public class MazeAsciiFactory {

    public String returnCharacterByDirection(Direction dir) {
        switch (dir) {
            case NORTH:
                return "--";
            case EAST:
                return "|";
            case SOUTH:
                return "__";
            case WEST:
                return "l";
            default:
                return "";
        }
    }

    public String returnDirectionName(Direction dir) {
        switch (dir) {
            case NORTH:
                return "NORTH";
            case EAST:
                return "EAST";
            case SOUTH:
                return "SOUTH";
            case WEST:
                return "WEST";
            default:
                return "";
        }
    }
}
