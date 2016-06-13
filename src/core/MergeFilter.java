package core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Data;
import utils.StringUtil;

/**
 * UtilityClass to store status of filters for merge process (eg. exclude [b] tags)
 * @author addi
 */
@Data
public class MergeFilter {

	private boolean excludeBadDumps = false;
	private boolean excludeAlternativeDumps = false;
	private boolean excludeHackedDumps = false;
	private boolean excludeModifiedDumps = false;
	private boolean excludeTrainers = false;
	private boolean excludeViruses = false;
	
	private Pattern customRegex = null;
	
	/**
	 * @param name (filename) to check against the filter
	 * @return true if filename is to be filtered, false if not
	 */
	public boolean matchesFilter(String name){
		boolean result = false;
		String pattern = this.getPattern();
		if(pattern.length() > 0){
			Pattern p = Pattern.compile(pattern);
			Matcher matcher =p.matcher(name);
			result = matcher.find();
		}
		return result;
	}
	
	/**
	 * @return stringform of regexpattern build by status of filter members
	 */
	private String getPattern(){
		String result = "";
		String orString = "";
		
		if(this.excludeBadDumps){
			result += orString + "((\\[b\\])|(\\[b\\d?\\d?\\])|(\\[b\\d?\\d?\\s.*\\]))";
			orString = "|";
		}
		
		if(this.excludeAlternativeDumps){
			result += orString + "((\\[a\\])|(\\[a\\d?\\d?\\])|(\\[a\\d?\\d?\\s.*\\]))";
			orString = "|";
		}
		
		if(this.excludeHackedDumps){
			result += orString + "((\\[h\\])|(\\[h\\d?\\d?\\])|(\\[h\\d?\\d?\\s.*\\]))";
			orString = "|";
		}
		
		if(this.excludeModifiedDumps){
			result += orString + "((\\[m\\])|(\\[m\\d?\\d?\\])|(\\[m\\d?\\d?\\s.*\\]))";
			orString = "|";
		}
		
		if(this.excludeTrainers){
			result += orString + "((\\[t\\])|(\\[t\\+?\\d?\\d?\\])|(\\[t\\+?\\d?\\d?\\s.*\\]))";
			orString = "|";
		}
		
		if(this.excludeViruses){
			result += orString + "((\\[v\\])|(\\[v\\d?\\d?\\])|(\\[v\\d?\\d?\\s.*\\]))";
			orString = "|";
		}
		
		if(customRegex != null){
			result += orString + "(" + this.customRegex.pattern() + ")";
			orString = "|";
		}
		
		return result;
	}
}
