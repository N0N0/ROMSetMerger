package xml.datafile.header;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.Data;

@Data
public class Romcenter {

	@Element(required=false)
	private String plugin;
	
	@Element(required=false)
	private String rommode = "split";
	
	@Element(required=false)
	private String biosmode = "Split";
	
	@Element(required=false)
	private String samplemode = "merged";
	
	@Element(required=false)
	private String lockrommode ="no";
	
	@Element(required=false)
	private String lockbiosmode ="no";
	
	@Element(required=false)
	private String locksamplemode ="no";
	
}
