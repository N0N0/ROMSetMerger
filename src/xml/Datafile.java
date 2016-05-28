package xml;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import lombok.Data;
import xml.datafile.Game;
import xml.datafile.Header;

@Data
@Root
public class Datafile {

	@Attribute(required=false)
	private String build;
	
	@Attribute(required=false)
	private String debug="no";
	
	@Element(required=false)
	private Header header;
	
	@ElementList(inline=true,required=false, name="game")
	private List<Game> games = new ArrayList<>();
}
