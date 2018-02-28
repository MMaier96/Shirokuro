package application;

import application.objects.DotList;

public class Main  {

	
	public static void main(String... args) {
		//TODO test
		DotList.createDotList();
		Application app = new Application();
		app.setArguments(args);
		app.startApplication();
	}
}
