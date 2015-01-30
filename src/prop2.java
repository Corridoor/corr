import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

public class prop2 { //rename this when needed if go this route (the file not the class) but not necessarily needed
    // Declare statics to be used throughout the class

    public static String oauthKey;
    public static String foodList;
    public static String otherList;
    static Properties prop = new Properties();
    
    /**
     * Handles the decision to write or save 
     * @param inOrOut
     * @param theProperties
     * @return 
     */
    public boolean loadProp(String inOrOut, String[] theProperties) {
        // This is where we will be loading the properties
        switch (inOrOut) {
                case "in":
                    return propIn(theProperties);
                case "out":
                    return propOut(theProperties);
                default:
                    return false;
        }

    }

    private boolean propIn(String[] theProperties) {
        if (theProperties.length == 1) {
            String[] splitProperty;
            splitProperty = theProperties[0].split("=");
        }
        return true; //for now
    }
    
    private boolean propOut(String[] theProperties) {
        return true; // for now
    }
    public static void getAllProperties() {
        InputStream propInFile = null;
        try {
            propInFile = new FileInputStream("config.properties");
            // Load the file
            prop.load(propInFile);
            // Read all properties (hardcoded for now, may need to change to a loop)
            oauthKey = prop.getProperty("oauthKey");
            foodList = prop.getProperty("foodList");
            otherList = prop.getProperty("otherList");            
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (propInFile != null) {
                try {
                    propInFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void saveProperty(String[] args) {
        OutputStream propOutFile = null;
        try {
            prop.setProperty(args[0], args[1]);
            prop.store(propOutFile, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (propOutFile != null) {
                try {
                    propOutFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}