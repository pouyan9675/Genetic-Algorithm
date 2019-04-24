public class Individual_Binary {
    int geneSize = 10;
    int bitSize = 32;
    int defaultgenLentgh = geneSize * bitSize;

    private float fitness = 0;
    private byte[] genes = new byte[defaultgenLentgh];

    // Create a random indivitual

    public void generateIndivitual() {
	for(int i = 0; i < size(); i++){
	    // it should be in 0 to pi;
	    byte gene = (byte) (Math.round(Math.random()));
	    genes[i] = gene;
	}

    }

    // changing the default gene size;
    public void setDefaultGeneLength(int length) {
	defaultgenLentgh = length * bitSize;
    }

    public float[] convertor(int length, Individual_Binary indiv) {
	float[] number = new float[length];
	int j = 0;
	for(int k = 0; k < length; k++){

	    for(int i = bitSize - 1; i >= 0; i--){
		if(indiv.getGene(i + j) == 1){
		    number[k] += (float) Math.pow(2, i);
		}
	    }
	    number[k] = (float) (number[k] * (Math.PI / (Math.pow(2, bitSize) - 1)));
	    j += bitSize;
	}
	return number;
    }

    public float getFitness() {
	if(fitness == 0){
	    fitness = Michalowich.f(convertor(defaultgenLentgh / bitSize, this));
	}
	return fitness;
    }

    public int size() {
	return genes.length;
    }

    public byte getGene(int i) {
	return genes[i];
    }

    public void setGene(int i, byte j) {
	genes[i] = j;
	fitness = 0;
    }

    public int getBitSize() {
	return bitSize;
    }

    public int getGeneSize() {
	return geneSize;
    }
}
