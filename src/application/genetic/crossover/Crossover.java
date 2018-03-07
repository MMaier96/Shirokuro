package application.genetic.crossover;

import java.util.Random;

import application.genetic.Gene;
import application.genetic.GeneticConfig;
import application.genetic.Individual;

public class Crossover {

	private Individual individual1;
	private Individual individual2;
	private Individual result;

	public Crossover() {
		
	}
	public void setIndividuals(Individual individual1, Individual individual2) {
		this.individual1 = individual1;
		this.individual2 = individual2;
		
	}
	
	public Individual doCrossOver() {
		result = new Individual();
			boolean switchIndividual = false;
			
			int index1 = 0;
			int index2 = 0;
			mainLoop: do {
				Gene tempGene;
				if (switchIndividual) {
					for (int j = index1; j < individual1.getGenes().size(); j++ , index1++) {
						tempGene = individual1.getGenes().get(j);
						if(isGeneAcceptable(tempGene)) {
							result.addGene(new Gene(tempGene.getX1(), tempGene.getY1(), tempGene.getX2(), tempGene.getY2()));
							switchIndividual = !switchIndividual;
							continue mainLoop;
						}
					}
					switchIndividual = !switchIndividual;
					continue mainLoop;
				}else {
					for (int j = index2; j < individual1.getGenes().size(); j++ , index2++) {
						tempGene = individual1.getGenes().get(j);
						if(isGeneAcceptable(tempGene)) {
							result.addGene(new Gene(tempGene.getX1(), tempGene.getY1(), tempGene.getX2(), tempGene.getY2()));
							switchIndividual = !switchIndividual;
							continue mainLoop;
						}
					}
					switchIndividual = !switchIndividual;
					continue mainLoop;
				}
				
			} while (index1 < individual1.getGenes().size() || index2 < individual2.getGenes().size() );
		
		System.out.println(result.getGenes().size());
		result.createRemainingGenes();
		
		
		return result;
	}
	private boolean isGeneAcceptable(Gene gene) {
		for (Gene _gene : result.getGenes()) {
			if (_gene.getX1() == gene.getX1()) {
				return false;
			}else if (_gene.getX2() == gene.getX1()) {
				return false;
			}else if (_gene.getY1() == gene.getY1()) {
				return false;
			}else if (_gene.getY2() == gene.getY1()) {
				return false;
			}else if (_gene.getX1() == gene.getX2()) {
				return false;
			}else if (_gene.getX2() == gene.getX2()) {
				return false;
			}else if (_gene.getY1() == gene.getY2()) {
				return false;
			}else if (_gene.getY2() == gene.getY2()) {
				return false;
			}
		}
		return true;
	}
}
