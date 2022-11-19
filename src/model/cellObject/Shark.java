package model.cellObject;
import java.util.HashMap;

import model.Grid;

public class Shark extends SeaCreature{
	protected int starvingTime;
	protected int starvingTimeElapsed;
	
	public Shark() {
		super();
		breedingTime = 20;
		starvingTime = 5;
		starvingTimeElapsed = 0;
	}

	
	private boolean isStarved() {
		return starvingTimeElapsed >= starvingTime;
	}
	
	public CellObject[][] sharkUpdate(int curRow, int curCol, CellObject[][] tempGrid, HashMap<Integer, CellObject> neighbors2) {
		//specific update method
		// if neighbors == fish
				// move to and delete fish from space
				
		//else neighbors == water, move there
		
		return tempGrid;
	}
	

}
