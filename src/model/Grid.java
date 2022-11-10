package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import model.cellObject.*;

public abstract class Grid {
	public static final int LEFT = 0;
	public static final int TOP = 1;
	public static final int RIGHT = 2;
	public static final int BTM = 3;
	
	protected CellObject[][] grid;
	protected CellObject[][] tempGrid;
	protected CellObject[] cellTypes; 
	protected double[] probabilities;
	protected Random spinRandom;
	
	
	public Grid(int rows, int columns) {
		grid = new CellObject[rows][columns];
		spinRandom = new Random();
	}
	
	public void populateGrid() {
		for (int row = 0; row <= grid.length; row++) {
			for(int column = 0; column <= grid[0].length; column++) {
				assignCell(row, column);
			}
		}
	}
	
	abstract void assignCell(int row, int column);
	
	
	public CellObject getRandomCell(){
		double spinNumber = spinRandom.nextDouble();
		return numToObject(spinNumber);
	}			
	
	/*
	 * Turn the random number into one of the spinner words 
	 * based on the given probabilities.
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
			for(int column = 0; column <= grid[0].length; column++) {
				CellObject cellObject = grid[row][column];
				
				if(cellObject instanceof SeaCreature) {
					
					if(willMove(row, column)) {
						//die
						tempGrid[row][column] = new Water();
						
						//moveLeft
						tempGrid[row][column-1] = cellObject;
						
						//moveUp
						tempGrid[row-1][column] = cellObject;
						
						//moveRight
						tempGrid[row][column+1] = cellObject;
						
						//moveDown
						tempGrid[row+1][column] = cellObject;
					}
					else {
						//stay 
						tempGrid[row][column] = cellObject;
					}
				}
			}
		}
	}
	
	protected boolean willMove(int row, int col) {
		return !(grid[row][col] instanceof Shark && containsFish(getNeighbors(row, col)));
	}
	
	
	
	//this needs improvement
	public CellObject[] getNeighbors(int row, int col) {
		CellObject[] neighbors = new CellObject[4];
		//Left neighbor
		if(col>0) {
			neighbors[0] = grid[row-1][col];
		}

			neighbors[0] = null;
		//Top neighbor
		if(row>0) {
			neighbors[1] = grid[row][col-1];
		}
		else {
			neighbors[1] = null;
		}
		//right neighbor
		if(col<grid[0].length-1) {
			neighbors[2] = grid[row+1][col];
		}
		else {
			neighbors[2] = null;
		}
		//btm neighbor
		if(row<grid.length-1) {
			neighbors[3] = grid[row+1][col];
		}
		else {
			neighbors[3] = null;
		}
		return neighbors;
	}
	
	private boolean containsFish(CellObject[] neighbors) {
		for (CellObject neighbor: neighbors) {
			if (neighbor instanceof Fish) {
				return true;
			}
		}
		return false;
	}
	

	
	
	
	
	

}
