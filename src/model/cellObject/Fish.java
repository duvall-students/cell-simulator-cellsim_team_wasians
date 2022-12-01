package model.cellObject;

import java.util.HashMap;
import java.util.Map.Entry;

import model.Grid;
/**
 * 
 * @author Blake Byerly
 * 
 */

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
		for (Entry<Integer, CellObject> entry : neighbors2.entrySet()) {
		    Integer key = entry.getKey(); 
		    Object value = entry.getValue();
		    if (value instanceof Water) {
		    	Fish fish = new Fish();
		    	value = fish;
		    	break;
		    }
		}
		return tempGrid;
	}
}
