package gatsp;
import java.util.Random;

/** May be used to add a probability of activation on any existing CrossoverMethod.
 * @author Stephen Wattam
 */
public class ProbabalisticCrossoverMethod extends ProbabalisticModifier
	implements CrossoverMethod{
    
    CrossoverMethod m;

    /** Creates a new ProbabalisticCrossoverMethod using a random seed. 
     * @param m The mutator method to use if the probability test passes.
     * @see ProbabalisticModifier
     * */
    public ProbabalisticCrossoverMethod(double threshold, CrossoverMethod m) throws Exception{
	super(threshold);
	this.m = m;
    }

    /** Creates a new ProbabalisticModifier using a pre-existing Random object. 
     * @param r The Random object to use
     * @see ProbabalisticModifier
     * */
    public ProbabalisticCrossoverMethod(Random r, double threshold, CrossoverMethod m) throws Exception{
	super(r, threshold);
	this.m = m;
    }



    /** Creates a new ProbabalisticModifier using a random seed. 
     * @param m The mutator method to use if the probability test passes.
     * @see ProbabalisticModifier
     * */
    public ProbabalisticCrossoverMethod(long seed, double threshold, CrossoverMethod m) throws Exception{
	super(seed, threshold);
	this.m = m;
    }

    /** Performs crossover, maybe.
     * @see CrossoverMethod#performCrossover
     */ 
    public Chromosone performCrossover(Chromosone p1, Chromosone p2){
	if(test())
	    return m.performCrossover(p1, p2);
	return p1;
    }
}
