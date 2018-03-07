package application.gui.objects;

import application.config.AppConfig;
import javafx.scene.shape.Line;

public class DotConnector extends Line {
	int offset = AppConfig.instance.gridHeight / 10 + 1;

	public DotConnector(int x1, int y1, int x2, int y2) {
		setStartX(x1 * offset + 25);
		setEndX(x2 * offset + 25);
		setStartY(y1 * offset + 29);
		setEndY(y2 * offset + 29);
		setStrokeWidth(5);
	}
}
