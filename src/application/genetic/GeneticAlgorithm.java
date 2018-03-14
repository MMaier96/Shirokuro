package application.genetic;

import application.Application;
import application.genetic.config.GeneticConfig;
import application.genetic.crossover.Crossover;
import application.genetic.mutation.Mutator;
import application.genetic.objects.Gene;
import application.genetic.objects.Individual;
import application.genetic.objects.Population;
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
	private Individual bestIndividual;

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
		
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				controller.clearDotConnectors();
				controller.setConfiguration(totalIterations, bestIndividual.getFitness());
				for (Gene _gene : bestIndividual.getGenes()) {
					controller.addConnector(_gene.getX1(), _gene.getX2(), _gene.getY1(), _gene.getY2());
				}
				controller.setFinish();
			}
			
		});
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

		for (int iterationCounter = 1; iterationCounter <= GeneticConfig.ITERATIONS; iterationCounter++) {
			if (iterationCounter > 1) {
				population.getIndividuals().set(0, bestIndividual);
			}
			totalIterations = iterationCounter;
			selector.setPopulation(population);
			selector.select();

			int crossoverLenght = selector.getSelectionSize();
			for (int j = 0; j < crossoverLenght; j++) {
				crossover.setIndividuals(selector.getNextSelection1(), selector.getNextSelection2());
				population.addIndividual(crossover.doCrossOver());
			}

			// mutate
			if (iterationCounter % 500 == 0) {
				Mutator mutator = new Mutator();
				mutator.setPopulation(population);
				population = mutator.mutate();
			}
			// turnament

			tournament.setPopulation(population);
			tournament.doTournament();

			if (bestIndividual == null
					|| bestIndividual.getFitness() < population.getIndividuals().get(0).getFitness()) {
				bestIndividual = population.getIndividuals().get(0);
			}
			if (iterationCounter % 100 == 0) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						controller.clearDotConnectors();
						controller.setConfiguration(totalIterations, bestIndividual.getFitness());
						for (Gene _gene : bestIndividual.getGenes()) {
							controller.addConnector(_gene.getX1(), _gene.getX2(), _gene.getY1(), _gene.getY2());
						}
					}
				});

			}
		}
	}

	private Population createNewPopulation() {
		Population population = new Population();
		population.createIndividuals();
		return population;
	}
}
