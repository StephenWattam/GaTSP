package gatsp;
import java.io.*;
/** Logs everything!
 * @author Stephen Wattam
 */ 
public class Log{
   
   /** Holds the stdout printstream */ 
    private static PrintStream stdout = System.out;
    /** Holds the error-y printstream */
    private static PrintStream stderr = System.err;

    /** Sets the stdout stream 
     * @param ps The PrintStream to use as stdout
     */ 
    public static void setStdOut(PrintStream ps){
	Log.stdout = ps;
    }

    /** Sets the stderr stream 
     * @param ps The PrintStream to use as stderr
     */ 
    public static void setStdErr(PrintStream ps){
	Log.stderr = ps;
    }

    /** Prints a given string to stdout.
     * @param s The string to print
     */ 
    public static void so(String s){
	Log.stdout.print(s);
    }

    /** Prints a given string to stdout, with newline.
     * @param s The string to print
     */ 
    public static void soln(String s){
	Log.stdout.println(s);
    }

    /** Prints a given string to stderr.
     * @param s The string to print
     */ 
    public static void se(String s){
	Log.stderr.print(s);
    }

    /** Prints a given string to stderr, with newline.
     * @param s The string to print
     */ 
    public static void seln(String s){
	Log.stderr.println(s);
    }
}
