package application.config;

public enum AppConfig {
	instance;
	
	public int pdfResolution = 120; //dpi
	public int windowWidth = 500;
	public int windowHeight = 500;
	public boolean startAsFullScreen = false;
	public String inputFolderName = "input/";
	public String outputFolderName = "output/";
	
}
