package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GUIController extends Application{

	//Stuff for the GUI
	private final int MILLISECOND_DELAY = 15;
	private final int NUM_ROWS = 20;
	private final int NUM_COLS = 40;
	private final int BLOCK_SIZE = 12;
	
	private final String TITLE = "Cellular Automata";
	private Scene myScene;
	private boolean paused = false;
	private Button pauseButton;
	
	@Override
	public void start(Stage stage){
		//Init GUI
		myScene = setupScene();
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
	
	private Scene setupScene() {
		Group cellLayout = setupCells();
		HBox controls = setupControlButtons();
		
		VBox root = new VBox();
		root.setAlignment(Pos.TOP_CENTER);
		root.setSpacing(10);
		root.setPadding(new Insets(10, 10, 10, 10));
		root.getChildren().add(controls);
		
		Scene scene = new Scene(root, NUM_COLS*BLOCK_SIZE, NUM_ROWS*BLOCK_SIZE, Color.FLORALWHITE );
		return scene;
	}
	
	private Group setupCells() {
		
		return null;
	}
	
	private HBox setupControlButtons() {
		HBox controls = new HBox();
		controls.setAlignment(Pos.BASELINE_CENTER);
		controls.setSpacing(10);
		
		Button newCellLayoutButton = new Button("New Layout");
		newCellLayoutButton.setOnAction(value -> {
			newCellLayoutCreator();
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
	
	//if not paused, run a step
	private void step(double elapsedTime) {
		if(!paused) {
			this.doOneStep(elapsedTime);
		}
	}
	
	//everything that should be done in one step or "generation"
	private void doOneStep(double elapsedTime) {
		System.out.println("Step");
	}
	
	private void pressPause() {
		this.paused = !this.paused;
		if(this.paused) {
			pauseButton.setText("Resume");
		} else {
			pauseButton.setText("Pause");
		}
	}
	
	private void newCellLayoutCreator() {
		
	}

	public void launchGUI() {
		launch();
	}
}
