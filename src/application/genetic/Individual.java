package application.genetic;

import java.util.ArrayList;
import java.util.Random;

import application.gui.objects.Dot;
import application.gui.objects.DotList;
import javafx.scene.paint.Color;

public class Individual {
	ArrayList<Gene> genes = new ArrayList<Gene>();

	public Individual() {
		genes = new ArrayList<Gene>();
	}

	/**
	 * add a new gene to the list
	 * 
	 * @param gene
	 */
	public void addGene(Gene gene) {
		genes.add(gene);
	}

	public void createAllGenes() {
		ArrayList<Dot> dotlist = DotList.getClonedDotlist();

		Dot dot1, dot2;
		for (int i = 0; i < 27; i++) {
			dot1 = popFromDotList(dotlist);
			dot2 = popFromDotList(dotlist);
			genes.add(new Gene(dot1.getX(), dot1.getY(), dot2.getX(), dot2.getY()));
		}
	}

	public void createRemainingGenes() {
		ArrayList<Dot> dotlist = DotList.getClonedDotlist();
		// remove dots from existing genes
		for (Gene _gene : genes) {
			for (int i = 0; i < dotlist.size(); i++) {
				if (dotIsEqalGene(dotlist.get(i), _gene)) {
					dotlist.remove(i);
					break;
				}
			}
			for (int i = 0; i < dotlist.size(); i++) {
				if (dotIsEqalGene(dotlist.get(i), _gene)) {
					dotlist.remove(i);
					break;
				}
			}
		}

		// combine remaining dots as new genes
		Dot dot1, dot2;
		int sizeBefore = genes.size();
		for (int i = 0; i < 27 - sizeBefore; i++) {
			dot1 = popFromDotList(dotlist);
			dot2 = popFromDotList(dotlist);
			genes.add(new Gene(dot1.getX(), dot1.getY(), dot2.getX(), dot2.getY()));
		}
	}

	private boolean dotIsEqalGene(Dot dot, Gene _gene) {
		if (_gene.getX1() == dot.getX() && _gene.getY1() == dot.getY()) {
			return true;
		} else if (_gene.getX2() == dot.getX() && _gene.getY2() == dot.getY()) {
			return true;
		}
		return false;
	}

	/**
	 * returns the fitness of the individual [0,31]
	 * 
	 * @return
	 */
	public int getFitness() {
		int fitness = 0;

		geneLoop: for (Gene _gene : genes) {
			if (isGeneMonochrome(_gene)) {
				continue geneLoop;
			}
			fitness += 1;

			if (isVertical(_gene)) {
				fitness += 1;
				if (isDotBetweenVertical(_gene)) {
					continue geneLoop;
				}
				fitness += 1;

				for (Gene _gene2 : genes) {
					if (_gene2.equals(_gene)) {
						continue;
					}
					if (isHorizontal(_gene2)) {
						if (isBetween(_gene2.getY1(), _gene.getY1(), _gene.getY2())) {
							if (isBetween(_gene.getX1(), _gene2.getX1(), _gene2.getX2())) {
								continue geneLoop;
							}
						}
					}
				}
				fitness += 10;
			} else if (isHorizontal(_gene)) {
				fitness += 1;

				if (isDotBetweenHorizontal(_gene)) {
					continue geneLoop;
				}
				fitness += 1;

				for (Gene _gene2 : genes) {
					if (_gene2.equals(_gene)) {
						continue;
					}
					if (isVertical(_gene2)) {
						if (isBetween(_gene2.getX1(), _gene.getX1(), _gene.getX2())) {
							if (isBetween(_gene.getY1(), _gene2.getY1(), _gene2.getY2())) {
								continue geneLoop;
							}
						}
					}
				}
				fitness += 10;
			}
		}
		return fitness;
	}

	/**
	 * get genes of the individual
	 * 
	 * @return
	 */
	public ArrayList<Gene> getGenes() {
		return genes;
	}

