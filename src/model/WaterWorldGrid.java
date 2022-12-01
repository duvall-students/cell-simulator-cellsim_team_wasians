package model;

import java.util.ArrayList;
import java.util.HashMap;

import model.cellObject.*;

/**
 * 
 * @author chrislee
 * 
 */

public class WaterWorldGrid extends Grid {
	
	public WaterWorldGrid(int rows, int columns, double fishDensity, double sharkDensity) {
		super(rows, columns);
		cellTypes = new CellObject[] {new Fish(), new Shark(), new Water()};
		// Brandon
		// pass the value of fish and shark density
		probabilities = new double[] {fishDensity, sharkDensity, .2};
	}
	
	
	public void assignCell(int row, int column) {
		if (isEdge(row, column)) {
			grid[row][column]  = new Edge();
		}
		else{
			grid[row][column] = getRandomCell();
		}
	}
	
	public void changeDensity(double fishDensity, double sharkDensity, double waterDensity) {
		probabilities = new double[] {fishDensity, sharkDensity, waterDensity};
	}
}
