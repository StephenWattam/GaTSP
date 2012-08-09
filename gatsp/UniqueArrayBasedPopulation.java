package gatsp;

public class UniqueArrayBasedPopulation extends ArrayBasedPopulation implements Population{
    
    public UniqueArrayBasedPopulation(int num_entries, ChromosoneFactory cf){
	super(num_entries, cf);
    }

    public UniqueArrayBasedPopulation(Chromosone[] csones){
	super(csones);
    }


    private boolean verify_unique(Chromosone c){
	boolean same;
	
	for(int i=0; i<csones.length; i++){
	    
	    same = true;
	    
	    for(int j=0; j<c.genes.length; j++){
		if(csones[i].genes[j] != c.genes[j]){
		    same = false;
		    break;
		}
	    }
	    

	    if(same == true)
		return false;
	    

	    //System.out.println( csones[i] + "\n" + c + "\n\n");
	}
	return true;
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
	    if(verify_unique(c[i])){
		//System.out.print("*");
		this.csones[ i ] = c[i];
		count ++;
	    //}else{
		//System.out.print("*");
	    }
	}

	

	is_sorted = false;  
	is_scored = false;
	return count;
    }

}
