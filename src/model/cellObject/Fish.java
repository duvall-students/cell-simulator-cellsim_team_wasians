package model.cellObject;

import java.util.HashMap;

import model.Grid;

public class Fish extends SeaCreature{
	
	public Fish(Grid grid, int row, int col) {
		super(grid, row, col);
		breedingTime = 1;
		
	}
	

	
public CellObject[][] fishUpdate(int curRow, int curCol, CellObject[][] tempGrid, HashMap<Integer, CellObject> neighbors2) {
		// specific update method
		// loop through neighbors
		// if neighbors == water
		
		// re-populate
		for (Entry<Integer, CellObject> entry : neighbors2.entrySet()) {
		    Integer key = entry.getKey(); 
		    Object value = entry.getValue();
		    if (value.equals(instanceof Water)) {
		    	value = Fish;
		    	break;
		    }
		}
		return tempGrid;
	}
}
