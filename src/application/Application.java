package application;

import java.io.IOException;

import application.config.AppConfig;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

	private String[] arguments;
	private Stage primaryStage;
	private AppConfig appConfig;
	private StackPane root;	
	
	@Override
	public void start(Stage primaryStage) {
		loadConfiguration();
		setPrimaryStage(primaryStage);
		loadMainWindow();
	}

	private void loadConfiguration() {
		appConfig = AppConfig.instance;
	}

	private void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void setArguments(String... arguments) {
		this.arguments = arguments;
	}

	private void loadMainWindow() {
		primaryStage.setTitle(appConfig.windowTitle);
		
		try {
			root = FXMLLoader.load(getClass().getResource("gui/application.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root, appConfig.gridWidth+300, appConfig.gridWidth);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public void startApplication() {
		launch(arguments);
	}

}
