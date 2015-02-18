/*
 * ChefBot by Brian B (atkailash) is licensed under the Creative Commons
 * Attribution-ShareAlike 4.0 International License. To view a
 * copy of this license, visit http://creativecommons.org/licenses/by-sa/4.0/
 */
package rocks.atkailash.chefbot;

import java.io.IOException;
import static java.lang.String.format;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jibble.pircbot.PircBot;
import org.jibble.pircbot.User;
import static rocks.atkailash.chefbot.PropertiesFoo.prop;

public class ChefBot extends PircBot {

    public static String theCommand;

    public static String[] foodNames;
    public static String[] otherWords;
    public static String theFoods;
    public static String theWords;
    private ListReader foodLR;
    private ListReader otherLR;
    String fileName;

    public ChefBot() throws IOException {
        int i;
        PropertiesFoo.allPropertiesIn();
        this.setName(prop.getProperty("botName"));
        fileName = prop.getProperty("NameFile");
        foodLR = new ListReader(prop.getProperty("foodList"));
        otherLR = new ListReader(prop.getProperty("otherList"));
        foodLR.readList();
        otherLR.readList();
        /* foodNames = listReader.listReader("foods");
         otherWords = listReader.listReader("other");
         theFoods = "";
         for (i = 0; i < foodNames.length; i++) {
         theFoods = theFoods + " " + foodNames[i];
         }
         theWords = "";
         for (i = 0; i < otherWords.length; i++) {
         theWords = theWords + " " + otherWords[i];
         } */
    }

    public void onMessage(String channel, String sender,
            String login, String hostname, String message) {
        String normalPrefix = prop.getProperty("normalPrefix");
        String modPrefix = prop.getProperty("modPrefix");
        if (message.startsWith(normalPrefix)) {
            theCommand = message.substring(1);
            String[] theArgs = message.split(" ");
            try {
                if (message.split(" ", 2).length > 1) {
                    processArgCommand(channel, sender, theCommand, theArgs);
                } else {
                    processNormalCommand(channel, sender, theCommand);
                }
            } catch (IOException ex) {
                Logger.getLogger(ChefBot.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        } else if (message.startsWith(modPrefix) && testMod(channel, sender)) {
            theCommand = message.substring(1);
            processModCommand(channel, sender, theCommand);
        } else {
            sendMessage(channel, sender + "-> mod only command.");
        }
    }

        public void onJoin(String channel, String sender, String login,
                String hostname, String Message) throws IOException {
            if (NameStorage.nameStorage.containsKey(sender)) {
                sendMessage(channel, ("Welcome back, " + sender));
            } else {
                try {
                    NameStorage.newName(sender);
                    Logger.getLogger(ChefBot.class.getName()).log(Level.INFO,
                            format("User %s given name %s", sender,
                                    NameStorage.getName(sender), null));
                } catch (IOException ex) {
                    Logger.getLogger(ChefBot.class.getName()).log(Level.SEVERE, 
                            format("User %s not given a name!", sender), ex);
                }
            }
        }
        
    public boolean testMod(String channel, String sender) {
        boolean isUserMod = false;
        User[] users = getUsers(channel);
        for (int i = 0; i < (users.length); i++) {
            if (users[i].getNick().equals(sender) && (isUserMod = false)) {
                isUserMod = users[i].isOp();
                String testName;
                testName = users[i].getNick();
            } else {
                isUserMod = false;
            }
        }
        return isUserMod;
    }

    public void processNormalCommand(String channel, String sender,
            String theCommand) throws IOException {
        int i;
        String mname = format("do_%s", theCommand);
        try {
            Class<?> doThings = Class.forName("DoCommands");
            try {
                Method theMethod = doThings.getDeclaredMethod(mname);
                try {
                    theMethod.invoke(null);
                } catch (IllegalAccessException | IllegalArgumentException | 
                        InvocationTargetException ex) {
                    Logger.getLogger(ChefBot.class.getName()).log(Level.SEVERE, 
                            null, ex);
                }
            } catch (NoSuchMethodException | SecurityException ex) {
                sendMessage(channel, sender + "-> no such command!");
                Logger.getLogger(ChefBot.class.getName()).log(Level.SEVERE, null, 
                        ex);
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChefBot.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /* switch (theCommand) {
            case "time":
                String time = new java.util.Date().toString();
                sendMessage(channel, sender + ": The time is now " + time);
                break;
            case "foods":
                sendMessage(channel, sender + " the food is: " + 
                        foodLR.chooseWord());
                break;
            case "ow":
                sendMessage(channel, sender + " the other word is: " + 
                        otherLR.chooseWord());
                break;
            case "combo":
                String newName = otherLR.chooseWord() + " " + foodLR.chooseWord();
                sendMessage(channel, sender + "Combo: " + newName);
                try {
                    boolean didWork = NameStorage.storeName(sender, newName, 
                            fileName);
                    if (didWork) {
                        sendMessage(channel, "Storage Successful.");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ChefBot.class.getName()).log(Level.SEVERE,
                            null, ex);
                }
                break;
            default:
                sendMessage(channel, sender + "-> I don't recognize that command");
                break;
        } */
    }

    public void processModCommand(String channel, String sender, String theCommand) {
        switch (theCommand) {
            case "test":
                sendMessage(channel, "Testing, testing. Fuck you.");
                break;
            default:
                sendMessage(channel, sender + "-> I don't recognize that command");
                break;
        }
    }

    private void processArgCommand(String channel, String sender, String theCommand, String[] theArgs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
