package rocks.atkailash.chefbot;

import java.io.IOException;
import org.jibble.pircbot.IrcException;
import static rocks.atkailash.chefbot.propertiesFoo.prop;

public class ChefBotMain {
        static String theOauth;
        static String botOwnerName;
        static String channelName;
        static ChefBot bot;
        static boolean readWorked;

    public static void main(String[] args) throws Exception {
        bot = new ChefBot();
        bot.setVerbose(true);
        channelName = bot.getNick();
        readWorked = propertiesFoo.allPropertiesIn();
        if (readWorked) {

        } else {
            // I dunno, set a manual lsit of some sort?
        } 
       // Checks if we put an argument and what that is, but default is to
        // just run in twitch mode.
        if (readWorked && (args.length > 0)) {
            if (args[0].equals("debug")) {
                runDebugMode();
            }
            else {
                runTwitchMode(); }
        } else {
            System.err.println(readWorked + " = readWorked");
            System.err.println(args.length + " args.length & " + args[0] + " = args[0]");}
    }


    /**
     * This will probably be removed soon.But this just runs everything off my
     * irc server instead of twitch so I can test it.
     * @throws java.io.IOException
     * @throws org.jibble.pircbot.IrcException
     */
    public static void runDebugMode() throws IOException, IrcException {
                botOwnerName = prop.getProperty("botOwner");
                channelName = prop.getProperty("myChannel") + "-test";
                bot.connect("omoikane.kailash.im", 6667);
                bot.joinChannel("#".concat(channelName));
    }
    
    /**
     * Connects to twitch and double-checks if the file read worked since we
     * must have the oAuth key.
     * @throws IOException
     * @throws IrcException
     */
    public static void runTwitchMode() throws IOException, IrcException {

        theOauth = prop.getProperty("oauthKey");
        channelName = prop.getProperty("myChannel");
        
        // both read the properties and make sure it was successful
        if (readWorked) {
            bot.connect("irc.twitch.tv", 6667, theOauth); // Change this to irc.twitch.tv when not testing anymore
            bot.joinChannel(channelName);
        } else if (theOauth != null) {
            System.err.print("oauthKey is not set!");
        } else if (channelName != null) {
            System.err.print("myChannel is not set!");
        } else {
            System.err.print("Something went wrong");
        }
    }
}
