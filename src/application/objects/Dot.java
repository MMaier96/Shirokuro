package application.objects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Dot extends Circle{

	private int x,y;
	private Color color;
	
	public Dot(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.setRadius(10);
		this.setFill(color);
		if (color != DotColor.instance.transparent) {
			this.setStroke(Color.BLACK);
			this.setStrokeWidth(0.5);
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
