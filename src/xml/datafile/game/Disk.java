package xml.datafile.game;

import org.simpleframework.xml.Attribute;

import lombok.Data;

@Data
public class Disk {

	@Attribute
	private String name;
	
	@Attribute(required=false)
	private String sha1;
	
	@Attribute(required=false)
	private String md5;
	
	@Attribute(required=false)
	private String merge;
	
	@Attribute(required=false)
	private String status="good";
}
