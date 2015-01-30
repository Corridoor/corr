package rocks.atkailash.chefbot;

import static rocks.atkailash.chefbot.propertiesFoo.validProperties;

public class ChefBotMain {
    public static void main(String[] args) throws Exception {
        ChefBot bot = new ChefBot();
        bot.setVerbose(true);
        if (args[1].equals("debug")) {
            String botOwnerName;
            String channelName;
            botOwnerName = (String) validProperties.get("botOwner");
            channelName = (String) validProperties.get("myChannel");
            validProperties.put("myChannel", channelName.concat("-test"));
            bot.connect("omoikane.kailash.im", 6667);
            bot.joinChannel("#".concat(channelName));
    }

        boolean readWorked;
        readWorked = false;
        // both read the properties and make sure it was successful
        if (propertiesFoo.readProperty("oauthKey") & propertiesFoo.readProperty("channel")) {
            readWorked = true;
            bot.connect("omoikane.kailash.im", 6667, propertiesFoo.oauthKey); // Change this to irc.twitch.tv when not testing anymore
            bot.joinChannel(propertiesFoo.myChannel);
        } else if (propertiesFoo.oauthKey != null) {
            readWorked = false;
            System.err.print("oauthKey is not set!");
        } else if (propertiesFoo.myChannel != null) {
            readWorked = false;
            System.err.print("myChannel is not set!");
        } else {
            System.err.print("Something went wrong");
        }
    }
}
