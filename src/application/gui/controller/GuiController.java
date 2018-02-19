package application.gui.controller;

import application.objects.Dot;
import application.objects.DotColor;
import application.objects.DotList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class GuiController {

	
	@FXML
	GridPane gridPane;

	@FXML
	Button test;

	@FXML
	public void initialize() {
		gridPane.setGridLinesVisible(false);
		
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
