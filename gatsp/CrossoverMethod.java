package gatsp;
/** Provides an interface through which to perform crossover on Chromosones.
 * @author Stephen Wattam
 */ 
public interface CrossoverMethod{
    
    /** Performs crossover on the two parents, leaving them unchanged and returning a third, offspring item 
     * @param p1 The first parent
     * @param p2 The second parent
     * @return The offspring of p1 and p2
     */
    public Chromosone performCrossover(Chromosone p1, Chromosone p2);

}
