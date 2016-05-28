package core;

import utils.StringUtil;
import xml.Datafile;
import xml.datafile.Game;

public class RomSetMerger {

	
	public Datafile getMergedDat(Datafile datafile){
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
					mergedGame = this.cloneCoreGame(current, lastBaseName);
					mergedGame.getRoms().addAll(current.getRoms());
				}else{
					currentBaseName = this.getBaseName(current.getName());
				}
				
				if(!StringUtil.isEmpty(currentBaseName)){
					if(lastBaseName.equals(currentBaseName)){
					//	System.out.println("match for: '" + lastBaseName + "'");
						mergedGame.getRoms().addAll(current.getRoms());
					}else{
						// add last game to merged set
						result.getGames().add(mergedGame);
						
						// make current game the new mergedGame
						lastBaseName = currentBaseName;
						mergedGame = this.cloneCoreGame(current, currentBaseName);
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
	
	private String getBaseName(String name){
		if(!StringUtil.isEmpty(name)){
			return name.substring(0, name.indexOf(" ("));
		}else{
			return "";
		}
	}
	
	private Game cloneCoreGame(Game game, String baseName){
		Game result = new Game();
		result.setDescription(baseName);
		result.setName(baseName);
		result.setYear(game.getYear());
		return result;
	}
	
	private Datafile cloneCoreDat(Datafile datafile){
		Datafile result = new Datafile();
		result.setBuild(datafile.getBuild());
		result.setDebug(datafile.getDebug());
		result.setHeader(datafile.getHeader());
		return result;
	}
}
