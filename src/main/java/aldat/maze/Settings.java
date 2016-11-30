package aldat.maze;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Settings {
	
	private String collectionTxt;
	private String collectionPbm;
    private boolean showStatus;
	
	public Settings() {
		loadProperties();
	}
	
    public String getCollectionTxtPath() {
		return collectionTxt;
	}

	public String getCollectionPbmPath() {
		return collectionPbm;
	}

	public boolean getShowStatus() { return showStatus; }
	
	private void loadProperties() {
    	Properties properties = new Properties();
    	BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("settings.properties"));
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: settings.poperties not found!");
			e.printStackTrace();
		}
    	try {
			properties.load(reader);
		} catch (IOException e) {
			System.err.println("ERROR: could not load settings.poperties!");
			e.printStackTrace();
		}
    	finally	{
    		try {
		        if (reader != null)
		        	reader.close();	
    		}
		    catch (IOException e) {
		    	System.err.println("ERROR: could not close reader!");
		    }
    	}    	
    	
    	collectionTxt = properties.getProperty("pathTxt", "./collection/txt/");
    	collectionPbm = properties.getProperty("pathPbm", "./collection/pbm/");
        showStatus = Boolean.parseBoolean(properties.getProperty("showStatus", "true"));
    }
}
