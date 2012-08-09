package gatsp;
/** Stores many Chromosones along with their rankings.  This interface regulates access to its implementors.
 * @author Stephen Wattam
 */ 
public interface Population{
   
    //Population(ChromosoneFactory c_src);

    /** Returns the size of the population 
     * @return The number of chromosones in the population
     */
    public int size();

    /** Returns a chromosone from the given index.
     * @param x The index of the chromsone to return
     * @return A chromosone from the index given
     */ 
    public Chromosone getChromosone(int x) throws IndexOutOfBoundsException;

    /** Ensures that every chromosone in the populations is scored.
     * @param ff The fitness function by which the chromosones should be scored.
     */
    public void rebuildScores(FitnessFunction ff);
    
    public void rebuildScores_fast(FitnessFunction ff);
    
    /** Sorts the chromosones by their fitness. */
    public void sort();
	
	
    /** Returns the minimum, maximum and average fitness of the population.
     * @return [min, avg, max] values of fitness across the population
     */
    public double[] getStats();

    /** Accept a set of chromosones into the population.  The population may choose to do anything with these
     * chromosones, though most will replace the crummier members of the current population with the provided
     * chromosones.
     * @param c An array of Chromosones
     */
    public int accept( Chromosone[] c);


}
