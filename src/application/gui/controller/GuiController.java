package application.gui.controller;

import java.util.ArrayList;

import application.Application;
import application.config.AppConfig;
import application.genetic.config.GeneticConfig;
import application.gui.objects.Dot;
import application.gui.objects.DotConnector;
import application.gui.objects.DotList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class GuiController {
	private Application application;

	@FXML
	AnchorPane connectorPane;
	@FXML
	Label fitness;
	@FXML
	Label fitnessRelative;
	@FXML
	GridPane gridPane;
	@FXML
	Label iterations;
	@FXML
	Button solveButton;
	@FXML
	Label status;

	/**
	 * add a new DotConnectior between two points
	 * 
	 * @param x1
	 * @param x2
	 * @param y1
	 * @param y2
	 */
	public void addConnector(int x1, int x2, int y1, int y2) {
		connectorPane.getChildren().add(new DotConnector(x1, y1, x2, y2));
	}

	/**
	 * fxml method handler when solve button clicked
	 */
	@FXML
	public void btnSolveClicked() {
		application.startGeneticAlgorithm();
		solveButton.setDisable(true);
	}

	/**
	 * removes all {@link DotConnector} from the GUI
	 */
	public void clearDotConnectors() {
		connectorPane.getChildren().clear();
	}

	/**
	 * initial fill of the gridpane with all dots from {@link DotList}
	 */
	private void fillGridPane() {
		ArrayList<Dot> dotlist = DotList.getClonedDotlist();
		for (Dot _dot : dotlist) {
			gridPane.add(_dot, _dot.getX(), _dot.getY());
			GridPane.setHalignment(_dot, HPos.CENTER);
		}
	}

	/**
	 * entry point when controller gets initialized
	 */
	@FXML
	public void initialize() {
		gridPane.setGridLinesVisible(false);
		connectorPane.setPrefWidth(AppConfig.instance.gridWidth + 12);
		fillGridPane();
	}

	/**
	 * set the application to call methods from the controller
	 * 
	 * @param application
	 */
	public void setApplication(Application application) {
		this.application = application;

	}

	/**
	 * sets the configuration of the gui (labels)
	 * 
	 * @param iterationCounter
	 * @param fitnessText
	 */
	public void setConfiguration(int iterationCounter, int fitnessText) {
		iterations.setText(iterationCounter + "");
		fitness.setText(fitnessText + "/" + GeneticConfig.MAX_FINTNESS);
		fitnessRelative.setText(Math.round(((double) fitnessText / GeneticConfig.MAX_FINTNESS * 100)) + "%");
	}

	/**
	 * sets the status text of the gui
	 * 
	 * @param statusText
	 */
	public void setStatus(String statusText) {
		status.setText(statusText);
	}

	public void setFinish() {
		status.setText("finished");
		solveButton.setDisable(false);
	}
}
