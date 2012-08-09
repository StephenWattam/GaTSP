package gatsp;
import java.util.Vector;
import java.util.Random;
/** Performs single point crossover.
 * @author Stephen Wattam
 */
public class SinglePointCrossoverMethod implements CrossoverMethod{

    /** Stores the random source for this object */ 
    Random r;
   
    /** Creates a new factory with a given seed.  This can be useful for re-running situations
     * @param seed The seed with which to initialise the source of entropy
     */ 
    public SinglePointCrossoverMethod(long seed){
	r = new Random(seed);
    }

    /** Creates a SinglePointCrossoverMethod with a random seed. */
    public SinglePointCrossoverMethod(){
	r = new Random();
    }

    /** Creates a new SinglePointCrossoverMethod using the given source of entropy.
     * @param r The source of random numbers to use
     */ 
    public SinglePointCrossoverMethod(Random r){
	this.r = r;
    }

    /** A helper method that returns true if the item is found in a list.
     * @param list An array of ints.
     * @param item The int to seek
     * @return True if item is in list, or false if not
     */
    private boolean isInList(int[] list, int item){
	for(int i=0; i<list.length; i++)
	    if( list[i] == item )
		return true;
	return false;
    }

    /** Outputs a list in a nice, easy to read format. */
    /*
     *private void printList(int[] list){
     *    System.out.print(" List: ");
     *    for(int i=0; i<list.length; i++)
     *        System.out.print(list[i] + ", ");
     *    System.out.println();
     *}
     */

    /** Performs single-point crossover by copying from parent a up to a pivot, then taking only the new items from b.
     * @see CrossoverMethod#performCrossover(Chromosone, Chromosone)
     */    
    public Chromosone performCrossover(Chromosone p1, Chromosone p2){
	int[] result	= new int[ p1.genes.length ];
	int pivot	= r.nextInt( p1.genes.length );


	//copy from p1 up to pivot
	for(int i=0; i<pivot; i++)
	    result[i] = p1.genes[i];

	int count = pivot;
	for(int i=0;i<p2.genes.length; i++)
	    if(	!isInList(result, p2.genes[i]) )
		result[count++] = p2.genes[i];

	return new Chromosone( result);	
    }
    
}
