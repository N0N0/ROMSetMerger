package xml.datafile.game;

import org.simpleframework.xml.Attribute;

import lombok.Data;

@Data
public class Biosset {

	@Attribute
	private String name;
	
	@Attribute
	private String description;
	
	@Attribute(required=false, name="default")
	private String dDefault="yes";
		
}
