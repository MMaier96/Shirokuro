package application.genetic.config;

import application.genetic.objects.Individual;

public enum GeneticConfig {

	instance;
	public static final int CROSSOVER_ITERATIONS = 50;
	public static final int ITERATIONS = 500;
	public static final int MAX_FINTNESS = Individual.getPerfectIndividual().getFitness();
    public static final int SIZE_OF_POPULATION = 500;
	
}
