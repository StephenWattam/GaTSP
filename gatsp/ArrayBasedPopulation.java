package gatsp;
/** A population manager that uses static-sized arrays for all management.
 * @author Stephen Wattam
 */ 
public class ArrayBasedPopulation implements Population{
    
    /** Stores all chromosones, ranked in order from 0 to n, good to bad respectively */ 
    Chromosone[] csones;

    /** is checked and reset to prevent sorting an already-sorted population */
    boolean is_sorted = false;
    /** is checked and reset to prevent scoring an already-scores population */
    boolean is_scored = false;

    /** Creates a new ArrayBasedPopulation from a number of entries and a factory.
     * @param num_entries The number of chromosones to hold
     * @param cf The factory from which to get them.
     */
    public ArrayBasedPopulation(int num_entries, ChromosoneFactory cf){
	this.csones = new Chromosone[num_entries];
	
	for(int i=0; i<num_entries; i++)
	    csones[i] = cf.newChromosone(); 
	
    }
  
   /** Creates a new ArrayBasedPopulation from an existing list of Chromosones.
    * @param c The list of chromosones.  The size of the population will be fixed from this.
    */ 
    public ArrayBasedPopulation(Chromosone[] c){
	this.csones = c;
    }		
  
    /** Returns the size of the population 
     * @see Population#size
     */
    public int size(){
	return this.csones.length;
    }

    /** Returns a chromosone from the given index.
     * @see Population#getChromosone(int)
     */ 
    public Chromosone getChromosone(int x) throws IndexOutOfBoundsException{
	try{
	    return this.csones[x];
	}catch(NullPointerException NPe){
	    LevelBasedLog.outln(LevelBasedLog.Levels.ERROR, 
		    "Attempt to access index " + x + " of " + this.csones.length + " chromosones in population '" + this + "'.");
	    throw new IndexOutOfBoundsException();
	}
    }

    /** Rebuilds all scores.
     * @see Population#rebuildScores(FitnessFunction)
     */
    public void rebuildScores(FitnessFunction ff){
	if(!is_scored){
	    for(Chromosone c: this.csones)
		c.score = ff.evaluate(c);
	    
	    is_scored = true;
	    is_sorted = false;
	}
    }

    /** Rebuilds scores, ignoring already-scored chromosones.
     * @param ff The fitness function to use.  If this differs from the last run of rebuildScores there could exist two
     * scoring systems, producing unpredictable results.
     */
    public void rebuildScores_fast(FitnessFunction ff){
	if(!is_scored){
	    for(Chromosone c: this.csones){
		// this makes sure we only test new chromosones.
		// This obviously fucks up if we use different fitness functions throughout the life of the population
		// This is in for TESTING PURPOSES ONLY, it ought to have its own API call for speed
		if(c.score < 0)
		    c.score = ff.evaluate(c);
	    }
	    
	    is_scored = true;
	    is_sorted = false;
	}
    }
   
    /** Ensure the list is sorted.  A caching system ensures that no unnecessary sorts occur. */
    public void sort(){
	if(!is_sorted){
	    java.util.Arrays.sort(this.csones);	
	    is_sorted = true;
	}
    }

    /** Returns max, average and min fitness values.  Due to the ordering of the population the best chromosone may
     * always be found at index n, where n is the size of the population, the worst at 0.
     * @return A double array where index 0 is the minimum fitness, index 1 is the mean fitness of the population, and
     * index 2 is the maximum fitness.
     */
    public double[] getStats(){
	sort();
	double total = 0;
	double min = this.csones[0].score;
	double max = this.csones[this.csones.length - 1].score;

	for(Chromosone c: this.csones)
	    total += c.score;
	

	total /= (double)this.csones.length;
	double[] min_avg_max = {min, total, max};
	return min_avg_max;
    }

    /** Accept some chromosones into the population.
     * @see Population#accept(Chromosone[])
     */
    public int accept(Chromosone[] c){
	//ensure worst are at end
	sort();
	//replace worst with chromosone
	for(int i=0; i< Math.min(this.csones.length, c.length); i++){
	    //System.out.println("Replacing csones[" + i + "]\t val: " + (int)this.csones[i].score);
	    this.csones[ i ] = c[i];
	}

	is_sorted = false;  
	is_scored = false;
	return Math.min(this.csones.length, c.length);
    }
}
