package gatsp;
/** Implementation of this interface certifies a class for use as the initial creator of new Chromosones in order to
 * fill a population.
 * @author Stephen Wattam
 */ 
public interface ChromosoneFactory{
    
    /** Creates a new Chromosone and returns it 
     * @return A chromsone as newly created
     */
    public Chromosone newChromosone();

}
