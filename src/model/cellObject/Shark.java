package model.cellObject;
import java.util.HashMap;

import model.Grid;

public class Shark extends SeaCreature{
	protected int starvingTime;
	protected int starvingTimeElapsed;
	
	public Shark(Grid grid, int row, int col) {
		super(grid, row, col);
		breedingTime = 20;
		starvingTime = 5;
		starvingTimeElapsed = 0;
	}

	
	private boolean isStarved() {
		return starvingTimeElapsed >= starvingTime;
	}
	
public CellObject[][] sharkUpdate(int curRow, int curCol, CellObject[][] tempGrid, HashMap<Integer, CellObject> neighbors2) {
		//specific update method
		//potential hashmap loop method?
		// if neighbors == fish
				// move to and delete fish from space
		for (Entry<Integer, CellObject> entry : neighbors2.entrySet()) {
			Integer key = entry.getKey();
		    Object value = entry.getValue();
		    if (value.equals(instanceof Fish)) {
		    	value = Shark;
		    	tempGrid[curRow][curCol].equals(Water);
		    	break;
		    }
		}
		//else neighbors == water, move there
		for (Entry<Integer, CellObject> entry : neighbors2.entrySet()) {
			Integer key = entry.getKey();
		    Object value = entry.getValue();
		    if (value.equals(instanceof Water)) {
		    	value = Shark;
		    	tempGrid[curRow][curCol].equals(Water);
		    	break;
		    }
		    
		}
		
		return tempGrid;
	}
	

}
