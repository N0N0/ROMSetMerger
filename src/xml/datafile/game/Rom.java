package xml.datafile.game;

import org.simpleframework.xml.Attribute;

import lombok.Data;

@Data
public class Rom {
	
	@Attribute
	private String name;
	
	@Attribute
	private String size;
	
	@Attribute
	private String crc;
	
	@Attribute(required=false)
	private String sha1;
	
	@Attribute(required=false)
	private String md5;
	
	@Attribute(required=false)
	private String merge;
	
	@Attribute(required=false)
	private String status="good";
	
	@Attribute(required=false)
	private String date;
	
	@Attribute(required=false)
	private String serial;
	
}
