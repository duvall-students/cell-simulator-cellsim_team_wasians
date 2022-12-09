package controller;

import java.util.ArrayList;
import java.util.HashMap;

import java.awt.Point;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import model.WaterWorldGrid;
import model.cellObject.*;

/**
 * @author Xu Yan
 * 
 * WaterWorldCellController.java
 * Implement the particular controller of the Water World cell simulator.
 * 
 */

public class WaterWorldCellController extends CellController {
	// The Rectangle objects that will get updated and drawn.  
	// It is called "micro" grid because there is one entry per square in the grid.
	private Rectangle[][] microGrid;
	
	/*
	 * cell color settings
	 */
	private Color[] color  = new Color[] {
			Color.rgb(0, 0, 0), 	// edge color (Black)
			Color.rgb(0, 0, 255), 	// empty water color (Blue)
			Color.rgb(0, 204, 0), 	// fish color (Green)
			Color.rgb(255, 255, 0), // shark color (Yellow)
	};

	/* 
	 * logic of the program
	 */
	// the cell objects
//	private Edge edge;
//	private Water water;
//	private Fish fish;				
//	private Shark shark;
//	private String identify = "";		// This string tells which cell object is currently chosen.  Anything other than 
	// the implemented identify class names will result in no identify happening.

	// index of the cell objects elements in the array of the user interface input series value
	private final int ROWS_NUM = 0;
	private final int COLS_NUM = 1;
	private final int FISH_BREED_TIME = 2;
	private final int SHARK_BREED_TIME = 3;
	private final int SHARK_STRAVE_TIME = 4;
	private final int FISH_DENSITY = 5;
	private final int SHARK_DENSITY = 6;

	// space variables of the grid
	private final int EDGE_EXTENSION_NUMS = 2;
	private int rows;
	private int cols;
	
	// other variables of the WaterWorldCellController
	private ArrayList<String> waterWorldCellProperties;
	private HashMap<Class<? extends CellObject>, Integer> cellObjectColorIndex;

	public WaterWorldCellController() {
		// initializing logic state
		initializeWaterWorldCellProperties();
		grid = new WaterWorldGrid(getRows(), getCols(), getFishDensity(), getSharkDensity());
	}
	
	// initialize the properties of the water world cell simulator
	private void initializeWaterWorldCellProperties() {
		waterWorldCellProperties = new ArrayList<String>();
		waterWorldCellProperties.add("3");	// rowsNum, 0
		waterWorldCellProperties.add("8");	// colsNum, 1
		waterWorldCellProperties.add("1");	// fishBreedTime, 2
		waterWorldCellProperties.add("20");	// sharkBreedTime, 3
		waterWorldCellProperties.add("5");	// sharkStarveTime, 4
		waterWorldCellProperties.add("0.7");	// fishDensity, 5
		waterWorldCellProperties.add("0.1");	// sharkDensity, 6
		updateRowsAndCols();
	}
	
	// update the properties of the water world cell simulator by user's inputs
	@Override
	public void updateUserInterfaceInfo(ArrayList<String> userInputsInfo) {
		String currentFishDensity = waterWorldCellProperties.get(FISH_DENSITY);
		String currentSharkDensity = waterWorldCellProperties.get(SHARK_DENSITY);
		
		waterWorldCellProperties = new ArrayList<String>();
		waterWorldCellProperties.add(userInputsInfo.get(ROWS_NUM));  // rowsNum, 0
		waterWorldCellProperties.add(userInputsInfo.get(COLS_NUM));  // colsNum, 1
		waterWorldCellProperties.add(userInputsInfo.get(FISH_BREED_TIME));  // fishBreedTime, 2
		waterWorldCellProperties.add(userInputsInfo.get(SHARK_BREED_TIME));  // sharkBreedTime, 3
		waterWorldCellProperties.add(userInputsInfo.get(SHARK_STRAVE_TIME));  // sharkStarveTime, 4
		if (isDensityValid(Double.parseDouble(userInputsInfo.get(FISH_DENSITY)), 
				Double.parseDouble(userInputsInfo.get(SHARK_DENSITY)))) {
			waterWorldCellProperties.add(userInputsInfo.get(FISH_DENSITY));  // fishDensity, 5
			waterWorldCellProperties.add(userInputsInfo.get(SHARK_DENSITY));  // sharkDensity, 6
		}
		else {
			waterWorldCellProperties.add(currentFishDensity);  // fishDensity, 5
			waterWorldCellProperties.add(currentSharkDensity);  // sharkDensity, 6
		}
		updateRowsAndCols();
		
		grid = new WaterWorldGrid(getRows(), getCols(), getFishDensity(), getSharkDensity());
	}
	
	// set the value of the number of rows and columns
	private void updateRowsAndCols() {
		rows = Integer.parseInt(waterWorldCellProperties.get(ROWS_NUM)) + EDGE_EXTENSION_NUMS;
		cols = Integer.parseInt(waterWorldCellProperties.get(COLS_NUM)) + EDGE_EXTENSION_NUMS;
	}
	
