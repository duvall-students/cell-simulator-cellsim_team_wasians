package model.cellObject;
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
	
	
}
