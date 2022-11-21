package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import model.cellObject.CellObject;
import model.cellObject.Edge;
import model.cellObject.SeaCreature;

class GridTest {
	

	//Test if the outer edges of the Grid is correctly assigned as "Edge"
	@Test
	void testEdge1() {
		Grid grid = new WaterWorldGrid(5,5);
		grid.populateGrid();
		assertTrue(grid.getCell(0,0) instanceof Edge);
	}
	
	@Test
	void testEdge2() {
		Grid grid = new WaterWorldGrid(5,5);
		grid.populateGrid();
		assertTrue(grid.getCell(4,4) instanceof Edge);
	}
	
	
	@Test
	void testInner() {
		Grid grid = new WaterWorldGrid(5,5);
		grid.populateGrid();
		assertFalse(grid.getCell(2,2) instanceof Edge);
	}

	//Does getNeighbor Method work? 
	// get Neighbor method is only called when the Object on which the method is called is not an Edge. 
	@Test
	void testNeighborMethod() {
		Grid grid = new WaterWorldGrid(5,5);
		grid.populateGrid();
		
		CellObject center = grid.getCell(1, 1);
		CellObject left = grid.getCell(1, 0);
		
		HashMap<Integer, CellObject> neighbors = grid.getNeighbors(1,1);
		System.out.println(neighbors.get(grid.LEFT));
		System.out.println(left);
		assertTrue(neighbors.get(grid.LEFT).equals(grid.getCell(1, 0)));
	}
	



}
