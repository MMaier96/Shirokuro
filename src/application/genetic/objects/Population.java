package application.genetic.objects;

import java.util.ArrayList;

import application.genetic.config.GeneticConfig;

public class Population {

	private ArrayList<Individual> individuals;

	public Population() {
		individuals = new ArrayList<Individual>();
	}

	/**
	 * add an individual to the list
	 * 
	 * @param individual
	 */
	public void addIndividual(Individual individual) {
		this.individuals.add(individual);
	}

	/**
	 * create an whole population by the config
	 */
	public void createIndividuals() {
		for (int i = 0; i < GeneticConfig.SIZE_OF_POPULATION; i++) {
			Individual individual = new Individual();
			individual.createAllGenes();
			individuals.add(individual);
		}
	}

	/**
	 * returns the individuals list
	 * 
	 * @return ArrayList<Individual>
	 */
	public ArrayList<Individual> getIndividuals() {
		return individuals;
	}

	/**
	 * returns the population size
	 * 
	 * @return int
	 */
	public int getSize() {
		return individuals.size();
	}

	/**
	 * sets the individuals list
	 * 
	 * @param ArrayList<Individual>
	 */
	public void setIndividual(ArrayList<Individual> individuals) {
		this.individuals = individuals;
	}

}
