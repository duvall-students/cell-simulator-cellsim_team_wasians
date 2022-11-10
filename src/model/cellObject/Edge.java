package model.cellObject;

import model.Grid;

public class Edge extends CellObject{
	
	public Edge(Grid grid, int row, int col) {
		super(grid, row, col);
	}

	
	public void step() {
		getNeighbors();
	}
	
}
