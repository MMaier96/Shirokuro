package application.gui.objects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import static application.gui.objects.DotColor.*;

public class Dot extends Circle {

	public static Dot clone(Dot _dot) {
		return new Dot(_dot.getX(), _dot.getY(), _dot.getColor());
	}

	private int x, y;

	private Color color;

	public Dot(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.setRadius(10);
		this.setFill(color);
		this.setRadius(15);
		if (color != TRANSP) {
			this.setStroke(Color.BLACK);
			this.setStrokeWidth(0.5);
		}
	}

	public Color getColor() {
		return color;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Dot [x=" + x + ", y=" + y + ", color=" + color + "]";
	}

}
