package application;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.util.List;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

import org.ghost4j.document.DocumentException;
import org.ghost4j.document.PDFDocument;
import org.ghost4j.renderer.RendererException;
import org.ghost4j.renderer.SimpleRenderer;

import application.config.AppConfig;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

	private String[] arguments;
	private Stage primaryStage;
	private AppConfig appConfig;
	private HBox root;
	
	
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
		
		try {
			root = FXMLLoader.load(getClass().getResource("gui/application.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root, appConfig.windowWidth, appConfig.windowWidth);
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(appConfig.startAsFullScreen);
		primaryStage.show();
	}

	public void startApplication() {
		launch(arguments);
	}

}
