
public class Algorithm_Binary {


    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

	//eveltion happend in this part!
    // all the enario should write here.
	static Population_Binary evolvePopulation(Population_Binary pop) {
		//make new population for doing selection,crossover and mutation.
		Population_Binary newPopulation = new Population_Binary(pop.size(), false);

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
			Individual_Binary indiv1 = tournomentSelection(pop);
            Individual_Binary indiv2 = tournomentSelection(pop);
            Individual_Binary newIndiv = twoPoint(indiv1, indiv2);
            newPopulation.saveIndivitual(i, newIndiv);
		}
		// for mutation
		for( int i = elitismOffset; i < newPopulation.size(); i ++) {
			binaryMutation(newPopulation.getIndividual(i));
		}

		return newPopulation;

	}


	private static Individual_Binary roullteSelection(Population_Binary pop) {

		float totalSum = 0;

		for(int i = 0; i < pop.size(); i++) {

		Individual_Binary fitness = pop.getIndividual(i);
		totalSum += -(fitness.getFitness());
		}
		float randomId = (float) Math.random() * totalSum;
		float partialSum = 0;
		for(int i = 0; i < pop.size(); i++) {
			Individual_Binary fitness = pop.getIndividual(i);
			partialSum += -(fitness.getFitness());
			if ( partialSum >= randomId) {
				return fitness;
			}
		}
		return null;
	 }

	 private static  Individual_Binary stochasticUniversalSampling(Population_Binary pop) {
		 float[] n = new float[pop.size()];
			Individual_Binary indiv = new Individual_Binary();

			for(int i = 0; i < n.length; i++) {
				n[i] = 0;
			}

			float max = n[0];
			float randomId = (float) Math.random();
			float sum = 0;
			for(int i = 0; i < pop.size(); i++) {
			Individual_Binary fitness = pop.getIndividual(i);
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

	 static Individual_Binary tournomentSelection(Population_Binary pop) {
		//
		Population_Binary tournoment = new Population_Binary(tournamentSize, false);
		for(int i = 0; i < tournoment.size(); i++){
		int randomId = (int) (Math.random() * pop.size());
		tournoment.saveIndivitual(i, pop.getIndividual(randomId));
		}
		Individual_Binary fitness = tournoment.getFittest();
		return fitness;

	}

	 public static Individual_Binary SinglePoint(Individual_Binary p1, Individual_Binary p2){
			Individual_Binary child = new Individual_Binary();
			int point = (int) ((Math.random() * p1.size()-2) + 1);
			for(int i=0; i<p1.size(); i++){
			    if(i<point){
				child.setGene(i, p1.getGene(i));
			    }else{
				child.setGene(i, p2.getGene(i));
			    }
			}
			return child;
		    }

		    public static Individual_Binary twoPoint(Individual_Binary p1, Individual_Binary p2){
			Individual_Binary child = new Individual_Binary();
			int bitSize = p1.getBitSize();
			int bitGeneSize = p1.getGeneSize();
			int point1 = (int) ((Math.random() * (bitGeneSize -2)) + 1) * bitSize;
			int point2 = (int) ((Math.random() * (bitGeneSize -2)) + 1) * bitSize;
//			System.out.println(point1/bitSize + "," + point2/bitSize);
			while(point2 == point1){
			    point2 = (int) ((Math.random() * (bitGeneSize -2)) + 1);	// Prevent single point crossover
			}
			if(point1 > point2){
			    int tmp = point1;
			    point1 = point2;
			    point2 = tmp;
			}
			for(int i=0; i<p1.size(); i++){
			    if(i<point1 || i>point2){
				child.setGene(i, p1.getGene(i));
			    }else{
				child.setGene(i, p2.getGene(i));
			    }
			}
			return child;
		    }

		    public static Individual_Binary binaryUniform(Individual_Binary p1, Individual_Binary p2){
			Individual_Binary child = new Individual_Binary();
			for(int i=0; i<p1.size(); i++){
			    if(Math.random() > 0.9){
				child.setGene(i, p1.getGene(i));
			    }else{
				child.setGene(i, p2.getGene(i));
			    }
			}
			return child;
		    }

		    public static void binaryMutation(Individual_Binary chromosome){
		    	for(int i=0; i<chromosome.size(); i++){
		    	    double mutationRate = Math.random() * 0.09 + 0.001;
		    	    if(Math.random() < mutationRate){
		    		byte gene = chromosome.getGene(i);
		    		gene = (byte) ((gene == 0) ? 1 : 0);
		    		chromosome.setGene(i, gene);
		    	    }
		    	}
		    }
}


