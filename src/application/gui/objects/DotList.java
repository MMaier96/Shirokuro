package application.gui.objects;

import static application.gui.objects.DotColor.*;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class DotList {

	public static ArrayList<Dot> dots = new ArrayList<>();

	// colors are initialized as [y][x]
	public static Color[][] colors = { { WHITE, GREY, TRANSP, GREY, TRANSP, WHITE, WHITE, TRANSP, GREY, GREY },
			{ GREY, TRANSP, TRANSP, TRANSP, TRANSP, TRANSP, GREY, WHITE, WHITE, TRANSP },
			{ WHITE, WHITE, TRANSP, TRANSP, GREY, TRANSP, WHITE, TRANSP, GREY, WHITE },
			{ TRANSP, WHITE, TRANSP, GREY, TRANSP, TRANSP, WHITE, TRANSP, TRANSP, GREY },
			{ GREY, TRANSP, TRANSP, TRANSP, TRANSP, WHITE, WHITE, GREY, TRANSP, TRANSP },
			{ TRANSP, TRANSP, WHITE, TRANSP, TRANSP, GREY, GREY, TRANSP, TRANSP, WHITE },
			{ WHITE, GREY, TRANSP, WHITE, WHITE, TRANSP, TRANSP, GREY, TRANSP, WHITE },
			{ GREY, WHITE, TRANSP, WHITE, TRANSP, WHITE, TRANSP, GREY, WHITE, GREY },
			{ GREY, GREY, GREY, GREY, TRANSP, TRANSP, TRANSP, TRANSP, WHITE, TRANSP },
			{ WHITE, TRANSP, TRANSP, GREY, WHITE, GREY, GREY, WHITE, GREY, TRANSP }, };

	/**
	 * initially creating Dot objects by using indices and colors from colors[]
	 */
	public static void createDotList() {
		for (int y = 0; y < colors.length; y++) {
			for (int x = 0; x < colors.length; x++) {
				if (colors[y][x] != TRANSP) {
					dots.add(new Dot(x, y, colors[y][x]));
				}
			}
		}
	}

	/**
	 * returns a cloned DotList to ensure not operating on the original
	 * 
	 * @return
	 */
	public static ArrayList<Dot> getClonedDotlist() {
		ArrayList<Dot> clone = new ArrayList<>();

		for (Dot _dot : dots) {
			clone.add(Dot.clone(_dot));
		}

		return clone;
	}

	/**
	 * returns a cloned dot by dimension
	 * 
	 * @param x
	 * @param y
	 * @return Dot
	 */
	public static Dot getDotByDemension(int x, int y) {
		for (Dot _dot : dots) {
			if (_dot.getX() == x) {
				if (_dot.getY() == y) {
					return Dot.clone(_dot);
				}
			}
		}
		return null;
	}

	/**
	 * returns the color of a dot at [x,y]
	 * 
	 * @param x
	 * @param y
	 * @return Color
	 */
	public static Color getDotColorByDemension(int x, int y) {
		return colors[y][x];
	}
}
