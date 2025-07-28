package propertiesFileUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetdatafromPropFile {
	
	public String GetDataFromPropFile(String key) throws IOException{
		FileInputStream fis = new FileInputStream("./src/main/resources/ConfigData/commondata.properties");
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);
		
		
	}

}
