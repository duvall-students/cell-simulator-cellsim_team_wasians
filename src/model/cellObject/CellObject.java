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
	

}
