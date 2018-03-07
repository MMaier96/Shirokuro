package application.genetic.crossover;

import java.util.Random;

import application.genetic.Gene;
import application.genetic.Individual;
import javafx.geometry.Side;

public class Crossover2 {

	private Individual individual1;
	private Individual individual2;
	private Individual result1;
	private Individual result2;

	public Crossover2() {

	}

	public void setIndividuals(Individual individual1, Individual individual2) {
		this.individual1 = individual1;
		this.individual2 = individual2;

	}

	public Individual doCrossOver() {
		result1 = new Individual();
		result2 = new Individual();

		boolean switchIt = false;
		Random rand = new Random();
		for (int i = 0; i < individual1.getGenes().size(); i++) {
			Gene tempGene1 = individual1.getGenes().get(rand.nextInt(individual1.getGenes().size()));
			Gene tempGene2 = individual2.getGenes().get(rand.nextInt(individual1.getGenes().size()));
			if (switchIt = !switchIt) {
				result1.addGene(new Gene(tempGene2.getX1(), tempGene2.getY1(), tempGene2.getX2(), tempGene2.getY2()));
				result2.addGene(new Gene(tempGene1.getX1(), tempGene1.getY1(), tempGene1.getX2(), tempGene1.getY2()));
			}else {
				result2.addGene(new Gene(tempGene2.getX1(), tempGene2.getY1(), tempGene2.getX2(), tempGene2.getY2()));
				result1.addGene(new Gene(tempGene1.getX1(), tempGene1.getY1(), tempGene1.getX2(), tempGene1.getY2()));
			}
		}

		
		if (result1.getFitness()>result2.getFitness()) {
			return result1;
		}
		return result2;
	}
}
