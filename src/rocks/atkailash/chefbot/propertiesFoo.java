package rocks.atkailash.chefbot;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
/**
 * 
 * @author Brian
 * @version Alfa
 */
public class propertiesFoo {
    // Declare statics to be used throughout the class
    /* protected static String oauthKey = null;
    public static String foodList = null;
    public static String otherList = null;
    public static String myChannel = null;
    public static String botOwner = null;
    public static String botName = null;
    public static String normalPrefix = null; */
    public static Map validProperties = new HashMap<String, Object>() {{
        put("oauthKey", null);
        put("foodList", null);
        put("otherList", null);
        put("myChannel", null);
        put("botOwner", null);
        put("botName", null);
        put("normalPrefix", "!");
        put("modPrefix", "$");
    }};
    static Properties prop = new Properties();
    protected static String propertyValue;

    protected static void initProp() {

    }
   /**
    * Returns value of a given property
    * @param theProperty which property to read
     * @return <code>successfulOrNot</code> is true if read, false if error
     * Should store the property value to the variable of the same name
     * @throws java.io.IOException 
    */
    public static boolean allPropertiesIn() throws IOException {
        boolean wasSuccess = false;
        Iterator it = validProperties.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();
            String currentKey = (String) pairs.getKey();
            if (readProperty(currentKey)) {
                it.remove();
            } else if (null != validProperties.get("foodList")){
                wasSuccess = true;
            } else {
                    wasSuccess = false;
                    }
            wasSuccess = true;
        }
        return wasSuccess;
    }
    public static boolean readProperty(String theProperty) throws IOException {
        
        InputStream propertiesFile = null;
        boolean successfulOrNot;
        try {
            propertiesFile = new FileInputStream("config.properties");
            // Load the file
            prop.load(propertiesFile);
            String theValue = prop.getProperty(theProperty);
            validProperties.put(theProperty, theValue);
            // propertyValue = prop.getProperty(theProperty);
            successfulOrNot = true;
        } catch (IOException ex) {
            ex.printStackTrace();
            successfulOrNot = false;
        } finally {
            if (propertiesFile != null) {
                try {
                    propertiesFile.close();
                    successfulOrNot = true;
                } catch (IOException e) {
                    e.printStackTrace();
                    successfulOrNot = false;
                }
            }
        }
        return successfulOrNot;
    }
    
    /**
     * Save the value of a given property
     * @param args key=val that is in config.properties
     * @return  returns True if successful, false otherwise.
     * @throws java.io.IOException
     */
    public static boolean saveProperty(String args) throws IOException {
        OutputStream output = null;
        boolean successfulOrNot;
        try {
            output = new FileOutputStream("config.properties");
            String[] splitArgs;
            splitArgs = args.split("=",2);
            prop.setProperty(splitArgs[0], splitArgs[1]);
            prop.store(output, null);
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
    
    /**
     * Calls read property then returns the value of the property called. This
     * is separate because in some later parts of the code it is not necessary
     * to check if reading was successful, as it would not be running at that
     * point (or shouldn't be anyway). Still will throw exception if reading
     * doesn't work.
     * @param theProperty
     * @return 
     * @throws java.io.IOException 
     * @throws java.lang.NoSuchFieldException 
     * @throws java.lang.IllegalAccessException 
     */
    public static String getAndReturn(String theProperty) throws IOException, NoSuchFieldException, IllegalAccessException {
        readProperty(theProperty);
        propertiesFoo thisClass = new propertiesFoo();
        Field f1;
        f1 = thisClass.getClass().getField(theProperty);
        String theValue;
        theValue = (String) f1.get(thisClass);
        return theValue;
    }
}