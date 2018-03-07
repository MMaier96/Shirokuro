package application.genetic.selection;

import java.util.Random;

import application.genetic.Individual;
import application.genetic.Population;

public class Selector {

	private Population population;
	private Population selection1;
	private Population selection2;

	public Selector() {
	}

	public void setPopulation(Population population) {
		this.population = population;
	}
	
	public void select() {
		
		selection1 = new Population();
		selection2 = new Population();
				
		
		Random random = new Random();
		do {
			int randomIndex = random.nextInt(population.getSize());
			selection1.addIndividual(population.getIndividuals().get(randomIndex));
			population.getIndividuals().remove(randomIndex);

			randomIndex = random.nextInt(population.getSize());
			selection2.addIndividual(population.getIndividuals().get(random.nextInt(population.getSize())));
			population.getIndividuals().remove(randomIndex);
		} while (population.getSize() > 0);
	}

	public int getSelectionSize() {
		return selection1.getSize();
	}

	public Individual getNextSelection1() {
		Random random = new Random();
		int index = random.nextInt(selection1.getSize());
		Individual individual = selection1.getIndividuals().get(index);
		selection1.getIndividuals().remove(index);
		population.addIndividual(individual);
		return individual;
	}
	
	public Individual getNextSelection2() {
		Random random = new Random();
		int index = random.nextInt(selection2.getSize());
		Individual individual = selection2.getIndividuals().get(index);
		selection2.getIndividuals().remove(index);
		population.addIndividual(individual);
		return individual;
	}
}
