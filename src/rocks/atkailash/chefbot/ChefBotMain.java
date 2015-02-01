package rocks.atkailash.chefbot;

import static rocks.atkailash.chefbot.propertiesFoo.prop;

public class ChefBotMain {
    public static void main(String[] args) throws Exception {
        boolean readWorked;
        readWorked = false;
        String theOauth;
        String botOwnerName;
        String channelName;
        ChefBot bot = new ChefBot();
        bot.setVerbose(true);
        channelName = bot.getNick();
        readWorked = propertiesFoo.allPropertiesIn();
        if (args.length > 0) {
            if (args[0].equals("debug")) {
                botOwnerName = prop.getProperty("botOwner");
                channelName = prop.getProperty("myChannel");
                channelName = prop.getProperty("myChannel") + "-test";
                bot.connect("omoikane.kailash.im", 6667);
                bot.joinChannel("#".concat(channelName));
    } } else {

        theOauth = prop.getProperty("oauthKey");
        // both read the properties and make sure it was successful
        if (propertiesFoo.readProperty("oauthKey") & propertiesFoo.readProperty("channel")) {
            readWorked = true;
            bot.connect("irc.twitch.tv", 6667, theOauth); // Change this to irc.twitch.tv when not testing anymore
            bot.joinChannel(channelName);
        } else if (theOauth != null) {
            readWorked = false;
            System.err.print("oauthKey is not set!");
        } else if (channelName != null) {
            readWorked = false;
            System.err.print("myChannel is not set!");
        } else {
            System.err.print("Something went wrong");
        }
        if (readWorked) {
            // do shit?
        } else {
            //more stuff
        } }
    }
}
