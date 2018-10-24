package core;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

import core.cli.Parameters;
import utils.XmlUtil;
import xml.Datafile;

public class Main {

	public static void main(String args[]){
		
		System.out.println("ROMSetMerger v0.22\n");
		
		CommandLineParser parser = new DefaultParser();
		try {
			Parameters parameters = new Parameters();
			CommandLine cmd = parser.parse(new Parameters(), args);
			
			if(cmd.hasOption(Parameters.PARAM_HELP)){
				printHelp(parameters);
			}else if(cmd.hasOption(Parameters.PARAM_DATAFILE)){
				String path = cmd.getOptionValue(Parameters.PARAM_DATAFILE);
				
				if(path.length() > 0){
					
					XmlUtil xmlUtil = new XmlUtil();
					Datafile datafile = (Datafile) xmlUtil.getXml(Datafile.class, path);

					
					MergeFilter mergeFilter = null;
					if(hasAnyOption(cmd, Parameters.getFilterParamsAsList())){
						mergeFilter = new MergeFilter();
						mergeFilter.setExcludeAlternativeDumps(cmd.hasOption(Parameters.PARAM_FILTER_ALTERNATE));
						mergeFilter.setExcludeBadDumps(cmd.hasOption(Parameters.PARAM_FILTER_BAD));
						mergeFilter.setExcludeHackedDumps(cmd.hasOption(Parameters.PARAM_FILTER_HACK));
						mergeFilter.setExcludeModifiedDumps(cmd.hasOption(Parameters.PARAM_FILTER_MODIFIED));
						mergeFilter.setExcludeTrainers(cmd.hasOption(Parameters.PARAM_FILTER_TRAINER));
						mergeFilter.setExcludeViruses(cmd.hasOption(Parameters.PARAM_FILTER_VIRUS));
						
						if(cmd.hasOption(Parameters.PARAM_FILTER_REGEX)){
							mergeFilter.setCustomRegex(Pattern.compile(cmd.getOptionValue(Parameters.PARAM_FILTER_REGEX)));
							// TODO: catch compile errors and print correct failure to console out
						}
						
					}
					
					if(datafile != null && datafile.getHeader() !=null) {
						System.out.println("Got a DAT including " + datafile.getGames().size() + " games.");
						
						Datafile merged = new RomSetMerger().getMergedDat(datafile, mergeFilter);
						
						if(merged != null){
							System.out.println("Merged DAT has " + merged.getGames().size() + " games.");
							
							String destinationPath = getDestinationPath(path);
							
							xmlUtil.writeXmlToDisc(Datafile.class, merged, destinationPath);
							System.out.println("Merged DAT saved as '" +destinationPath + "'.");
							
						}else{
							System.out.print("Merging didn't work.");
						}
						
					}else{
						System.out.println("No XML loaded.");
					}
					
				}else{
					printHelp(parameters);
				}
				
			}else{
				printHelp(parameters);
			}
			
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void printHelp(Parameters parameters){
		System.out.println(parameters.getOption(Parameters.PARAM_HELP).getDescription());
	}
	
	private static String getDestinationPath(String sourcePath){
		String destinationPath = sourcePath;
		int extensionBegin = sourcePath.lastIndexOf(".");
		
		if(extensionBegin == sourcePath.length()-4){
			destinationPath = sourcePath.substring(0, extensionBegin );
		}
		
		destinationPath += "_merged.dat";
		
		return destinationPath;
		
	}
	
	private static boolean hasAnyOption(CommandLine cmd, List<String> searchOptions){
		if(searchOptions != null && searchOptions.size() > 0){
			for(Option option : cmd.getOptions()){
				if(searchOptions.contains(option.getLongOpt())){
					return true;
				}
			}
		}
		return false;
	}

	
}
