package gatsp;
import java.util.Random;
/** Performs many mutations using one mutator, each with a probability, up to a set maximum.  Can be used to accentuate
 * the effects of a set mutator.
 * @author Stephen Wattam
 */
public class RandomMultiplePassMutator implements Mutator{
    /** Stores the random source for this object */ 
    Random r;

    /** Threshold for re-entropising */
    private double threshold = 0.5;

    /** maximum retries */
    private int max_iterations;

    /** The mutation method to use */
    private Mutator method;
   
    /** Creates a new factory with a given seed.  This can be useful for re-running situations
     * @param seed The seed with which to initialise the source of entropy
     * @param threshold The probability of running the mutator each pass
     * @param method The Mutator to use.
     */ 
    public RandomMultiplePassMutator(long seed, double threshold, int max_iterations, Mutator method){
	r = new Random(seed);
	init(threshold, max_iterations, method);
    }

    /** Creates a RandomMultiplePassMutator with a random seed. 
     * @param threshold The probability of running the mutator each pass
     * @param method The Mutator to use.
     * */
    public RandomMultiplePassMutator(double threshold, int max_iterations, Mutator method){
	r = new Random();
	init(threshold, max_iterations, method);
    }

    /** Creates a new RandomMultiplePassMutator using the given source of entropy.
     * @param r The source of random numbers to use
     * @param threshold The probability of running the mutator each pass
     * @param method The Mutator to use.
     */ 
    public RandomMultiplePassMutator(Random r, double threshold, int max_iterations, Mutator method){
	this.r = r;
	init(threshold, max_iterations, method);
    }

    /** Sets default options for all constructors.
     * @param threshold The probability of executing the mutator
     * @param max_iterations The number of times to possibly mutate
     * @param method The mutator to use
     */
    private void init(double threshold, int max_iterations, Mutator method){
	this.threshold = threshold;
	this.max_iterations = max_iterations;
	this.method = method;
    }

    /** Mutates a chromosone.
     * @see Mutator#mutate(Chromosone)
     */
    public Chromosone mutate(Chromosone a){
	for(int i=0; i<max_iterations; i++)
	    if(r.nextDouble() < threshold)
		method.mutate(a);
	return a;
    }

}

