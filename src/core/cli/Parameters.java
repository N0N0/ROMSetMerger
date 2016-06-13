package core.cli;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * Encapsulates CLI Optins
 * @author addi
 *
 */
public class Parameters extends Options{
	private static final long serialVersionUID = 1L;

	public static final String PARAM_HELP = "help";
	
	public static final String PARAM_DATAFILE = "datafile";
	
	public static final String PARAM_FILTER_ALTERNATE = "alternate";
	public static final String PARAM_FILTER_BAD = "bad";
	public static final String PARAM_FILTER_HACK = "hack";
	public static final String PARAM_FILTER_MODIFIED = "modified";
	
	public static final String PARAM_FILTER_TRAINER = "trainer";
	public static final String PARAM_FILTER_VIRUS = "virus";
	
	public static final String PARAM_FILTER_REGEX = "regex";
	
	public Parameters(){
		
		this.addOption("f", PARAM_DATAFILE, true, "Full qualified pathname of the DAT file to cleanup.");
		
		this.addOption("an", PARAM_FILTER_ALTERNATE, false, "ROMs marked as alternative version [a] will be filtered.");
		this.addOption("bn", PARAM_FILTER_BAD, false, "ROMs marked as bad dump [b] will be filtered.");
		this.addOption ("hn", PARAM_FILTER_HACK, false, "ROMs marked as hacked [h] will be filtered.");
		this.addOption("mn", PARAM_FILTER_MODIFIED, false, "ROMs marked as modified [m] will be filtered.");
		this.addOption("tn", PARAM_FILTER_TRAINER, false, "ROMs marked as trained [t] will be filtered.");
		this.addOption("vn", PARAM_FILTER_VIRUS, false, "ROMs marked as virus infected [v] will be filtered.");
		
		this.addOption("rx", PARAM_FILTER_REGEX, true, "Filter ROMs by custom regex pattern (java compatibility required).");
		
		this.addOption("h", PARAM_HELP, false, this.getHelpText());
	}
	
	public static List<String> getFilterParamsAsList(){
		List<String> result = new ArrayList<>();
		
		result.add(PARAM_FILTER_ALTERNATE);
		result.add(PARAM_FILTER_BAD);
		result.add(PARAM_FILTER_HACK);
		result.add(PARAM_FILTER_MODIFIED);
		result.add(PARAM_FILTER_TRAINER);
		result.add(PARAM_FILTER_VIRUS);
		result.add(PARAM_FILTER_REGEX);
		
		return result;
	}
	
	private String getHelpText(){
		StringBuilder sb = new StringBuilder();
		sb.append("Available Parameters:\n");
		
		for(Option o: this.getOptions()){
			sb.append("\t-");
			sb.append(o.getOpt());
			sb.append(", --");
			sb.append(o.getLongOpt());
			sb.append(" = ");
			sb.append(o.getDescription());
			sb.append("\n");
		}
		
		sb.append("\nUsage: java -jar ROMSetMerger.jar -f /home/vintage.DAT -bn");
		return sb.toString();
	}
}
