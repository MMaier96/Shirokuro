package application.objects;

import javafx.scene.paint.Color;
import static application.objects.DotColor.instance;
public class DotList {

	
	public static Color[][] colors = {
			{instance.white, instance.grey, instance.transparent, instance.grey, instance.transparent, instance.white, instance.white, instance.transparent, instance.grey, instance.grey},//
			{instance.grey, instance.transparent, instance.transparent, instance.transparent, instance.transparent, instance.transparent, instance.grey, instance.white, instance.white, instance.transparent},//
			{instance.white, instance.white, instance.transparent, instance.transparent, instance.grey, instance.transparent, instance.white, instance.transparent, instance.grey, instance.white},//
			{instance.transparent, instance.white, instance.transparent, instance.grey, instance.transparent, instance.transparent, instance.white, instance.transparent, instance.transparent, instance.grey},//
			{instance.grey, instance.transparent, instance.transparent, instance.transparent, instance.transparent, instance.white, instance.white, instance.grey, instance.transparent, instance.transparent},//
			{instance.transparent, instance.transparent, instance.white, instance.transparent, instance.transparent, instance.grey, instance.grey, instance.transparent, instance.transparent, instance.white},//
			{instance.white, instance.grey, instance.transparent, instance.white, instance.white, instance.transparent, instance.transparent, instance.grey, instance.transparent, instance.white},//
			{instance.grey, instance.white, instance.transparent, instance.white, instance.transparent, instance.white, instance.transparent, instance.grey, instance.white, instance.grey},//
			{instance.grey, instance.grey, instance.grey, instance.grey, instance.transparent, instance.transparent, instance.transparent, instance.transparent, instance.white, instance.transparent},//
			{instance.white, instance.transparent, instance.transparent, instance.grey, instance.white, instance.grey, instance.grey, instance.white, instance.grey, instance.transparent},			
			}; //y , x
	
	public static Dot[][] dots = new Dot[10][10];

	public static void createDotList() {
		for (int y = 0; y < colors.length; y++) {
			for (int x = 0;x < colors.length; x++) {
				dots[y][x] = new Dot(x, y, colors[y][x]);
			}	
		}
	}
	
	public static Dot getDotbyDimension(int x, int y) {
		return dots[y][x];
	}
}
