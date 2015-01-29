package rocks.atkailash.properties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class propertiesFoo {
    // Declare statics to be used throughout the class

    public static String oauthKey;
    public static String foodList;
    public static String otherList;
    static Properties prop = new Properties();

    public static void loadProp() {
        // This is where we will be loading the properties
        InputStream propertiesFile = null;
        try {
            propertiesFile = new FileInputStream("config.properties");
            // Load the file
            prop.load(propertiesFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (propertiesFile != null) {
                try {
                    propertiesFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void getProperties() {
        // get values from properties file
        loadProp(output);
        oauthKey = prop.getProperty("oauthKey");
        foodList = prop.getProperty("foodList");
        otherList = prop.getProperty("otherList");
    }

    public void saveProperty(String[] args) {
        loadProp();
        try {
            prop.setProperty(args[0], args[1]);
            prop.store(propertiesFile, null);
        }
    }
}