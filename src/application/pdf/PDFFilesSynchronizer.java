package application.pdf;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import application.config.AppConfig;

public class PDFFilesSynchronizer {

	private static Logger logger = Logger.getLogger(PDFFilesSynchronizer.class);

	
	private List<File> files;
	private boolean instanceSuccess = true;
	
	public PDFFilesSynchronizer() {
		File directory = new File(AppConfig.instance.inputFolderName);
		if (!directory.exists()) {
			instanceSuccess = false;
			logger.error("the input folder [" + AppConfig.instance.inputFolderName + "] does not exist!");
			logger.error("the input folder [" + AppConfig.instance.inputFolderName + "] does not exist!");
			return;
		}
		files = Arrays.asList(directory.listFiles());		
	}
	
	public void startSynchronising() {
		if (!instanceSuccess) {
			logger.error("the input folder [" + AppConfig.instance.inputFolderName + "] does not exist! Skipping convertion");
			return;
		}
		for (File file : files) {
			PDFToIMGConverter converter = new PDFToIMGConverter(file.getPath());
			new Thread(converter).run();
		}
	}
}
