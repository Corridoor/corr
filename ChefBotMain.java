import org.jibble.pircbot.*;

public class ChefBotMain {
    public static void main(String[] args) throws Exception {
	
	// Init instance of PircBot ChefBot from ChefBot.java
	ChefBot bot = new ChefBot();
	// Store oAuth in a variable, need to get this to be a config thing
	String oAuthCode = "";

	// Enable Debug
	bot.setVerbose(true);

	// Connect to Twitch using oauth then join channel
	bot.connect("omoikane.kailash.im");
	bot.joinChannel("#corridoor");
    }
}
