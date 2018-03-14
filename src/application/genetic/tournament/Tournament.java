package application.genetic.tournament;



import java.util.Collections;
import java.util.Comparator;

import application.genetic.config.GeneticConfig;
import application.genetic.objects.Individual;
import application.genetic.objects.Population;

public class Tournament {
	private Population population;

	public Tournament() {
	}

	public void setPopulation(Population population) {
		this.population = population;

	}
	public void doTournament() {
		Collections.sort(population.getIndividuals(), new Comparator<Individual>() {
            @Override
            public int compare(Individual i1, Individual i2) {
                return i2.getFitness() - i1.getFitness();
            }
        });
		
		for (int j = (GeneticConfig.SIZE_OF_POPULATION + (GeneticConfig.SIZE_OF_POPULATION / 2))-1; j > GeneticConfig.SIZE_OF_POPULATION-1; j--) {
			population.getIndividuals().remove(j);
		}
	}
}