	// create the grid
	@Override
	public Group setupGrid(int numBlockSize) {
		Group drawing = new Group();
		microGrid = new Rectangle[rows][cols];
		generateWaterWorldGrid();
		for(int row = 0; row < rows; row++){
			for(int col = 0; col < cols; col++){
				Rectangle rect = new Rectangle(col*numBlockSize, row*numBlockSize, numBlockSize, numBlockSize);
				rect.setFill(color[findRelatedIndexInColorByCellObject(getCellIdentification(row, col))]);
				microGrid[row][col] = rect;
				drawing.getChildren().add(rect);
			}	
		}
		return drawing;
	}
	
	public Point getGridDimensions() {
		return new Point(rows, cols);
	}
	
	/*
	 * create a new grid;
	 * when this happens, the current simulation should also be stopped and disappear
	 */
	@Override
	public void newGrid() {
		generateWaterWorldGrid();
//		identify = "";
		redrawNewGrid();
	}

	/*
	 * resets all the rectangle colors according to the 
	 * current cell state of that rectangle in the new grid.
	 * This method assumes the display grid matches the model grid.
	 */
	public void redrawNewGrid() {
		for(int row = 0; row < microGrid.length; row++){
			for(int col = 0; col < microGrid[row].length; col++){
				microGrid[row][col].setFill(color[findRelatedIndexInColorByCellObject(getCellIdentification(row, col))]);
			}
		}
	}
	
	/*
	 * resets all the rectangle colors according to the current cell state 
	 * of that rectangle in the current grid by the cell update rule.  
	 * This method assumes the display grid matches the model grid.
	 */
	public void redrawUpdatedGrid() {
		for(int row = 0; row < microGrid.length; row++){
			for(int col = 0; col < microGrid[row].length; col++){
				microGrid[row][col].setFill(color[findRelatedIndexInColorByCellObject(getCellIdentification(row, col))]);
			}
		}
	}

	/*
	 * does a step in the update regardless of pause status
	 * 
	 * (!!! This is an incomplete part due to the logic error when calling 
	 * the cell-updated method in another class; needs further investigation.)
	 * 
	 */
	@Override
	public void doOneStep(double elapsedTime) {
		// should call these methods as a combination
//		grid.updateGrid();
//		redrawUpdatedGrid();
		
		// now calling this method to totally create 
		// a new grid without the cell update rule
		newGrid();
	}
	
	/*
	 * does a step in the update regardless of pause status
	 */
//	public void doOneStep(double elapsedTime) {
//		if(identify.equals("Water")) water.step();
//		else if (identify.equals("Shark")) shark.step();
//		else if (identify.equals("Fish")) fish.step();
//		redraw();
//	}
	
	// identify the object type of each cell
//	public void startIdentify(String identifyType) {
//		grid.updateGrid();
//		identify = identifyType;
//
//		edge = new Edge();
//		water = new Water();
//		fish = new Fish();
//		shark = new Shark();
//	}
	
	// find the related color of each cell based on its object type
	private int findRelatedIndexInColorByCellObject(CellObject cellObj) {
		setUpCellObjectTypeToRelatedColorIndexWithMap();
		
		return cellObjectColorIndex.get(cellObj.getClass());
	}
	
	// set up the object type and its related index number as the Map
	private void setUpCellObjectTypeToRelatedColorIndexWithMap() {
		final Class<? extends CellObject> EDGE = new Edge().getClass();
		final Class<? extends CellObject> WATER = new Water().getClass();
		final Class<? extends CellObject> FISH = new Fish().getClass();
		final Class<? extends CellObject> SHARK = new Shark().getClass();
		
		cellObjectColorIndex = new HashMap<Class<? extends CellObject>, Integer>();
		cellObjectColorIndex.put(EDGE, 0);
		cellObjectColorIndex.put(WATER, 1);
		cellObjectColorIndex.put(FISH, 2);
		cellObjectColorIndex.put(SHARK, 3);
	}
	
	// check if the density of the fish, shark and 
	// water (0.2) will add up to 1 or not
	private boolean isDensityValid(double fishDensity, double sharkDensity) {
		double waterDensity = 0.2;

		return (fishDensity + sharkDensity + waterDensity) == 1.0;
	}

	// generate the overall grid
	protected void generateWaterWorldGrid() {
		grid.populateGrid();
	}

	// identify each cell as an object
	private CellObject getCellIdentification(int row, int col) {
		return grid.getCell(row, col);
	}

	@Override
	public ArrayList<String> getUserInterfaceInfo() {
		return waterWorldCellProperties;
	}

	@Override
	public int getRows() {
		return Integer.parseInt(waterWorldCellProperties.get(ROWS_NUM)) + EDGE_EXTENSION_NUMS;
	}

	@Override
	public int getCols() {
		return Integer.parseInt(waterWorldCellProperties.get(COLS_NUM)) + EDGE_EXTENSION_NUMS;
	}

	private double getFishDensity() {
		return Double.parseDouble(waterWorldCellProperties.get(FISH_DENSITY));
	}

	private double getSharkDensity() {
		return Double.parseDouble(waterWorldCellProperties.get(SHARK_DENSITY));
	}
	
}
