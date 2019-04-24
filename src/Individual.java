
public class Individual {
	int defaultgenLentgh = 10;
	private float fitness = 0;
	private float[] genesReal = new float[defaultgenLentgh];

	//Create a random indivitual

	public void generateIndivitual(){
		for(int i = 0; i < size(); i++) {
		// it should be in 0 to pi;
		float gene = (float) ( Math.random() * Math.PI);
			genesReal[i] = gene;
		}

	}

	// changing the default gen size;
	public void setDefaultGeneLength(int length){
		 defaultgenLentgh = length;
	}

	public float getFitness() {
		if(fitness == 0 ) {
			fitness = Michalowich.f(this);
		}
		return fitness;
	}

	public int size() {
		return genesReal.length;
	}

	public float getGene(int i) {
		return genesReal[i];
	}

	public void setGene(int i, float value) {
		genesReal[i] = value;
		fitness = 0;
	}
}
