package core;

import java.util.ArrayList;
import java.util.List;

import utils.StringUtil;
import xml.Datafile;
import xml.datafile.Game;
import xml.datafile.game.Rom;

public class RomSetMerger {

	
	public Datafile getMergedDat(Datafile datafile, MergeFilter mergeFilter){
		Datafile result = null;
		
		if(datafile != null && datafile.getGames().size() > 0){

			result = this.cloneCoreDat(datafile);
			
			for(int i=0;i<datafile.getGames().size();i++){
				Game current = datafile.getGames().get(i);
				String baseName = this.getBaseName(current.getName());
				Game mergedGame = this.cloneCoreGame(current, baseName, mergeFilter);
				result.mergeGame(mergedGame, baseName);
			}
		}

		return result;
	}

	/**
	 * @param mergeFilter
	 * @param roms list of roms that wont match mergeFilter criteria
	 * @return
	 */
	private List<Rom> getFilteredRoms(MergeFilter mergeFilter, List<Rom> roms){
		List<Rom> result = new ArrayList<>();
		
		for(Rom rom : roms){
			if(!mergeFilter.matchesFilter(rom.getName())){
				result.add(rom);
			}
		}
		return result;
	}
	
	/**
	 * @param name
	 * @return basename (e.g. name of given game up till 1st match of string: " (" (aprox year of release) or " Rev" (Revision 0...N) - whatever's first in namestring
	 */
	private String getBaseName(String name){
		if(!StringUtil.isEmpty(name)){
			int revPartIndex = name.indexOf(" Rev ");
			int detailPartIndex = name.indexOf(" (");
			if(revPartIndex >= 0 && revPartIndex < detailPartIndex) {
				return name.substring(0, revPartIndex);
			} else if(detailPartIndex > -1){
				return name.substring(0, detailPartIndex);
			} else {
				return name;
			}
		}
		return "";
	}
	
	/**
	 * copies basic data of given game into new game instance
	 * @param game
	 * @param baseName
	 * @return
	 */
	private Game cloneCoreGame(Game game, String baseName, MergeFilter mergeFilter){
		Game result = new Game();
		result.setDescription(baseName);
		result.setName(baseName);
		result.setYear(game.getYear());
		if(mergeFilter != null){
			result.getRoms().addAll(this.getFilteredRoms(mergeFilter, game.getRoms()));
		}else{
			result.getRoms().addAll(game.getRoms());
		}
		return result;
	}
	
	/**
	 * copies basic data of given DAT into new DAT instance
	 * @param datafile
	 * @return
	 */
	private Datafile cloneCoreDat(Datafile datafile){
		Datafile result = new Datafile();
		result.setBuild(datafile.getBuild());
		result.setDebug(datafile.getDebug());
		result.setHeader(datafile.getHeader());
		return result;
	}
}
