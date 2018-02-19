package application.gui.controller;

import application.config.AppConfig;
import application.objects.Dot;
import application.objects.DotConnector;
import application.objects.DotList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class GuiController {

	
	@FXML
	GridPane gridPane;
	@FXML
	AnchorPane connectorPane;

	@FXML
	Button test;

	@FXML
	public void initialize() {
		gridPane.setGridLinesVisible(false);
		connectorPane.setPrefWidth(AppConfig.instance.gridWidth+12);
		fillGridPane();
		
		connectorPane.getChildren().add(new DotConnector(0,0,0,2));
		connectorPane.getChildren().add(new DotConnector(1,3,3,3));

	}

	private void fillGridPane() {
		for (int y = 0; y < gridPane.getColumnConstraints().size(); y++) {
			for (int x = 0; x < gridPane.getColumnConstraints().size(); x++) {
				Dot dot = DotList.getDotbyDimension(x, y);
				gridPane.add(dot, x, y);
				GridPane.setHalignment(dot, HPos.CENTER);
			}
		}
	}

	@FXML
	public void btnSolveClicked() {
		System.out.println("clicked");
	}
}
