package utils;

/**
 * Encapsulates methods for Stringoperations
 * @author addi
 *
 */
public class StringUtil {

	public static boolean isEmpty(String val){
		return val == null || val.trim().length() == 0;
	}
	
}
