package model;

import java.util.ArrayList;
import java.util.HashMap;

import model.cellObject.*;



public class WaterWorldGrid extends Grid {
	
	
	public WaterWorldGrid(int rows, int columns) {
		super(rows, columns);
		cellTypes = new CellObject[] {new Fish(), new Shark(), new Water()};
		probabilities = new double[] {.7, .1, .2};
	}
	
	
	public void assignCell(int row, int column) {
		if (isEdge(row, column)) {
			grid[row][column]  = new Edge(this);
		}
		else{
			grid[row][column] = getRandomCell();
		}
	}

	
	private boolean isEdge(int row, int column) {
		return row == 0 || column == 0 || row == grid.length-1 || column == grid[0].length-1;
	}
	
	//returns the list of neighbor fishes, if any. 
	public ArrayList<Integer> getFishes(CellObject[] neighbors){
		ArrayList<Integer> neighborFishes = new ArrayList<Integer>();
		for(int i = 0; i <= neighbors.length ; i++) {
			if (neighbors[i] instanceof Fish) {
				neighborFishes.add(i);
			}
		}
		return neighborFishes;
	}
	
//	public HashMap<String, ArrayList<Integer>> getNeighborsInfo(){
//		HashMap<instanceOf, ArrayList<Integer>> neighborList = new HashMap<String, ArrayList<Integer>>();
//		for(int i = 0; i <= neighbors.length ; i++) {
//			
//		}
//	}
	
	
	
	
}