	/**
	 * removes a random {@link Dot} from the dotlist and returns it
	 * 
	 * @param dotlist
	 * @return
	 */
	private Dot popFromDotList(ArrayList<Dot> dotlist) {
		Random random = new Random();
		Dot dot;
		int index = random.nextInt(dotlist.size());
		dot = dotlist.get(index);
		dotlist.remove(index);
		return dot;
	}

	/**
	 * set all genes of the individual
	 * 
	 * @param genes
	 */
	public void setGenes(ArrayList<Gene> genes) {
		this.genes = genes;
	}

	/**
	 * override to string to show the fitness representing the individual
	 */
	@Override
	public String toString() {
		return getFitness() + "";
	}

	private boolean isBetween(int check, int bound1, int bound2) {
		int min = Math.min(bound1, bound2);
		int max = Math.max(bound1, bound2);
		return min < check && check < max;
	}

	private boolean isVertical(Gene _gene) {
		return _gene.getX1() == _gene.getX2();
	}

	private boolean isHorizontal(Gene _gene) {
		return _gene.getY1() == _gene.getY2();
	}

	private boolean isGeneMonochrome(Gene _gene) {
		Color dotColor1 = DotList.getDotColorByDemension(_gene.getX1(), _gene.getY1());
		Color dotColor2 = DotList.getDotColorByDemension(_gene.getX2(), _gene.getY2());
		return dotColor1.equals(dotColor2);
	}

	private boolean isDotBetweenHorizontal(Gene _gene) {
		int min = Math.min(_gene.getX1(), _gene.getX2());
		int max = Math.max(_gene.getX1(), _gene.getX2());
		int deltaX = max - min;
		for (int i = 1; i < deltaX; i++) {
			if (DotList.getDotByDemension(min + i, _gene.getY1()) != null) {
				return true;
			}
		}
		return false;
	}

	private boolean isDotBetweenVertical(Gene _gene) {
		int min = Math.min(_gene.getY1(), _gene.getY2());
		int max = Math.max(_gene.getY1(), _gene.getY2());
		int deltaY = max - min;
		for (int i = 1; i < deltaY; i++) {
			if (DotList.getDotByDemension(_gene.getX1(), min + i) != null) {
				return true;
			}
		}
		return false;
	}
	
	public static Individual getPerfectIndividual() {
		Individual individual = new Individual();
		individual.addGene(new Gene(0, 0, 0, 1));
		individual.addGene(new Gene(0,2,0,4));
		individual.addGene(new Gene(0,6,0,7));
		individual.addGene(new Gene(0,8,0,9));
		individual.addGene(new Gene(1,0,1,2));
		individual.addGene(new Gene(1,3,1,6));
		individual.addGene(new Gene(1,7,1,8));
		individual.addGene(new Gene(2,5,2,8));
		individual.addGene(new Gene(3,0,5,0));
		individual.addGene(new Gene(6,0,8,0));
		individual.addGene(new Gene(9,0,9,2));
		individual.addGene(new Gene(8,1,8,2));
		individual.addGene(new Gene(9,3,6,3));
		individual.addGene(new Gene(6,1,7,1));
		individual.addGene(new Gene(4,2,6,2));
		individual.addGene(new Gene(3,3,3,6));
		individual.addGene(new Gene(3,7,3,8));
		individual.addGene(new Gene(3,9,4,9));
		individual.addGene(new Gene(6,9,7,9));
		individual.addGene(new Gene(8,9,8,8));
		individual.addGene(new Gene(5,9,5,7));
		individual.addGene(new Gene(9,7,9,6));
		individual.addGene(new Gene(9,5,6,5));
		individual.addGene(new Gene(7,4,6,4));
		individual.addGene(new Gene(5,4,5,5));
		individual.addGene(new Gene(4,6,7,6));
		individual.addGene(new Gene(7,7,8,7));
		return individual;
	}
}
