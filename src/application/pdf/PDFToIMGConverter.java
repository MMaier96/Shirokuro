package application.pdf;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.ghost4j.document.DocumentException;
import org.ghost4j.document.PDFDocument;
import org.ghost4j.renderer.RendererException;
import org.ghost4j.renderer.SimpleRenderer;

import application.config.AppConfig;

public class PDFToIMGConverter implements Runnable {

	private static Logger logger = Logger.getLogger(PDFToIMGConverter.class);
	
	private String name;
	private File inputFile;
	private File outputDirectory;
	private List<Image> images;
	private PDFDocument pdfDocument;
	private SimpleRenderer renderer;
	private boolean instanceSuccess = true;

	public PDFToIMGConverter(String path) {
		this.inputFile = new File(path);
		if (inputFile.getName().contains(".")) {
			this.name = inputFile.getName().split("\\.")[0];
		}else {
			logger.error("the file [" + inputFile.getName() + "] has no or a wrong file ending! ONLY PDF SUPPORTED!" );
			instanceSuccess = false;
			return;
		}
		this.outputDirectory = new File(AppConfig.instance.outputFolderName + name + "/");
		this.renderer = new SimpleRenderer();
		this.pdfDocument = new PDFDocument();
		this.renderer.setResolution(AppConfig.instance.pdfResolution);
	}

	@Override
	public void run() {	
		if (!instanceSuccess) {
			logger.error("because of wrong file ending of [" + inputFile.getName() + "] the convertion was skipped!" );
			return;
		}
		
		logger.info("checking weather input file exists ...");
		if (!inputFile.exists()) {
			logger.info("the file [" + inputFile +"] does not exist. Skipped converting to images");
			return;
		}

		logger.info("checking weather output directory exists ...");
		if (!outputDirectory.mkdirs()) {
			logger.info("the output directory [" + outputDirectory +"] already exist. Skipped converting to images");
			return;
		}

		logger.info("loading pdf file ["+ inputFile +"] ...");
		if (!loadPDFFile()) {
			logger.info("error occurred on loading the pdf file ["+ inputFile +"] ...");
			return;
		}
		
		logger.info("start rendering pdf file ["+ pdfDocument +"] ...");
		if (!renderPDFFile()) {
			logger.info("error occurred on rendering pdf file ["+ pdfDocument +"] ...");
			return;
		}
		
		logger.info("start saving images of pdf file ["+ pdfDocument +"] to [" + outputDirectory + "] ...");
		if (!saveRenderedImages()) {
			logger.info("error occurred on saving images of pdf file ["+ pdfDocument +"] to [" + outputDirectory + "] ...");
			return;
		}
	}

	private boolean saveRenderedImages() {
		int prefixCounter = 0;
		File outputFile;
		for (Image image : images) {
			try {
				outputFile = new File(AppConfig.instance.outputFolderName + name + "/" + (prefixCounter++) + ".png");
				ImageIO.write((RenderedImage) image, "png", outputFile);
			} catch (IOException e) {
				logger.error(e.getMessage());
				return false;
			}
		}
		return true;
	}

	private boolean renderPDFFile() {
		try {
			images = renderer.render(pdfDocument);
		} catch (IOException | RendererException | DocumentException e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}

	private boolean loadPDFFile() {
		try {
			pdfDocument.load(inputFile);
		} catch (IOException e) {
			logger.error(e.getMessage());
			return false;
		}
		return true;
	}
}
