package rocks.atkailash.chefbot;

import static java.lang.String.format;
import java.util.logging.Level;
import java.util.logging.Logger;

        
final class DoCommands {
    
    private void DoCommands() throws RuntimeException {
        RuntimeException ex = new RuntimeException("DoCommands shouldn't be called directly.");
        Logger.getLogger(ChefBot.class.getName()).log(Level.INFO,
                        "DoCommands() method called.", ex);
    }
    
    public String do_time(String theChannel, String theSender) {
        String theTime;
        String timeString;
        theTime = new java.util.Date().toString();
        timeString = format("The current time is %s", theTime);

        return timeString;
    }
    
   
}