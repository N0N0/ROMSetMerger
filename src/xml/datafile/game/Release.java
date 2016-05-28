package xml.datafile.game;

import org.simpleframework.xml.Attribute;

import lombok.Data;

@Data
public class Release {

	@Attribute
	private String name;
	
	@Attribute
	private String region;
	
	@Attribute(required=false)
	private String language;
	
	@Attribute(required=false)
	private String date;
	
	@Attribute(required=false)
	private String dDefault = "no";
}
