package aldat.maze;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Settings {
	
	private String collectionTxt;
	private String collectionPbm;
	
	public Settings() {
		loadProperties();
	}
	
    public String getCollectionTxtPath() {
		return collectionTxt;
	}

	public String getCollectionPbmPath() {
		return collectionPbm;
	}	
	
	private void loadProperties() {
    	Properties properties = new Properties();
    	BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("settings.properties"));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: settings.poperties not found!");
			e.printStackTrace();
		}
    	try {
			properties.load(reader);
		} catch (IOException e) {
			System.out.println("ERROR: could not load settings.poperties!");
			e.printStackTrace();
		}
    	finally	{
    		try {
		        if (reader != null)
		        	reader.close();	
    		}
		    catch (IOException e) {
		    	System.out.println("ERROR: could not close reader!");
		    }
    	}    	
    	
    	collectionTxt = properties.getProperty("pathTxt");
    	collectionPbm = properties.getProperty("pathPbm");    	
    }
}
