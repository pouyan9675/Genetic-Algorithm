
public class Population {
	Individual[] individuals;

	public Population(int population, boolean intialize) {
		// define something

		individuals = new Individual[population];

		if (intialize) {
			for (int i = 0; i < size(); i++) {
				Individual newIndivituals = new Individual();
				newIndivituals.generateIndivitual();
				saveIndivitual(i, newIndivituals);
			}
		}
	}

	public Individual getIndividual(int index) {
		return individuals[index];
	}

	public int size() {
		return individuals.length;
	}

	public void saveIndivitual(int index, Individual indiv) {
		individuals[index] = indiv;
	}

	//best fit in the current population.
	public Individual getFittest() {
        Individual fittest = individuals[0];
        // Loop through individuals to find fittest
        for (int i = 0; i < size(); i++) {
            if (-(fittest.getFitness()) <= -(getIndividual(i).getFitness())) {

            	fittest = getIndividual(i);
//            	System.out.println("fitess in one popu:" + fittest.getFitness());
            }
        }
        return fittest;
    }
}
