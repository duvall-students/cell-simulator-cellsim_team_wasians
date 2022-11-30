package view;

import javafx.application.Application;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

import controller.*;

/**
 * @Author: Hunter Copeland
 * The frontend, a visual representation of the model and GUI.
 */

public class GUIController extends Application{

	//Stuff for the GUI
	private final int MILLISECOND_DELAY = 15;
	private final int EXTRA_VERTICAL = 230;
	private final int EXTRA_HORIZONTAL = 200;
	private final int BLOCK_SIZE = 60;
	
	private final String TITLE = "Cellular Automata";
	private Scene myScene;
	private boolean paused = false;
	private Button pauseButton;
	
	// cell controller object
	private CellController cellController;
	
	public GUIController() {
		cellController = new WaterWorldCellController();
	}
	
	@Override
	public void start(Stage stage){
		//Init GUI
		myScene = setupScene(stage);
		stage.setScene(myScene);
		stage.setTitle(TITLE);
		stage.show();
		
		//calls the step method each frame
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(MILLISECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}
	
	private Scene setupScene(Stage stage) {
		Group cellLayout = new Group();
		VBox inputs = setupUserInterface(stage);
		HBox controls = setupControlButtons();
		
		VBox root = new VBox();
		root.setAlignment(Pos.TOP_CENTER);
		root.setSpacing(20);
		root.setPadding(new Insets(10, 10, 10, 10));
		cellLayout = cellController.setupGrid(BLOCK_SIZE);
		root.getChildren().addAll(inputs, cellLayout, controls);
		
		Scene scene = new Scene(root, cellController.getCols()*BLOCK_SIZE + EXTRA_HORIZONTAL, 
				cellController.getRows()*BLOCK_SIZE + EXTRA_VERTICAL, Color.FLORALWHITE );
		return scene;
	}
	
	private HBox setupControlButtons() {
		HBox controls = new HBox();
		controls.setAlignment(Pos.BASELINE_CENTER);
		controls.setSpacing(10);
		
		Button newCellLayoutButton = new Button("New Layout");
		newCellLayoutButton.setOnAction(value -> {
			cellController.newGrid();
		});
		controls.getChildren().add(newCellLayoutButton);
		
		pauseButton = new Button("Pause");
		pauseButton.setOnAction(value -> {
			pressPause();
		});
		controls.getChildren().add(pauseButton);
		
		Button stepButton = new Button("Step");
		stepButton.setOnAction(value -> {
			doOneStep(MILLISECOND_DELAY);
		});
		controls.getChildren().add(stepButton);
		
		return controls;
	}
	
	// < NEW ADDED >
	// Brandon
	// set up the text-field for the user inputs interface
	private VBox setupUserInterface(Stage stage) {
		VBox inputUnit = new VBox();
		inputUnit.setAlignment(Pos.TOP_CENTER);
		inputUnit.setSpacing(10);
		
		HBox gridInputs = new HBox();
		gridInputs.setAlignment(Pos.BASELINE_CENTER);
		gridInputs.setSpacing(10);
		
		HBox seaCreatureActionInputs = new HBox();
		seaCreatureActionInputs.setAlignment(Pos.BASELINE_CENTER);
		seaCreatureActionInputs.setSpacing(10);
		
		HBox seaCreatureDensityInputs = new HBox();
		seaCreatureDensityInputs.setAlignment(Pos.BASELINE_CENTER);
		seaCreatureDensityInputs.setSpacing(10);
		
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.BASELINE_CENTER);
		
		ArrayList<String> userInterfaceInfo = cellController.getUserInterfaceInfo();

		Text gridRowsText = new Text("Grid Rows:");
		TextField gridRowsUserInput = new TextField (userInterfaceInfo.get(0));
		gridRowsUserInput.setPrefColumnCount(3);
		gridInputs.getChildren().addAll(gridRowsText, gridRowsUserInput);
		
		Text gridColsText = new Text("Grid Columns:");
		TextField gridColsUserInput = new TextField (userInterfaceInfo.get(1));
		gridColsUserInput.setPrefColumnCount(3);
		gridInputs.getChildren().addAll(gridColsText, gridColsUserInput);
		
		Text fishBreedTimeText = new Text("Fish Breed Time:");
		TextField fishBreedTimeUserInput = new TextField (userInterfaceInfo.get(2));
		fishBreedTimeUserInput.setPrefColumnCount(3);
		seaCreatureActionInputs.getChildren().addAll(fishBreedTimeText, fishBreedTimeUserInput);
		
		Text sharkBreedTimeText = new Text("Shark Breed Time:");
		TextField sharkBreedTimeUserInput = new TextField (userInterfaceInfo.get(3));
		sharkBreedTimeUserInput.setPrefColumnCount(3);
		seaCreatureActionInputs.getChildren().addAll(sharkBreedTimeText, sharkBreedTimeUserInput);
		
		Text sharkStarveTimeText = new Text("Shark Starve Time:");
		TextField sharkStarveTimeUserInput = new TextField (userInterfaceInfo.get(4));
		sharkStarveTimeUserInput.setPrefColumnCount(3);
		seaCreatureActionInputs.getChildren().addAll(sharkStarveTimeText, sharkStarveTimeUserInput);
		
		Text fishDensityText = new Text("Fish Density:");
		TextField fishDensityUserInput = new TextField (userInterfaceInfo.get(5));
		fishDensityUserInput.setPrefColumnCount(3);
		seaCreatureDensityInputs.getChildren().addAll(fishDensityText, fishDensityUserInput);
		
		Text sharkDensityText = new Text("Shark Density:");
		TextField sharkDensityUserInput = new TextField (userInterfaceInfo.get(6));
		sharkDensityUserInput.setPrefColumnCount(3);
		seaCreatureDensityInputs.getChildren().addAll(sharkDensityText, sharkDensityUserInput);
		
		Button submitButton = new Button("Start");
		submitButton.setOnAction(value -> {
			readUserInterfaceInfo(gridRowsUserInput.getText(), gridColsUserInput.getText(), 
					fishBreedTimeUserInput.getText(), sharkBreedTimeUserInput.getText(), sharkStarveTimeUserInput.getText(), 
					fishDensityUserInput.getText(), sharkDensityUserInput.getText());
			stage.close();
			start(new Stage());
		});
		buttons.getChildren().add(submitButton);
		
		inputUnit.getChildren().addAll(gridInputs, seaCreatureActionInputs, seaCreatureDensityInputs, buttons);

		return inputUnit;
	}
	
