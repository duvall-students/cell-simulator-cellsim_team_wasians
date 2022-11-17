package model.cellObject;

import java.util.HashMap;

import model.Grid;

public class SeaCreature extends CellObject{
	
	public SeaCreature(Grid grid, int row, int col) {
		super(grid, row, col);

	}

	protected int breedingTime;
	protected int breedingTimeElapsed = 0;
	
	public void step() {
		breedingTimeElapsed++;
	};
	
	public boolean isBreedable() {
		return breedingTimeElapsed >= breedingTime;
	}
	
	public void breed() {
		
	}
	
	public CellObject[][] creatureUpdate(int curRow, int curCol, CellObject[][] tempGrid, HashMap<Integer, CellObject> neighbors2) {
		// if shark
		if (tempGrid[curRow][curCol].getClass().isInstance(Shark)) {
			
		}
		// if fish
		if (tempGrid[curRow][curCol].getClass().isInstance(Fish)) {
			
		}
		// if another creature can go below here
		
		//return the updated grid
		return tempGrid;
	}
}

