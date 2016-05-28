import core.RomSetMerger;
import utils.XmlUtil;
import xml.Datafile;

public class Main {

	public static void main(String args[]){
		
		if(args != null && args.length > 0){
		
			Datafile datafile = null;
			XmlUtil xmlUtil = new XmlUtil();
			
			String path = args[0];
	
			datafile = (Datafile) xmlUtil.getXml(Datafile.class, path);
			
			if(datafile != null && datafile.getHeader() !=null) {
				System.out.println("Got a DAT including " + datafile.getGames().size() + " games.");
				
				Datafile merged = new RomSetMerger().getMergedDat(datafile);
				
				if(merged != null){
					System.out.println("Merged DAT has " + merged.getGames().size() + " games.");
					
					System.out.println("Writing merged DAT as '" + path + "_merged'.");
					System.out.println("Please be patient...");
					xmlUtil.writeXmlToDisc(Datafile.class, merged, path + "_merged");
					System.out.println("Writing to disk done, have fun :)");
					
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
