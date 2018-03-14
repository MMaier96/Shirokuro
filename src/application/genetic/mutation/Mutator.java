package application.genetic.mutation;

import java.util.Random;

import application.genetic.config.GeneticConfig;
import application.genetic.objects.Population;

public class Mutator {

	private Population population;

	public void setPopulation(Population population) {
		this.population = population;
		
	}

	public Population mutate() {
		Population tempPopulation = new Population();
		tempPopulation.createIndividuals();
		tempPopulation.getIndividuals().set(0, population.getIndividuals().get(0));
		for (int j = (GeneticConfig.SIZE_OF_POPULATION + (GeneticConfig.SIZE_OF_POPULATION / 2))-1; j > GeneticConfig.SIZE_OF_POPULATION-1; j--) {
			Random random = new Random();
			tempPopulation.getIndividuals().add(population.getIndividuals().get(random.nextInt(population.getIndividuals().size())));

		}
		System.out.println("mutated!");
		return tempPopulation;
	}

}
