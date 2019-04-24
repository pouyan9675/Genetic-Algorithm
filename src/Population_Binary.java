
public class Population_Binary {

	Individual_Binary[] individuals_binary;

	 public Population_Binary(int population_binary, boolean intialize) {
		// define something

		individuals_binary = new Individual_Binary[population_binary];

		if (intialize) {
			for (int i = 0; i < size(); i++) {
				Individual_Binary newIndivituals = new Individual_Binary();
				newIndivituals.generateIndivitual();
				saveIndivitual(i, newIndivituals);
			}
		}
	}

	public Individual_Binary getIndividual(int index) {
		return individuals_binary[index];
	}

	public int size() {
		return individuals_binary.length;
	}

	public void saveIndivitual(int index, Individual_Binary indiv) {
		individuals_binary[index] = indiv;
	}

	//best fit in the current population.
	public Individual_Binary getFittest() {
        Individual_Binary fittest = individuals_binary[0];
        // Loop through individuals to find fittest
        for (int i = 0; i < size(); i++) {
        	 if (-(fittest.getFitness()) <= -(getIndividual(i).getFitness())) {

             	fittest = getIndividual(i);
            }
        }
        return fittest;
    }
}
