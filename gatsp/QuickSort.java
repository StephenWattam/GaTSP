package gatsp;
/** Quick sorts double arrays.  This was going to be used to sort parallel arrays of chromosones and scores, but now is
 * rather redundant.
 * @author Stephen Wattam
 */
public class QuickSort{

    /** Sorts a double array.*/
    public static double[] qsort(double[] array){
	if(array.length <= 1)
	    return array;

	//Apologies to the Gods of memory usage.  This is easier than expanding arrays
	double[] less	    = new double[array.length];
	double[] greater    = new double[array.length];
	int less_counter    = -1;
	int greater_counter = less_counter;
	
	double x = 0.0;

	for(int i=1; i<array.length; i++){
	    x = array[i];
	    
	    //array[0] is pivot
	    if( x <= array[0] )
		less[++less_counter] = x;
	    else
		greater[++greater_counter] = x;
	}

	return concat(qsort( crop(less, less_counter) ), crop( array, 1 ), qsort( crop(greater, greater_counter) ));
    }

    /** returns only the first n items of a double array.
     * @param arr The array to select items from.
     * @param number_to_take The numbner of items to select
     */ 
    private static double[] crop(double[] arr, int number_to_take){
	//save ourselves many errors
	if(number_to_take <= 0)
	    return new double[0];
	
	//define destination array
	double[] result = new double[number_to_take];
	//copy
	System.arraycopy(arr, 0, result, 0, number_to_take);
	return result;
    }

    public static double[] concat(double[] ... arrays){
	int c = 0;
	//use c to count total items
	for(int i=0; i<arrays.length; i++)
	    c += arrays[i].length;
	double[] result = new double[c];
	
	//now re-use c to count total items copied.
	//I'm aware this re-use is not 'good', but it is speedy
	c = 0;	

	for(int i=0; i<arrays.length; i++){
	    System.arraycopy(arrays[i], 0, result, c, arrays[i].length);
	    c += arrays[i].length;
	}

	return result;
    }
}
