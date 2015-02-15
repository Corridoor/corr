/*
 * ChefBot by Brian B (atkailash) is licensed under the Creative Commons
 * Attribution-ShareAlike 4.0 International License. To view a
 * copy of this license, visit http://creativecommons.org/licenses/by-sa/4.0/
 */
package rocks.atkailash.chefbot;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbFoo
{
/**
 * Opens database (or creates if it's not there) and returns True if successful.
 * @param theFile
 * @return 
 */    
// public static void main(String args[]) // For testing purposes
    public boolean openDB(String theFile)
    {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:chefbot.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    return true;
    //    System.out.println("Opened DB"); // For testing purposes
    }
    
}