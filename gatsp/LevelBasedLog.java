package gatsp;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/** An extension to the log class that allows people to choose the level at which they log.
 *
 * @author Stephen Wattam
 */ 
public class LevelBasedLog extends Log{
    
    public abstract class Levels{
	public static final int DEBUG = 4;
	public static final int VERBOSE = 3;
	public static final int STD = 2;
	public static final int WARN = 1;
	public static final int ERROR = 0;
    }

    /** Holds the current verbosity level */
    public static int level = Levels.STD;
    
    /** Logs an item only if the current level of the logger is greater than the importance of the message.
     * @param level An int from the Levels enum that represents the importance of the message.
     * @param message The message to print
     */ 
    public static void out(int level, String message){
	//only print if we have level >= chosen message
	if(LevelBasedLog.level < level)
	    return;

	if(level == Levels.DEBUG)
	    message = getDateTime() + ": " + message;

	//this looks funny, but it's easily extensible.
	switch(level){
	    case Levels.ERROR:
		Log.se(message);
		break;
	    default:
		Log.so(message);
	}
    }

    /** Same as out, but appends a newline. 
     * @see LevelBasedLog#out
     */ 
    public static void outln(int level, String message){
	out(level, message + "\n");
    }
    
    
    //thanks to http://www.java-tips.org/java-se-tips/java.util/how-to-get-current-date-time.html
    /** Returns the date and time as a string.
     * @return The datetime in the format "HH:mm:ss dd/MM/yyyy"
     */
    private static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
