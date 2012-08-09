package gatsp;
import java.util.Random;
/** Performs rank selection on a Population.
 * @author Stephen Wattam
 */
public class RankSelector implements Selector{
    /** Stores the random source for this object */ 
    Random r;
   
    /** Creates a new factory with a given seed.  This can be useful for re-running situations
     * @param seed The seed with which to initialise the source of entropy
     */ 
    public RankSelector(long seed){
	r = new Random(seed);
    }

    /** Creates a RankSelector with a random seed. */
    public RankSelector(){
	r = new Random();
    }

    /** Creates a new RankSelector using the given source of entropy.
     * @param r The source of random numbers to use
     */ 
    public RankSelector(Random r){
	this.r = r;
    }

    /** Internal method that returns random integers up to n.  It favours numbers close to n - it is assumed that a
     * sorted population is sorted from bad to good, indices from 0 to n.
     *
     * P(n) = 1/n(n+1)
     * @param n The number up to which to randomise
     * @return The index to use.
     */
    private int get_weighted_random(int n){
	//return (int) ln( rand(e^n) );
	//java doesn't have enough precision to do this, so...
	//select out of larger sum
	long selection = (long)(r.nextDouble() * (double)(n * (n + 1) / 2));
	int count = n;

	//then count through them all, looping each time
	//System.out.println("Random up to " + n + " selection= " + selection);
	while(selection > 0 ){
	    //System.out.println(selection + " loops remain, at " + count);
	    for(int i=0; i<count; i++)
		selection --;
	    count--;
	}

	return (n - count);
    }

    /** Returns some parents.
     * @see Selector#getParents(int, Population)
     */
    public Chromosone[] getParents(int how_many, Population p){
	Chromosone[] results = new Chromosone[how_many];
	
	for(int i=0; i<how_many; i++)
	    results[i] = p.getChromosone( get_weighted_random( p.size() ) );
	

	return results;
    }


    /** null for now */
    public void accept(Population p, Chromosone[] c){ }
    
}
