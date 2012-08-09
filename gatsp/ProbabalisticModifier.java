package gatsp;
import java.util.Random;
/** Provides methods that allow for probabalistic activation systems for Crossover methods and Mutators.
 * @author Stephen Wattam
 */
public class ProbabalisticModifier{
  
    /** A source of Random */  
    Random r;

    /** The threshold below which the thingy will be activated. */
    double threshold;

    /** Creates a new ProbabalisticModifier using a random seed. 
     * @param threshold A figure between 0 and 1 that is the probability of the given method running. 
     * @throws Exception when the threshold is not within bounds 0 &lt; threshold &lt; 1
     * */
    public ProbabalisticModifier(double threshold) throws Exception{
	setThreshold(threshold);
	//random seed
	this.r = new Random();
    }

    /** Creates a new ProbabalisticModifier using a pre-existing Random object. 
     * @param r The Random object to use
     * @param threshold A figure between 0 and 1 that is the probability of the given method running. 
     * @throws Exception when the threshold is not within bounds 0 &lt; threshold &lt; 1
     * */
    public ProbabalisticModifier(Random r, double threshold) throws Exception{
	setThreshold(threshold);
	this.r = r;
    }



    /** Creates a new ProbabalisticModifier using a random seed. 
     * @param seed The seed used to initialise the random object.
     * @param threshold A figure between 0 and 1 that is the probability of the given method running. 
     * @throws Exception when the threshold is not within bounds 0 &lt; threshold &lt; 1
     * */
    public ProbabalisticModifier(long seed, double threshold) throws Exception{
	setThreshold(threshold);
	this.r = new Random(seed);
    }

    /** Sets the threshold.
     * @param threshold The probability of methods being run.  Between 0 and 1.
     * @throws Exception when the threshold is not within bounds 0 &lt; threshold &lt; 1
     */ 
    //TODO: change exception to ValueOutOfBounds 
    public void setThreshold(double threshold) throws Exception{
	if(threshold < 0 || threshold > 1)
	    throw new Exception("Threshold given must be between 0 and 1, inclusive");
	
	this.threshold = threshold;
    }

    /** Retuns true if the probability test passes.
     * @return true or false, depending on random double value and threshold
     */
    protected boolean test(){
	return (r.nextDouble() < threshold);
    }

}
