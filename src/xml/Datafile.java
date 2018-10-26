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
public class Datafile implements XmlFile{

	private final String prologue = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<!DOCTYPE datafile PUBLIC \"-//Logiqx//DTD ROM Management Datafile//EN\" \"http://www.logiqx.com/Dats/datafile.dtd\">";
	
	@Attribute(required=false)
	private String build;
	
	@Attribute(required=false)
	private String debug="no";
	
	@Element(required=false)
	private Header header;
	
	@ElementList(inline=true,required=false, name="game")
	private List<Game> games = new ArrayList<>();
	
	/**
	 * Searches for game in {@link #games} with matching name. (ignoring case)
	 * @param name
	 * @return
	 */
	private Game getGameByName(String name) {
		if(name != null && name.length() > 0) {
			String lowerCaseSearchName = name.toLowerCase();
			for(Game game : this.games) {
				if(lowerCaseSearchName.equals(game.getName().toLowerCase())) {
					return game;
				}
			}
		}
		return null;
	}
	
	/**
	 * Checks if given baseName is already knwon, if so roms of new game will be added to existing one. If not newGame will be added as is.
	 * @param newGame
	 * @param baseName
	 */
	public void mergeGame(Game newGame, String baseName) {
		Game existingGame = this.getGameByName(baseName);
		if(existingGame != null) {
			existingGame.getRoms().addAll(newGame.getRoms());
		} else {
			this.games.add(newGame);
		}
		
	}
}
