
public class Michalowich {
private static int m = 10;

    public static float f(Individual input){
	float sigmaResult = 0;
	for(int i=0; i<input.size(); i++){
	    	float Xi = input.getGene(i);
	    	sigmaResult += Math.sin(Xi) * Math.pow(Math.sin(i*Xi*Xi/Math.PI), 2*m);
	}
	return -sigmaResult;
    }

    public static float f(float[] input){
    	float sigmaResult = 0;
    	for(int i=0; i<input.length; i++){
    	    	float Xi = input[i];
    	    	sigmaResult += Math.sin(Xi) * Math.pow(Math.sin(i*Xi*Xi/Math.PI), 2*m);
    	}
    	return -sigmaResult;
        }

    public void setM(int m){
	this.m = m;
    }

    public int getM(){
	return m;
    }

}
