package model.cellObject;

import java.util.HashMap;

import model.Grid;
/**
 * 
 * @author Blake Byerly
 * 
 */

public class SeaCreature extends CellObject{
	
	public SeaCreature() {
		super();

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
		if (tempGrid[curRow][curCol]  instanceof Shark) {
			Shark shark = new Shark();
			shark.sharkUpdate(curRow, curCol, tempGrid, neighbors2);
		}
		// if fish
		if (tempGrid[curRow][curCol]instanceof Fish) {
			Fish fish = new Fish();
			fish.fishUpdate(curRow, curCol, tempGrid, neighbors2);
		}
		// if another creature can go below here
		
		//return the updated grid
		return tempGrid;
	}
}

