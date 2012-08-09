package gatsp;
import java.util.Random;

/** May be used to alter the probability of the mutator mutating.
 * @author Stephen Wattam
 */
public class ProbabalisticMutator extends ProbabalisticModifier
	implements Mutator{
   
    /** The method to mutate. */	
    Mutator m;

    /** Creates a new ProbabalisticMutator using a random seed. 
     * @param m The mutator method to use if the probability test passes.
     * @see ProbabalisticModifier
     * */
    public ProbabalisticMutator(double threshold, Mutator m) throws Exception{
	super(threshold);
	this.m = m;
    }

    /** Creates a new ProbabalisticModifier using a pre-existing Random object. 
     * @param r The Random object to use
     * @see ProbabalisticModifier
     * */
    public ProbabalisticMutator(Random r, double threshold, Mutator m) throws Exception{
	super(r, threshold);
	this.m = m;
    }



    /** Creates a new ProbabalisticModifier using a random seed. 
     * @param m The mutator method to use if the probability test passes.
     * @see ProbabalisticModifier
     * */
    public ProbabalisticMutator(long seed, double threshold, Mutator m) throws Exception{
	super(seed, threshold);
	this.m = m;
    }

    /** Mutates a chromosone, possibly.
     * @see Mutator#mutate
     */ 
    public Chromosone mutate(Chromosone a){
	if(test())
	    return m.mutate(a);
	return a;

    }
}
