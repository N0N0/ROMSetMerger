package xml.datafile.game;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import lombok.Data;

@Data
public class Sample {

	@Attribute
	private String name;
}
