package xml.datafile;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import lombok.Data;
import xml.datafile.game.Archive;
import xml.datafile.game.Biosset;
import xml.datafile.game.Disk;
import xml.datafile.game.Release;
import xml.datafile.game.Rom;
import xml.datafile.game.Sample;

@Data
public class Game {

	@Attribute(required=false)
	private String cdata;
	
	@Attribute
	private String name;
	
	@Attribute(required=false)
	private String sourcefile;
	
	@Attribute(required=false)
	private String isbios = "no";
	
	@Attribute(required=false)
	private String cloneof;
	
	@Attribute(required=false)
	private String romof;
	
	@Attribute(required=false)
	private String sampleof;
	
	@Attribute(required=false)
	private String board;
	
	@Attribute(required=false)
	private String rebuildto;
	
	@Element(required=false)
	private String year;
	
	@Element(required=false)
	private String manufacturer;

	@Element(required=false)
	private Release release;
	
	@Element(required=false)
	private Biosset biosset;
	
	@ElementList(inline=true, required=false, name="rom")
	private List<Rom> roms = new ArrayList<>();
	
	@ElementList(inline=true, required=false)
	private List<Disk> disk = new ArrayList<>();
	
	@ElementList(inline=true, required=false)
	private List<Sample> sample = new ArrayList<>();
	
	@ElementList(required=false)
	private Archive archive;
	
	@Element(required=false)
	private String description;
}
