package model.cellObject;

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
}

