package application;

import java.io.IOException;

import application.config.AppConfig;
import application.genetic.GeneticAlgorithm;
import application.gui.controller.GuiController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Application extends javafx.application.Application {

	private String[] arguments;
	private AppConfig appConfig;
	private GeneticAlgorithm geneticAlgorithm;
	private Thread geneticAlgorithmThread;
	private FXMLLoader loader;
	private Stage primaryStage;
	private StackPane root;

	/**
	 * returns the GuiController.class set to the FXMLLoader
	 * 
	 * @return GuiController
	 */
	public GuiController getController() {
		return loader.getController();
	}

	/**
	 * loads the AppConfig for application settings
	 */
	private void loadConfiguration() {
		appConfig = AppConfig.instance;
	}

	/**
	 * set all configurations and show the application
	 */
	private void loadMainWindow() {
		primaryStage.setTitle(appConfig.windowTitle);

		loader = new FXMLLoader();
		try {
			root = loader.load(getClass().getResource("gui/application.fxml").openStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		getController().setApplication(this);
		Scene scene = new Scene(root, appConfig.gridWidth + 300, appConfig.gridWidth);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
		primaryStage.show();
	}

	/**
	 * set parameters from main method to javaFX application
	 * 
	 * @param arguments
	 */
	public void setArguments(String... arguments) {
		this.arguments = arguments;
	}

	/**
	 * sets the primary stage given by entry of application lifecycle
	 * 
	 * @param primaryStage
	 */
	private void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	/**
	 * gets called after starApplication(). Entry point of lifecycle
	 */
	@Override
	public void start(Stage primaryStage) {
		loadConfiguration();
		setPrimaryStage(primaryStage);
		loadMainWindow();
	}

	/**
	 * runs the javaFX application with the set arguments
	 */
	public void startApplication() {
		launch(arguments);

	}

	/**
	 * start a new deamon with the genetic algorithm
	 */
	public void startGeneticAlgorithm() {
		geneticAlgorithm = new GeneticAlgorithm(this);
		geneticAlgorithmThread = new Thread(geneticAlgorithm);
		geneticAlgorithmThread.setDaemon(true);
		geneticAlgorithmThread.start();
	}
}