	//if not paused, run a step
	private void step(double elapsedTime) {
		if(!paused) {
			this.doOneStep(elapsedTime);
		}
	}
	
	//everything that should be done in one step or "generation"
	private void doOneStep(double elapsedTime) {
//		System.out.println("Step");
	}
	
	private void pressPause() {
		this.paused = !this.paused;
		if(this.paused) {
			pauseButton.setText("Resume");
		} else {
			pauseButton.setText("Pause");
		}
	}
	
	// read and update the series properties of the water world cell simulator by the user interface
	private void readUserInterfaceInfo(String rowsInfo, String colsInfo, String fishBreedTimeInfo, 
			String sharkBreedTimeInfo, String sharkStarveTimeInfo, String fishDensityInfo, String sharkDensityInfo) 
	{
		ArrayList<String> userInputsInfo = new ArrayList<String>();
		userInputsInfo.add(rowsInfo);
		userInputsInfo.add(colsInfo);
		userInputsInfo.add(fishBreedTimeInfo);
		userInputsInfo.add(sharkBreedTimeInfo);
		userInputsInfo.add(sharkStarveTimeInfo);
		userInputsInfo.add(fishDensityInfo);
		userInputsInfo.add(sharkDensityInfo);
		cellController.updateUserInterfaceInfo(userInputsInfo);
	}
	
	public void launchGUI() {
		launch();
	}
	
}
