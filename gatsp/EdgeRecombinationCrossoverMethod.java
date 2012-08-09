//Let K be the empty list
//Let N be the first node of a random parent.

//While Length(K) < Length(Parent):
    //K := K, N   (append N to K)
    //Remove N from all neighbor lists

    //If N's neighbor list is non-empty
       //then let N* be the neighbor of N with the fewest neighbors in its list (or a random one, should there be multiple)
       //else let N* be a randomly chosen node that is not in K

    //N := N*

    
package gatsp;
import java.util.Vector;
import java.util.Random;

public class EdgeRecombinationCrossoverMethod implements CrossoverMethod{

    /** Stores the random source for this object */ 
    Random r;

    Landscape l;
   
    /** Creates a new factory with a given seed.  This can be useful for re-running situations
     * @param seed The seed with which to initialise the source of entropy
     */ 
    public EdgeRecombinationCrossoverMethod(long seed, Landscape l){
	r = new Random(seed);
	this.l = l;
    }

    /** Creates a EdgeRecombinationCrossoverMethod with a random seed. */
    public EdgeRecombinationCrossoverMethod(Landscape l){
	r = new Random();
	this.l = l;
    }

    /** Creates a new EdgeRecombinationCrossoverMethod using the given source of entropy.
     * @param r The source of random numbers to use
     */ 
    public EdgeRecombinationCrossoverMethod(Random r, Landscape l){
	this.r = r;
	this.l = l;
    }

    //produce tuples from a list of ids
    private int[][] buildTuples(Chromosone c){
	int[][] adjtable = new int[c.genes.length][2];
	
	//first one
	adjtable[c.genes[0]][0] = c.genes[c.genes.length - 1];
	adjtable[c.genes[0]][1] = c.genes[1];

	//middles!
	for(int i=1; i<c.genes.length - 1; i++){
	    adjtable[c.genes[i]][0] = c.genes[i - 1];
	    adjtable[c.genes[i]][1] = c.genes[i + 1];
	}

	//last one	
	adjtable[c.genes[ c.genes.length - 1]][0] = c.genes[c.genes.length - 2];;
	adjtable[c.genes[ c.genes.length - 1]][1] = c.genes[0];

	return adjtable;
    }

    //remove al references to an int from an array
    private int[] removeFromArray(int[] list, int num){
	int[] result_long = new int[list.length];
	int c = 0;
	for(int i=0; i<list.length; i++){
	    if(list[i] != num)
		result_long[c++] = list[i];
	}
    
	//copy into new thingy
	//System.out.println("c = " + c);
	int[] result = new int[c];
	System.arraycopy(result_long, 0, result, 0, c);
	return result;
    }

    //remove all references to an int fron a table
    private int[][] removeRefTo(int[][] list, int num){
	list[num] = new int[0];
	for(int i=0; i<list.length; i++)
	    list[i] = removeFromArray(list[i], num);

	return list;
    }



    //debug output
    private void printthething(int[][] list){
	System.out.print("\n");
	for(int i=0; i<list.length; i++){
	    System.out.print("\n " + i + ":");
	    for(int j=0; j<list[i].length; j++){
		System.out.print("," + list[i][j]);
	    }
	}
    }

    //is an int in a list? true if so, false if not
    private boolean isIn(int[] list, int num){
	for(int i=0; i<list.length; i++){
	    if(list[i] == num)
		return true;
	}
	return false;
    }

    //merges two lists, checking for dupes
    private int[] mergeLists( int[] l1, int[] l2){
	int[] result_long = new int[l1.length + l2.length];
	//System.out.println("l1.length=" + l1.length + "  l2.length=" + l2.length + "  rlong.length=" + result_long.length);
	System.arraycopy( l1, 0, result_long, 0, l1.length);
	System.arraycopy( l2, 0, result_long, l1.length, l2.length);
	//int c = l1.length;
	//for(int i=0; i<l2.length; i++){
	    //if(!isIn(result_long, l2[i]))
		//result_long[c++] = l2[i];
	//}
	
	//int[] result = new int[c];
	//System.arraycopy( result_long, 0, result, 0, c);
	
	return result_long;
    }

    //merges two tables together to form one larger adjacency table.  essentially ensures that no duplicates exist
    private int[][] mergeTables(int[][] t1, int[][] t2){
	int[][] result = new int[t1.length][];
	for(int i=0; i<t1.length; i++)
	    result[i] = mergeLists( t1[i], t2[i] );
	return result;
    }

    private int getShortestIndex(int city, int[] indices ){
	double shortest_distance = l.getDistance(city, indices[0]);
	int shortest_index = 0;
	double dcache = 0;


	for(int i=0; i<indices.length; i++){
	    dcache = l.getDistance(city, indices[i]);
	    if(shortest_distance > dcache){
		shortest_distance = dcache;
		shortest_index = i;
	    }
	}
	return shortest_index;
    }

    public Chromosone performCrossover(Chromosone p1, Chromosone p2){
	
        /*
	 *System.out.print("\n");
	 *System.out.print("p1: ");
	 *for(int i=0; i<p2.genes.length; i++)
	 *    System.out.print(" " + p1.genes[i]);
	 *System.out.print("\n");
	 *
	 *System.out.print("p2: ");
	 *for(int i=0; i<p2.genes.length; i++)
	 *    System.out.print(" " + p2.genes[i]);
         */
	
	
	int[][] p1table = buildTuples(p1);
	int[][] p2table = buildTuples(p2);
	int[][] mergetable = mergeTables(p1table, p2table);

	//printthething(mergetable);
	


	int[] result = new int[p1.genes.length];
	int current = r.nextInt(mergetable.length);
	result[0] = current;
	for(int i=1; i<result.length; i++){
	    
	    //System.out.print(" " + mergetable[current].length);

	    result[i] = mergetable[current][
		//getShortestIndex(result[i-1], mergetable[result[i-1]])	
		mergetable[current].length - 1
		//shortest_index
		];//0];

	    //System.out.print(" " + result[i]);
	    //printthething(mergetable);
	    mergetable = removeRefTo( mergetable, current);
	    current = result[i];
	}
        /*
	 *System.out.print("\n");
	 *System.out.print("produced: ");
	 *for(int i=0; i<result.length; i++)
	 *    System.out.print(" " + result[i]);
	 *System.out.print("\n");
         */


	
	return new Chromosone( result );
    }
}
