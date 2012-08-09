package gatsp;
import java.util.Random;

public class RandomParentCrossoverMethod implements CrossoverMethod{

    
    /** Stores the random source for this object */ 
    Random r;
   
    /** Creates a new factory with a given seed.  This can be useful for re-running situations
     * @param seed The seed with which to initialise the source of entropy
     */ 
    public RandomParentCrossoverMethod(long seed){
	r = new Random(seed);
    }

    /** Creates a RandomParentCrossoverMethod with a random seed. */
    public RandomParentCrossoverMethod(){
	r = new Random();
    }

    /** Creates a new RandomParentCrossoverMethod using the given source of entropy.
     * @param r The source of random numbers to use
     */ 
    public RandomParentCrossoverMethod(Random r){
	this.r = r;
    }


    public Chromosone performCrossover(Chromosone p1, Chromosone p2){
	if(r.nextDouble() > 0.5)
	    return new Chromosone(p1.genes);
	return new Chromosone(p2.genes);
    }


}
