package model.cellObject;

import model.Grid;
public class Edge extends CellObject{
	
	public Edge() {

	}
	// check each of the 3 attaching cells to each of the 4 corner grids
	private int FindAdjacent(CellObject x, CellObject y) {
	       // top left corner
		/*
		   if (Grid[x-1][y-1] == Grid[x][y]) {
	            return 1 + FindAdjacent(x-1,y-1);
	       }
	       if (Grid[x-1][y-1] == Grid[x][y]) {
	            return 1 + FindAdjacent(x-1,y-1);
	       }
	       if (Grid[x-1][y-1] == Grid[x][y]) {
	            return 1 + FindAdjacent(x-1,y-1);
	       }

	       
	      // top right corner
	       if (Grid[x-1][y-1] == Grid[x][y]) {
	            return 1 + FindAdjacent(x-1,y-1);
	       }
	       if (Grid[x-1][y-1] == Grid[x][y]) {
	            return 1 + FindAdjacent(x-1,y-1);
	       }
	       if (Grid[x-1][y-1] == Grid[x][y]) {
	            return 1 + FindAdjacent(x-1,y-1);
	       }

	       
	       // bottom left corner
	       if (Grid[x-1][y-1] == Grid[x][y]) {
	            return 1 + FindAdjacent(x-1,y-1);
	       }
	       if (Grid[x-1][y-1] == Grid[x][y]) {
	            return 1 + FindAdjacent(x-1,y-1);
	       }
	       if (Grid[x-1][y-1] == Grid[x][y]) {
	            return 1 + FindAdjacent(x-1,y-1);
	       }

	       
	       
	       // bottom right corner
	       if (Grid[x-1][y-1] == Grid[x][y]) {
	            return 1 + FindAdjacent(x-1,y-1);
	       }
	       if (Grid[x-1][y-1] == Grid[x][y]) {
	            return 1 + FindAdjacent(x-1,y-1);
	       }	  
	       if (Grid[x-1][y-1] == Grid[x][y]) {
	            return 1 + FindAdjacent(x-1,y-1);
	       }

	       .. Repeat for all 7 other adjacent locations (-1, +0) (+0, -1) (+1, -1) (-1, +1) (+1, +1) (+1, 0) (0, +1)
	       else {
	       */
	    	   return 1;
	       
	      
	}

	
	public void step() {
		getNeighbors();
	}
	
}
