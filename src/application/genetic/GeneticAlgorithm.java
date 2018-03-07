package application.genetic;

import application.Application;
import application.genetic.crossover.Crossover;
import application.genetic.selection.Selector;
import application.genetic.tournament.Tournament;
import application.gui.controller.GuiController;
import javafx.application.Platform;

public class GeneticAlgorithm implements Runnable {

	private Application app;
	private GuiController controller;

	private Population population;
	private Selector selector;
	private Crossover crossover;
	private Tournament tournament;
	private int totalIterations;

	public GeneticAlgorithm(Application app) {
		this.app = app;
	}

	@Override
	public void run() {
		if (app == null)
			return;

		controller = app.getController();

		if (controller == null)
			return;

		startAlgorithm();
	}

	private void startAlgorithm() {

		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.setStatus("processing ...");
			}
		});
		
		population = createNewPopulation();
		selector = new Selector();
		crossover = new Crossover();
		tournament = new Tournament();

		for (int iterationCounter = 0; iterationCounter < GeneticConfig.ITERATIONS; iterationCounter++) {
			totalIterations = iterationCounter;
			selector.setPopulation(population);
			selector.select();

			int crossoverLenght = selector.getSelectionSize();
			for (int j = 0; j < crossoverLenght; j++) {
				crossover.setIndividuals(selector.getNextSelection1(), selector.getNextSelection2());
				population.addIndividual(crossover.doCrossOver());
			}

			// mutate

			// turnament

			tournament.setPopulation(population);
			tournament.doTournament();

			if (iterationCounter % 100 == 0) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						controller.clearDotConnectors();
						controller.setConfiguration(totalIterations, population.getIndividuals().get(0).getFitness());
						for (Gene _gene : population.getIndividuals().get(0).getGenes()) {
							controller.addConnector(_gene.getX1(), _gene.getX2(), _gene.getY1(), _gene.getY2());
						}
					}
				});
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private Population createNewPopulation() {
		Population population = new Population();
		population.createIndividuals();
		return population;
	}
}
