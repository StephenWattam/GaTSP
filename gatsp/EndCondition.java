package gatsp;

/** An interface to be implemented in order to define convergeance conditions.
 * @author Stephen Wattam
 */
public interface EndCondition{
    
    /** Should return true when the end condition is met.
     * @param p The population of chromosones.
     * @return false if another iteration should continue, or true if the end condition is met 
     */
    public boolean endYet( Population p);
}
