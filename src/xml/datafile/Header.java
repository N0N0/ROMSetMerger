package xml.datafile;

import org.simpleframework.xml.Element;

import lombok.Data;
import xml.datafile.header.Clrmamepro;
import xml.datafile.header.Romcenter;

@Data
public class Header {
	
	@Element
	private String name;
	
	@Element
	private String description;
		
	@Element(required=false)
	private String category;
	
	@Element
	private String version;
	
	@Element(required=false)
	private String date;
	
	@Element
	private String author;
	
	@Element(required=false)
	private String email;
	
	@Element(required=false)
	private String homepage;
	
	@Element(required=false)
	private String url;
	
	@Element(required=false)
	private String comment;
	
	@Element(required=false)
	private Clrmamepro clrmamepro;
	
	@Element(required=false)
	private Romcenter romcenter;
	
	
}
