
public class main {
	public static void main(String[] args) {

		Population_Binary myPop = new Population_Binary(100, true);

		// Evolve our population until we reach an optimum solution
		int generationCount = 0;
		while (generationCount < 100) {
			generationCount++;
			System.out.println(myPop.getFittest().getFitness());
			myPop = Algorithm_Binary.evolvePopulation(myPop);
		}
		System.out.println("Solution found!");
		System.out.println("Generation: " + generationCount);
//		System.out.println("Genes:");
//		System.out.println(myPop.getFittest());

	}
}
