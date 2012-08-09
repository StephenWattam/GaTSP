package gatsp;

import java.util.Random;

public class RandomCrossoverMethod implements CrossoverMethod{

    
    /** Stores the random source for this object */ 
    Random r;

    CrossoverMethod[] methods;
   
    /** Creates a new factory with a given seed.  This can be useful for re-running situations
     * @param seed The seed with which to initialise the source of entropy
     */ 
    public RandomCrossoverMethod(long seed, CrossoverMethod ... methods){
	r = new Random(seed);
	this.methods = methods;
    }

    /** Creates a RandomCrossoverMethod with a random seed. */
    public RandomCrossoverMethod(CrossoverMethod ... methods){
	r = new Random();
	this.methods = methods;
    }

    /** Creates a new RandomCrossoverMethod using the given source of entropy.
     * @param r The source of random numbers to use
     */ 
    public RandomCrossoverMethod(Random r, CrossoverMethod ... methods){
	this.r = r;
	this.methods = methods;
    }

    public Chromosone performCrossover(Chromosone p1, Chromosone p2){
	return methods[ r.nextInt( methods.length )].performCrossover(p1, p2);
    }

}
