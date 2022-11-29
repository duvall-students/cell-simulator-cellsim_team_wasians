package model.cellObject;


import java.util.HashMap;

import javafx.scene.effect.Light.Point;
import model.Grid;


public class CellObject {
	Grid grid;
	Point location;
	protected HashMap<Direction, CellObject> neighbors;
	
	public CellObject() {

	}
	
	public Direction getSpaceNearby() {
		for(Direction direction: neighbors.keySet()) {
			if(neighbors.get(direction) instanceof Water) {
				return direction;
			}
		}
		//there is no empty "water" cell nearby.
		return null; 
	}

	// using abstraction and polymorphism, create general update that returns the partially updated grid, 
	// only 2-3 coordinates should be changed
	// if neighbors returns null, then it is non-existent
	// 'instance of' will check what type of cellObject
	/*
	if (tempGrid[curRow][curCol].) == Fish) {
		// if neighbors == water
		// re-populate
		// 
		
		else neighbors == water, move there
				
	}
	if (tempGrid[curRow][curCol].equals(getClass().) == shark) {
		// if neighbors == fish
		// move to and delete fish from space
		
		else neighbors == water, move there
		
	}

	//if statements about what the cellbojects are and how they should update

	*/
	public CellObject[][] update(int curRow, int curCol, CellObject[][] tempGrid, HashMap<Integer, CellObject> neighbors2) {
		// updates for specific possible cell objects. 
		if (tempGrid[curRow][curCol] instanceof SeaCreature) {
			SeaCreature seaCreature = new SeaCreature();
			seaCreature.creatureUpdate(curRow, curCol, tempGrid, neighbors2);
			// create a new object to get rid of the static reference
			return tempGrid;
		}
		else {
			return tempGrid;
		}
	}

//	public HashMap<Integer, CellObject> getNeighbors(int curRow, int curCol) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	

}
