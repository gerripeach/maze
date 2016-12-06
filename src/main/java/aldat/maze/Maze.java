package aldat.maze;

import java.util.ArrayList;

enum Direction
{
    SOUTH,
    EAST,
    NORTH,
    WEST
}

public class Maze
{
    private boolean[][] maze;
    private int maxRows;
    private int maxCols;

    public Maze(boolean[][] arr) {
        this.maze = arr;
        maxRows = (maze.length / 2) + 1;
        maxCols = maze[0].length;
    }

    public int getMaxRows() {
        return maxRows;
    }

    public int getMaxCols() {
        return maxCols;
    }

    public boolean hasWall(int row, int col, Direction dir) {
        if (row > maxRows || col > maxCols) {
            System.out.println("ERROR: wrong input!");
            return false;
        }

        switch (dir) {
            case NORTH:
                return row == 0 ? true : false;
            case EAST:
            	if (col == maxCols)
            		return true;

            	row *= 2;
            	return maze[row][col];
            case SOUTH:
            	if (row == maxRows - 1)
            		return true;
            	
            	row = row == 0 ? 1 : (row * 2) + 1;
            	return maze[row][col];
            case WEST:
                return col == 0 ? true : false;
            default:
                return false;
        }
    }
    
    // wir können es nicht ausgeben und Sören findet es nicht sooooo gut :/
    public int[][] unionFindId(int row, int col){
    	int c=0;
    	//int a = maxCols * maxRows;
    	int [][] id = new int[maxCols][maxRows];
    	for(int i=0; i <=maxRows; i++){
    		for (int k=0; k<= maxCols; i++){
    			id[i][k] = c;
    			c++;
    		}
    	}
    	return id;
   }

}
