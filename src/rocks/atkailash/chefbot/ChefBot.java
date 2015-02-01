package rocks.atkailash.chefbot;

import java.io.IOException;
import org.jibble.pircbot.PircBot;
import org.jibble.pircbot.User;
import static rocks.atkailash.chefbot.propertiesFoo.prop;

public class ChefBot extends PircBot {
    public static String theCommand;

    public ChefBot() throws IOException {
        propertiesFoo.allPropertiesIn();
	this.setName(prop.getProperty("botName"));
    }
    
    public void onMessage(String channel, String sender,
                       String login, String hostname, String message) {
        String normalPrefix = prop.getProperty("normalPrefix");
        String modPrefix = prop.getProperty("modPrefix");
        if (message.startsWith(normalPrefix)) {
            theCommand = message.substring(1);
            processNormalCommand(channel, sender, theCommand);
        } else if (message.startsWith(modPrefix) && testMod(channel, sender)) {
            theCommand = message.substring(1);
            processModCommand(channel, sender, theCommand);
        } else {sendMessage(channel, sender + "-> mod only command.");}
}
    
    public boolean testMod(String channel, String sender) {
        boolean isUserMod = false;
        User[] users = getUsers(channel);
        for (int i=0;i<(users.length);i++) {
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
    
    public void processNormalCommand(String channel, String sender, String theCommand) {
        switch (theCommand) {
            case "time":
                String time = new java.util.Date().toString();
                sendMessage(channel, sender + ": The time is now " + time);
                break;
            default:
                sendMessage(channel, sender + "-> I don't recognize that command");
                break;
        }
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
}