package application;

import org.apache.log4j.BasicConfigurator;

import application.pdf.PDFFilesSynchronizer;

public class Main  {

	
	public static void main(String... args) {
		
		BasicConfigurator.configure();

		PDFFilesSynchronizer syncher = new PDFFilesSynchronizer();
		syncher.startSynchronising();
		
		Application app = new Application();
		app.setArguments(args);
		app.startApplication();
	}
}
