
public class Algorithm {

    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

	//eveltion happend in this part!
    // all the enario should write here.
	static Population evolvePopulation(Population pop) {
		//make new population for doing selection,crossover and mutation.
		Population newPopulation = new Population(pop.size(), false);

		// Keep our best individual
        if (elitism) {
            newPopulation.saveIndivitual(0, pop.getFittest());
        }

        // Crossover population
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }

		// for selection and crossover
		for(int i = elitismOffset; i < pop.size(); i++) {
			Individual indiv1 = tournomentSelection(pop);
            Individual indiv2 = tournomentSelection(pop);
            Individual newIndiv = BLX(indiv1, indiv2);
            newPopulation.saveIndivitual(i, newIndiv);
		}
		// for mutation
		for( int i = elitismOffset; i < newPopulation.size(); i ++) {
			realMutation(newPopulation.getIndividual(i));
		}

		return newPopulation;

	}


	private static Individual roullteSelection(Population pop) {

		float totalSum = 0;

		for(int i = 0; i < pop.size(); i++) {

		Individual fitness = pop.getIndividual(i);
		totalSum += -(fitness.getFitness());
		}
		float randomId = (float) Math.random() * totalSum;
		float partialSum = 0;
		for(int i = 0; i < pop.size(); i++) {
			Individual fitness = pop.getIndividual(i);
			partialSum += -(fitness.getFitness());
			if ( partialSum >= randomId) {
				return fitness;
			}
		}
		return null;
	 }

	 private static Individual stochasticUniversalSampling(Population pop) {
		float[] n = new float[pop.size()];
		Individual indiv = new Individual();

		for(int i = 0; i < n.length; i++) {
			n[i] = 0;
		}

		float max = n[0];
		float randomId = (float) Math.random();
		float sum = 0;
		for(int i = 0; i < pop.size(); i++) {
			Individual fitness = pop.getIndividual(i);
			sum += -(fitness.getFitness());
			while ( sum >= randomId) {
				n[i]++;
				randomId = randomId + 1;
			}
			if(max < n[i]) {
				max = n[i];
				indiv = fitness;
//				System.out.println(indiv.getFitness());
			}
			}
		return indiv;
	 }

	 static Individual tournomentSelection(Population pop) {
		//
		Population tournoment = new Population(tournamentSize, false);
		for(int i = 0; i < tournoment.size(); i++){
		int randomId = (int) (Math.random() * pop.size());
		tournoment.saveIndivitual(i, pop.getIndividual(randomId));
		}
		Individual fitness = tournoment.getFittest();
		return fitness;

	}

	 /**
	     * A crossover method that will use intermediate recombination (Math crossover) for <b>REAL</b> genes
	     * @param p1	Parent 1 chromosome with double values
	     * @param p2	Parent 2 chromosome with double values
	     * @return child Child chromosome with double values
	     */
	    public static Individual intermidiate(Individual p1, Individual p2){
		Individual child = new Individual();
		for(int i=0; i< p1.size(); i++){
		    float landa = (float) Math.random();
		    child.setGene(i, landa * p1.getGene(i) + (1 - landa) * p2.getGene(i));;
		}
		return child;
	    }

	    /**
	     * A crossover method that will use line recombination (For <b>REAL</b> genes)
	     * @param p1	Parent 1 chromosome with double values
	     * @param p2	Parent 2 chromosome with double values
	     * @return child Child chromosome with double values
	     */
	    public static Individual linear(Individual p1, Individual p2){
	    	Individual child = new Individual();
		float landa = (float) Math.random();
		for(int i=0; i< p1.size(); i++){
		    child.setGene(i, landa * p1.getGene(i) + (1 - landa) * p2.getGene(i));
		}
		return child;
	    }

	    /**
	     * Blend crossover (For <b>REAL</b> and <b>INTEGER</b> genes)
	     * @param p1
	     * @param p2
	     * @return List of childrens that are two chromosomes
	     */
	    public static Individual BLX(Individual p1, Individual p2){
	    	Individual child = new Individual();
	    	float landa = (float) Math.random();
	    	for(int i=0; i< p1.size(); i++){
	    	    child.setGene(i, p1.getGene(i) - landa * (p1.getGene(i) - p2.getGene(i)));
	    	}
	    	return child;
	        }

	    public static Individual realMutation(Individual chromosome){
	    	for(int i=0; i< chromosome.size(); i++){
	    	    float mutationRate = (float) (Math.random() * 0.09 + 0.01);
	    	    if(Math.random() < mutationRate){
	    		chromosome.setGene(i, (float) (Math.random() * Math.PI));
	    	    }
	    	}
	    	return chromosome;
	        }

}
