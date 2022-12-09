package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import javafx.scene.effect.Light.Point;
import model.cellObject.*;
/**
 * 
 * 
 * @author chrislee
 * As of Nov 18, Other parts of the project is incomplete. 
 * Therefore, I have a J Unit Test to make sure my classes and methods are working correctly-- but the program itself may not be runnable. 
 */


public abstract class Grid {
	private static final int LEFT = 0;
	private static final int TOP = 1;
	private static final int RIGHT = 2;
	private static final int BTM = 3;
	
	protected CellObject[][] grid;
	protected CellObject[][] tempGrid;
	protected CellObject[] cellTypes; 
	protected double[] probabilities;
	protected Random spinRandom;
	
	
	public Grid(int rows, int columns) {
		grid = new CellObject[rows][columns];
		tempGrid = new CellObject[rows][columns];
		spinRandom = new Random();
	}
	
	public void populateGrid() {
		for (int row = 0; row < grid.length; row++) {
			for(int column = 0; column < grid[0].length; column++) {
				assignCell(row, column);
			}
		}
	}
	
	public CellObject getCell(int row, int col) {
		return grid[row][col];
	}
	
	abstract void assignCell(int row, int column);
	
	
	public CellObject getRandomCell(){
		double spinNumber = spinRandom.nextDouble();
		return numToObject(spinNumber);
	}			
	
	/*
	 * returns a random CellObject. 
	 */
	public CellObject numToObject(double spinNumber){	
		int index = 0;
		double low = 0;
		boolean done = false;
		CellObject result = new CellObject();
		while(!done){
			double high = probabilities[index] + low;
			if(spinNumber>= low && spinNumber< high){
				result = cellTypes[index];
				done = true;
			}
			else{
				low = high;
				index++;
			}
		}
		return result;
	}
	
	public void updateGrid() {
		for (int row = 0; row <= grid.length; row++) {
			for(int col = 0; col <= grid[0].length; col++) {
				CellObject cellObject = grid[row][col];
				
				if(!isEdge(row, col)) {
					tempGrid = updateCell(row, col, cellObject);
				}
			}
		}
		materializeTempGrid();
	}
	//Blake
	//returns coordinates of a chosen neighbor CellObject (row, column)
	public static ArrayList<Integer> neighborCoordinate(int curRow, int curCol, int coordinate) {
		ArrayList<Integer> arr = new ArrayList<>();
		// if neighbor is left
		if (coordinate == LEFT) {
			arr.add(curRow);
			arr.add(curCol - 1);
		}
		// if neighbor is top
		else if (coordinate == TOP) {
			arr.add(curRow - 1);
			arr.add(curCol);
		}
		// if neighbor is right
		else if (coordinate == RIGHT) {
			arr.add(curRow);
			arr.add(curCol + 1);
		}		
		// if neighbor is bottom
		else if (coordinate == BTM) {
			arr.add(curRow + 1);
			arr.add(curCol);
		}		
		return arr;
	}
	protected boolean isEdge(int row, int column) {
		return row == 0 || column == 0 || row == grid.length-1 || column == grid[0].length-1;
	}
	
	
	// return a partially updated temporary grid
	public CellObject[][] updateCell(int curRow, int curCol, CellObject currentCell){
		HashMap<Integer, CellObject> neighbors  = getNeighbors(curRow, curCol);
		return currentCell.update(curRow, curCol, tempGrid, neighbors);
	}
	
	public void materializeTempGrid() {
		grid = tempGrid;
		tempGrid = new CellObject[grid.length][grid[0].length];
	}
	
	//This is only called when the Cell is NOT an edge. 
	public HashMap<Integer, CellObject> getNeighbors(int row, int col) {
		HashMap<Integer, CellObject> neighbors = new HashMap<Integer, CellObject>();
		neighbors.put(LEFT, grid[row][col-1]);
		neighbors.put(TOP, grid[row-1][col]);
		neighbors.put(RIGHT, grid[row][col+1]);
		neighbors.put(BTM, grid[row+1][col]);
		return neighbors;
	}
	
	

}
