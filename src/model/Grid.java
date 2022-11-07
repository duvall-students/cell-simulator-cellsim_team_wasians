package model;
import java.util.Random;
import model.cellObject.*;

public abstract class Grid {
	protected CellObject[][] grid;
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
	
	
	public CellObject spin(){
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
	
	
	
	
	

}
