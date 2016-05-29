package core;
import utils.XmlUtil;
import xml.Datafile;

public class Main {

	public static void main(String args[]){
		
		MergeFilter mergeFilter = new MergeFilter();
		mergeFilter.setExcludeAlternativeDumps(true);
		mergeFilter.setExcludeBadDumps(true);
		mergeFilter.setExcludeViruses(true);
		mergeFilter.setExcludeTrainers(false);
		
		if(args != null && args.length > 0){
		
			Datafile datafile = null;
			XmlUtil xmlUtil = new XmlUtil();
			
			String path = args[0];
			

			datafile = (Datafile) xmlUtil.getXml(Datafile.class, path);
			
			if(datafile != null && datafile.getHeader() !=null) {
				System.out.println("Got a DAT including " + datafile.getGames().size() + " games.");
				
				Datafile merged = new RomSetMerger().getMergedDat(datafile, mergeFilter);
				
				if(merged != null){
					System.out.println("Merged DAT has " + merged.getGames().size() + " games.");
					
					
					xmlUtil.writeXmlToDisc(Datafile.class, merged, path + "_merged");
					System.out.println("Merged DAT saved as '" + path + "_merged'.");
					
				}else{
					System.out.print("Merging didn't work.");
				}
				
			}else{
				System.out.println("No XML loaded.");
			}
		}else{
			System.out.println("usage: java -jar ROMSetMerger.jar \"C:/DataSets/Commodore Amiga.dat\"");
		}
	}
	
}
