package gatsp;
/** Implement this to rank chromosones.
 * @author Stephen Wattam
 */ 
public interface FitnessFunction{
    
    /** Evaluate a given chromosone and return a number representing its fitness.
     * @param c The Chromosone to rank
     */ 
    public double evaluate(Chromosone c);

}
