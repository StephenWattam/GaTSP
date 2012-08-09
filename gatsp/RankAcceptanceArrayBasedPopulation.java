package gatsp;
import java.util.Random;

public class RankAcceptanceArrayBasedPopulation extends ArrayBasedPopulation{

    /** Stores the random source for this object */ 
    Random r;
   
    /** Creates a new factory with a given seed.  This can be useful for re-running situations
     * @param seed The seed with which to initialise the source of entropy
     */ 
    public RankAcceptanceArrayBasedPopulation(long seed, int num_entries, ChromosoneFactory cf){
	super(num_entries, cf);
	r = new Random(seed);
    }

    /** Creates a RankAcceptanceArrayBasedPopulation with a random seed. */
    public RankAcceptanceArrayBasedPopulation(int num_entries, ChromosoneFactory cf){
	super(num_entries, cf);
	r = new Random();
    }

    /** Creates a new RankAcceptanceArrayBasedPopulation using the given source of entropy.
     * @param r The source of random numbers to use
     */ 
    public RankAcceptanceArrayBasedPopulation(Random r, int num_entries, ChromosoneFactory cf){
	super(num_entries, cf);
	this.r = r;
    }


    /** Creates a new factory with a given seed.  This can be useful for re-running situations
     * @param seed The seed with which to initialise the source of entropy
     */ 
    public RankAcceptanceArrayBasedPopulation(long seed, Chromosone[] csones){
	super(csones);
	r = new Random(seed);
    }

    /** Creates a RankAcceptanceArrayBasedPopulation with a random seed. */
    public RankAcceptanceArrayBasedPopulation(Chromosone[] csones){
	super(csones);
	r = new Random();
    }

    /** Creates a new RankAcceptanceArrayBasedPopulation using the given source of entropy.
     * @param r The source of random numbers to use
     */ 
    public RankAcceptanceArrayBasedPopulation(Random r, Chromosone[] csones){
	super(csones);
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

    
    /** Accept some chromosones into the population.
     * @see Population#accept(Chromosone[])
     */
    public int accept(Chromosone[] c){
	//ensure worst are at end
	sort();
	int count = 0;
	//replace worst with chromosone
	for(int i=0; i<c.length; i++){
	    //System.out.println("Replacing csones[" + i + "]\t val: " + (int)this.csones[i].score);
		//System.out.print("*");
		this.csones[ get_weighted_random(this.csones.length - 1) ] = c[i];
		count ++;
	    //}else{
		//System.out.print("*");
	}

	

	is_sorted = false;  
	is_scored = false;
	return count;
    }
    


}
