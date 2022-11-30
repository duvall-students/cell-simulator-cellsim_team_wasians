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

	// where to start and stop the search
//	private Point start;
//	private Point destination;

	// space variables of the grid
	private final int EDGE_EXTENSION_NUMS = 2;
	private int rows;
	private int cols;
	
	// other variables of the WaterWorldCellController
	private ArrayList<String> waterWorldCellProperties;
	private HashMap<Class<? extends CellObject>, Integer> cellObjectColorIndex;

	public WaterWorldCellController() {
		// Initializing logic state
//		start = new Point(1, 1);
//		destination = new Point(rows - 2, cols - 2);
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
		waterWorldCellProperties = new ArrayList<String>();
		waterWorldCellProperties.add(userInputsInfo.get(0));  // rowsNum, 0
		waterWorldCellProperties.add(userInputsInfo.get(1));  // colsNum, 1
		waterWorldCellProperties.add(userInputsInfo.get(2));  // fishBreedTime, 2
		waterWorldCellProperties.add(userInputsInfo.get(3));  // sharkBreedTime, 3
		waterWorldCellProperties.add(userInputsInfo.get(4));  // sharkStarveTime, 4
		waterWorldCellProperties.add(userInputsInfo.get(5));  // fishDensity, 5
		waterWorldCellProperties.add(userInputsInfo.get(6));  // sharkDensity, 6
		updateRowsAndCols();
		
		grid = new WaterWorldGrid(getRows(), getCols(), getFishDensity(), getSharkDensity());
	}
	
	// set the value of the number of rows and columns
	private void updateRowsAndCols() {
		rows = Integer.parseInt(waterWorldCellProperties.get(0)) + EDGE_EXTENSION_NUMS;
		cols = Integer.parseInt(waterWorldCellProperties.get(1)) + EDGE_EXTENSION_NUMS;
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
		redraw();
	}

	/*
	 * resets all the rectangle colors according to the 
	 * current state of that rectangle in the maze.  This 
	 * method assumes the display maze matches the model maze
	 */
	public void redraw() {
		for(int row = 0; row< microGrid.length; row++){
			for(int col = 0; col < microGrid[row].length; col++){
				microGrid[row][col].setFill(color[findRelatedIndexInColorByCellObject(getCellIdentification(row, col))]);
			}
		}
	}

	
	/*
	 * Does a step in the search regardless of pause status
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
	
	// generate the grid
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
		return Integer.parseInt(waterWorldCellProperties.get(0)) + EDGE_EXTENSION_NUMS;
	}
	
	@Override
	public int getCols() {
		return Integer.parseInt(waterWorldCellProperties.get(1)) + EDGE_EXTENSION_NUMS;
	}
	
	private double getFishDensity() {
		return Double.parseDouble(waterWorldCellProperties.get(5));
	}
	
	private double getSharkDensity() {
		return Double.parseDouble(waterWorldCellProperties.get(6));
	}
	
}
