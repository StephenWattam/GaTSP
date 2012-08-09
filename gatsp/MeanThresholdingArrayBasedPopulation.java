package gatsp;


public class MeanThresholdingArrayBasedPopulation extends ArrayBasedPopulation implements Population{

    public FitnessFunction ff;

    /** Creates a new ArrayBasedPopulation from a number of entries and a factory.
     * @param num_entries The number of chromosones to hold
     * @param cf The factory from which to get them.
     */
    public MeanThresholdingArrayBasedPopulation(int num_entries, ChromosoneFactory cf, FitnessFunction ff){
	super(num_entries, cf);
	this.ff = ff;
    }
  
   /** Creates a new ArrayBasedPopulation from an existing list of Chromosones.
    * @param c The list of chromosones.  The size of the population will be fixed from this.
    */ 
    public MeanThresholdingArrayBasedPopulation(Chromosone[] c, FitnessFunction ff){
	super(c);
	this.ff = ff;
    }

    public int accept(Chromosone[] c){
	double score;
	double mean = (super.getStats()[1] + super.getStats()[0]) / 2;

	
	Chromosone[] accept_list = new Chromosone[c.length];
	int count = 0;

	for(int i=0; i<c.length; i++){
	    score = ff.evaluate(c[i]);
	    //System.out.println("Score=" + score + "  mean=" + mean);
	    if(score > mean)
		accept_list[count++] = c[i];
	}
	
	Chromosone[] short_list = new Chromosone[count];
	System.arraycopy(accept_list, 0, short_list, 0, count);
	return super.accept(short_list);
    }
}
