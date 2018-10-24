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
			
			
			String lastBaseName = "";
			String currentBaseName = "";
			
			Game mergedGame = new Game();
			
			// work each game in given DAT file
			for(int i=0;i<datafile.getGames().size();i++){
				Game current = datafile.getGames().get(i);
				
				// extract basename 1st
				if(i==0){
					// first one only
					lastBaseName = this.getBaseName(current.getName());
					mergedGame = this.cloneCoreGame(current, lastBaseName, mergeFilter);
				}else{
					currentBaseName = this.getBaseName(current.getName());
				}
				
				if(!StringUtil.isEmpty(currentBaseName)){
					if(lastBaseName.equals(currentBaseName)){
					//	System.out.println("match for: '" + lastBaseName + "'");
						if(mergeFilter != null){
							// filter? check if this version is wanted at all
							mergedGame.getRoms().addAll(this.getFilteredRoms(mergeFilter, current.getRoms()));
						}else{
							// no filter? take all
							mergedGame.getRoms().addAll(current.getRoms());
						}
					}else{
						if(!mergedGame.getRoms().isEmpty()){
							
							// check if a previous game is existent with current basename (for unsorted datfiles)
							Game oldMergedGame = result.getGameByName(mergedGame.getName());
							if(oldMergedGame != null) {
								result.getGames().remove(oldMergedGame);
								oldMergedGame.getRoms().addAll(mergedGame.getRoms());
								mergedGame = oldMergedGame;
							} else {
								// seems to be a new game set
								result.getGames().add(mergedGame);
							}
						}
						
						// make current game the new mergedGame
						lastBaseName = currentBaseName;
						mergedGame = this.cloneCoreGame(current, currentBaseName, mergeFilter);
					}
				}
				
			}
			
			// make sure the last game is added to the merged list - if it has any roms...
			if(mergedGame.getRoms().size() > 0 && !result.getGames().contains(mergedGame)){
				result.getGames().add(mergedGame);
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
