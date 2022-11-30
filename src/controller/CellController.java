package controller;

import java.util.ArrayList;

import javafx.scene.Group;

import model.Grid;

/**
 * @author Xu Yan
 * 
 * CellController.java
 * Implement the logic and interactions between the GUI and model of the cell simulator.
 * 
 */

public abstract class CellController {
	// the grid to identify
	protected Grid grid;

	public abstract void newGrid();

	public abstract Group setupGrid(int blockSize);

	public abstract void updateUserInterfaceInfo(ArrayList<String> userInputsInfo);

	public abstract ArrayList<String> getUserInterfaceInfo();

	public abstract int getRows();

	public abstract int getCols();

}
