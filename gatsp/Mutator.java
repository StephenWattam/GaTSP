package gatsp;
/** Implementors of this interface must mutate a given chromosone.
 * @author Stephen Wattam
 */ 
public interface Mutator{
    
    /** Mutates the chromosone given 
     * @param a The chromsone to mutate
     * @return A mutated form of a
     * */
    public Chromosone mutate(Chromosone a);

}
