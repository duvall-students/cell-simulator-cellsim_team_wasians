package model.cellObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import model.Grid;
/**
 * 
 * @author Blake Byerly
 * 
 */

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
		//potential hashmap loop method?
		// if neighbors == fish
				// move to and delete fish from space
		for (Entry<Integer, CellObject> entry : neighbors2.entrySet()) {
			Integer key = entry.getKey();
		    Object value = entry.getValue();
		    if (value instanceof Fish) {
		    	ArrayList<Integer> neighborLocation = new ArrayList<Integer>();
		    	neighborLocation = Grid.neighborCoordinate(curRow,curCol,key);
		    	int one = neighborLocation.get(0);
		    	int two = neighborLocation.get(1);
		    	Shark shark = new Shark();
		    	tempGrid[one][two] = shark;
		    	Water water = new Water();
		    	tempGrid[curRow][curCol] = water;
		    	break;
		    }
		}
		//else neighbors == water, move there
		for (Entry<Integer, CellObject> entry : neighbors2.entrySet()) {
			Integer key = entry.getKey();
		    Object value = entry.getValue();
		    if (value instanceof Water) {
		    	ArrayList<Integer> neighborLocation = new ArrayList<Integer>();
		    	neighborLocation = Grid.neighborCoordinate(curRow,curCol,key);
		    	int one = neighborLocation.get(0);
		    	int two = neighborLocation.get(1);
		    	Shark shark = new Shark();
		    	tempGrid[one][two] = shark;
		    	Water water = new Water();
		    	tempGrid[curRow][curCol] = water;
		    	break;
		    }
		    
		}
		
		return tempGrid;
	}
	

}
