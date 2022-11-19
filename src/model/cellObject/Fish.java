package model.cellObject;

import java.util.HashMap;

import model.Grid;

public class Fish extends SeaCreature{
	
	public Fish() {
		super();
		breedingTime = 1;
		
	}
	

	
	public CellObject[][] fishUpdate(int curRow, int curCol, CellObject[][] tempGrid, HashMap<Integer, CellObject> neighbors2) {
		// specific update method
		// loop through neighbors
		// if neighbors == water
		// re-populate
		return tempGrid;
	}
}
