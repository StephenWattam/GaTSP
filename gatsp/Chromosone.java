package gatsp;
/** Represents a Chromosone to the system.  Each one holds enough data to resconstruct a hamiltonian path across the
 * landscape.
 * @author Stephen Wattam
 */ 
public class Chromosone implements Comparable, Cloneable{
   
    /** Holds all symbols */
    public int[] genes;

    /** Holds the score this chromosone has been given by a fitness function.  Initially this takes the value -1 to
     * indicate an unranked chromosone. */
    public double score = -1;

   /** Creates a new Chromosone with support for a given number of symbols.
    * @param num_sumbols The number of symbols to store.  This should be equal to the number of cities in the landscape.
    */ 
    public Chromosone(int num_sumbols){
	this.genes = new int[num_sumbols];
    }

    /** Create a new Chromsone from a set of symbols.
     * @param symbols An integer array with the indices of cities in it, in order.  This array has to be the same length
     * as the number of cities in the landscape it's to be used with, and must represent a hamiltonian path.
     */
    public Chromosone(int[] symbols){
	this.genes = symbols;
    }

    /** returns less than 0 if this object is smaller than c, more than 0 if it's larger, or 0 if they
     * are of equal standing. */
    public int compareTo(Object o){
	Chromosone c = (Chromosone)o;
	if(this.score > c.score)
	    return 1;
	else if(this.score == c.score)
	    return 0;
	return -1;
    }

    public String toString(){
	String op = "[" + genes[0];
	for(int i=1; i<genes.length; i++)
	    op += "," + genes[i];
	return super.toString() + " " + op + "]";
    }

    public Object clone(){
	try{
	    return super.clone();
	}catch( CloneNotSupportedException e ){
	    return null;
	}
    } 
}
