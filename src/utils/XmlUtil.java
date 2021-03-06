package utils;

import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.Format;

import xml.XmlFile;


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
			
			if(!StringUtil.isEmpty(path)){
				Serializer serializer = new Persister();
				File source = new File(path);
					result = serializer.read(result, source);
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean writeXmlToDisc(Class<?> type, XmlFile xml, String path){
		boolean result = false;
		try{
			Serializer serializer = new Persister(new Format(xml.getPrologue()));
			if(!StringUtil.isEmpty(path)){
				File destination = new File(path);
				serializer.write(xml, destination);
				result = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
}
