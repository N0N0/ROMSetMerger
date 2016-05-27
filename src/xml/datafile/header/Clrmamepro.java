package xml.datafile.header;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Clrmamepro {
	
	
	@Element(required=false)
	private String header;
	
	@Element(required=false)
	private String forcemerging = "split";
	
	@Element(required=false)
	private String forcedump = "obsolete";
	
	@Element(required=false)
	private String forcepacking ="zip";
	
}
