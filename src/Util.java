import java.util.ArrayList;

/**
 * Utility class offering useful static methods
 * 
 * @author Seth Tinglof
 * @version 1.0
 */
public class Util {
	
	/**
	 * causes thread to sleep for millis milliseconds
	 * @param milis
	 */
	public static void pause(int millis){
		try{
			Thread.sleep(millis);
		}catch(Exception e) {}
	}
	
	/**
	 * @param one
	 * @param two
	 * @return true if one and two have the same values in the same indexes
	 */
	public static boolean integerArraysEqual(Integer[] one, Integer[] two){
		if(one.length != two.length)
			return false;
		for(int i = 0; i < one.length; i++){
			if(!one[i].equals(two[i]))
				return false;
		}
		return true;
	}
	
	/**
	 * @param array
	 * @param integers
	 * @return true if ArrayList array contains Integer[] integers.
	 */
	public static boolean integer_Array_ArrayList_Contains(ArrayList<Integer[]> array, Integer[] integers){
		for(int i = 0; i < array.size(); i++){
			if(Util.integerArraysEqual(array.get(i), integers))
				return true;
		}
			return false;
	}
}
