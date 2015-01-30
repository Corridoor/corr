package rocks.atkailash.chefbot;

import org.jibble.pircbot.PircBot;

public class ChefBot extends PircBot {
    public static String theCommand;

    public ChefBot() {
	this.setName(propertiesFoo.botName);
    }
    
    public void onMessage(String channel, String sender,
                       String login, String hostname, String message) {
        if (message.startsWith(propertiesFoo.normalPrefix)) {
            theCommand = message.substring(1);
            processNormalCommand(theCommand);
        }
        if (message.equalsIgnoreCase("time")) {
            String time = new java.util.Date().toString();
            sendMessage(channel, sender + ": The time is now " + time);
        }
}
}
