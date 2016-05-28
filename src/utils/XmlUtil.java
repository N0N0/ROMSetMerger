package utils;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 * Encapsulates methods that do XML stuff :)
 * @author addi
 *
 */
public class XmlUtil {

	public Object getXml(Class<?> type, String path) {
		Object result = null;
		try {
			result = type.newInstance();
			
			if(path != null && path.length() > 0){
				Serializer serializer = new Persister();
				File source = new File(path);
	
					result = serializer.read(result, source);
					
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
