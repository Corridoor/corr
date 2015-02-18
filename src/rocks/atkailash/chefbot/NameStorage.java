/*
 * ChefBot by Brian B (atkailash) is licensed under the Creative Commons
 * Attribution-ShareAlike 4.0 International License. To view a
 * copy of this license, visit http://creativecommons.org/licenses/by-sa/4.0/
 */
package rocks.atkailash.chefbot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import static rocks.atkailash.chefbot.PropertiesFoo.prop;

/**
 * This class stores the names in a config file, for easy access/changes done
 * manually if desired. May move to SQLite
 *
 * @author Brian
 */
public class NameStorage {
        static ListReader foodLR;
        static ListReader otherLR;
        static String fileName;
        static String theFood;
        static String theWord;
        static String[] foodNames;
        static String[] otherWords;

        
    static Properties nameStorage = new Properties();

    public boolean openFile(String fileName) throws FileNotFoundException, 
            IOException {
        InputStream theFile = null;
        boolean successfulOrNot = false; // Assume it doesn't actually get found
        try {
            theFile = new FileInputStream(fileName);
            nameStorage.load(theFile);
            successfulOrNot = true;
        } catch (FileNotFoundException ex) {
            successfulOrNot = false;
            ex.printStackTrace();
        } catch (IOException ex) {
            successfulOrNot = false;
            ex.printStackTrace();
        }
        return successfulOrNot;
    }

    public static boolean storeName(String twitchName, String chefName, 
            String fileName) throws IOException {
        OutputStream output = null;
        boolean successfulOrNot = false;
        try {
            output = new FileOutputStream(fileName);
            nameStorage.setProperty(twitchName, chefName);
            nameStorage.store(output, null);
            successfulOrNot = true;
        } catch (IOException ex) {
            ex.printStackTrace();
            successfulOrNot = false;
        } finally {
            if (output != null) {
                try {
                    output.close();
                    successfulOrNot = true;
                } catch (IOException e) {
                    e.printStackTrace();
                    successfulOrNot = false;
                }
            }
        }
        return successfulOrNot;
    }
    
    public static String getName(String twitchName) {
        if (nameStorage.containsKey(twitchName)) {
            return nameStorage.getProperty(twitchName);
        } else {
            return "No specialty!";
        }
    }
    
    /**
     * 
     * @param twitchName
     * @return
     * @throws IOException 
     */
    public static boolean newName(String twitchName) throws IOException {
        fileName = prop.getProperty("NameFile");
        foodLR = new ListReader(prop.getProperty("foodList"));
        otherLR = new ListReader(prop.getProperty("otherList"));
        foodLR.readList();
        otherLR.readList();
        String newName;
        Boolean nameStored;
        
        theWord = otherLR.chooseWord();
        theFood = foodLR.chooseWord();
        newName = theWord + " " + theFood;
        nameStored = storeName(twitchName, newName, fileName);
        
        if (nameStored) {
            return true;
        }
        else {
            return false;
        }
    }
}
