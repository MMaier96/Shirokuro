package application;

import application.gui.objects.DotList;

public class Main  {

	
	public static void main(String... args) {
		DotList.createDotList();
		Application app = new Application();
		app.setArguments(args);
		app.startApplication();
	}
}
