package gatsp;
/** An interface for all selection methods to use.  Implementors will be expected to select items from a population
 * using a method that favours the fittest chromosones.
 * @author Stephen Wattam
 */ 
public interface Selector{
   
    /** Returns some parents as selected from the population 
     * @param how_many The number of chromosones to select for breeding.  I expect this to be '2' a lot ;-)
     * @param p The population from which to select
     * @return A lsit of chromosones selected from the Population, p
     * @see Population
     */
    public Chromosone[] getParents(int how_many, Population p);

    /** Accepts a list of chromosones into the population given 
     *	@param p The population into which to place the chromosones
     *	@param c A list of chrosmones to place into the population.
     *	@see Population
     */
    public void accept(Population p, Chromosone[] c);

}
