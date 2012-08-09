package gatsp;
import java.util.Random;

public class RandomSinglePointCrossoverMethod extends SinglePointCrossoverMethod implements CrossoverMethod{
    
    
    /** Creates a new factory with a given seed.  This can be useful for re-running situations
     * @param seed The seed with which to initialise the source of entropy
     */ 
    public RandomSinglePointCrossoverMethod(long seed){
	super(seed);
    }

    /** Creates a RandomSinglePointCrossoverMethod with a random seed. */
    public RandomSinglePointCrossoverMethod(){
	super();
    }

    /** Creates a new RandomSinglePointCrossoverMethod using the given source of entropy.
     * @param r The source of random numbers to use
     */ 
    public RandomSinglePointCrossoverMethod(Random r){
	super(r);
    }


    private Chromosone swapRandomCities(Chromosone a){
	//select two random points
	int g1 = r.nextInt( a.genes.length );
	int g2 = r.nextInt( a.genes.length );

	//swap them
	int temp = a.genes[g1];
	a.genes[g1] = a.genes[g2];	
	a.genes[g2] = temp;

	return a;
    }


    private Chromosone mangle(Chromosone p, float veracity){
	int[] newarray = new int[p.genes.length];
	System.arraycopy(p.genes, 0, newarray, 0, p.genes.length);
	Chromosone r = new Chromosone(newarray);
	
	for(int i=0; i<r.genes.length * veracity; i++)
	    r = swapRandomCities(r);
	return r;
    }

    public Chromosone performCrossover(Chromosone p1, Chromosone p2){
	return super.performCrossover(p1, mangle(p2, .2f));
    }
}
