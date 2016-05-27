package xml.datafile.game;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import lombok.Data;

@Data
public class Archive {
	 @Attribute
	 private String name;
}
