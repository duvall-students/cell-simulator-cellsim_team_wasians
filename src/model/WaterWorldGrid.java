package model;

import model.cellObject.*;



public class WaterWorldGrid extends Grid {
	
	
	public WaterWorldGrid(int rows, int columns) {
		super(rows, columns);
		cellTypes = new CellObject[] {new Fish(), new Shark(), new Water()};
		probabilities = new double[] {.7, .1, .2};
	}
	
	
	public void assignCell(int row, int column) {
		if (isEdge(row, column)) {
			grid[row][column]  = new Edge();
		}
		else{
			grid[row][column] = spin();
		}
	}

	
	private boolean isEdge(int row, int column) {
		return row == 0 || column == 0 || row == grid.length-1 || column == grid[0].length-1;
	}
	
	
	
	
	
	
}
