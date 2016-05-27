import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import xml.Datafile;

public class Main {

	public static void main(String args[]){
		Datafile datafile = getDatafile("K:/zeugs/no-intro-dats/TOSEC - DAT Pack - Complete (2304) (TOSEC-v2016-01-03)/TOSEC/Commodore Amiga - Demos - Various - [EXE] (TOSEC-v2013-10-02_CM).dat");
		
		if(datafile != null && datafile.getHeader() !=null) {
			System.out.println(datafile.getHeader().getName());
		}
	}
	
	
	private static Datafile getDatafile(String path){
		Datafile result = null;
		
		if(path != null && path.length() > 0){
			Serializer serializer = new Persister();
			File source = new File(path);
			try {
				result = serializer.read(Datafile.class, source);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
}
