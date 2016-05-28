import core.RomSetMerger;
import utils.XmlUtil;
import xml.Datafile;

public class Main {

	public static void main(String args[]){
		
		Datafile datafile = null;
		XmlUtil xmlUtil = new XmlUtil();
		
		String path = "K:/zeugs/no-intro-dats/TOSEC - DAT Pack - Complete (2304) (TOSEC-v2016-01-03)/TOSEC/Commodore Amiga - Games - [ADF] (TOSEC-v2015-12-28_CM).dat";

		datafile = (Datafile) xmlUtil.getXml(Datafile.class, path);
		
		if(datafile != null && datafile.getHeader() !=null) {
			System.out.println("Got a DAT including " + datafile.getGames().size() + " games.");
			
			Datafile merged = new RomSetMerger().getMergedDat(datafile);
			
			if(merged != null){
				System.out.println("Merged DAT has " + merged.getGames().size() + " games.");
				
				xmlUtil.writeXmlToDisc(Datafile.class, merged, path + "_merged");
				
			}else{
				System.out.print("Merging didn't work.");
			}
			
		}else{
			System.out.println("No XML loaded.");
		}

	}
	
}
