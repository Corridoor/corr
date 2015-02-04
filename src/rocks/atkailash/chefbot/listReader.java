/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rocks.atkailash.chefbot;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 *
 * @author Brian
 */
public class listReader {
    private static String fileName;
    
    public void notNeeded(String file_path) {
        fileName = file_path + ".txt";
    }
    
    static int countLines(String fileName) throws IOException {
        FileReader file_to_read = new FileReader(fileName);
        BufferedReader bf = new BufferedReader(file_to_read);
        
        String aLine;
        int numberOfLines = 0;
        
        while (( aLine = bf.readLine()) != null) {
            numberOfLines++;
        }
        
        bf.close();
        
        return numberOfLines;
}
    
    public static String[] listReader(String list_to_read) throws IOException {
        String theFile = list_to_read + ".txt";
        FileReader fr = new FileReader(theFile);
        BufferedReader textReader = new BufferedReader(fr);
        
        int numberOfLines = countLines(theFile);
        String[] textData;
        textData = new String[numberOfLines];
        
        int i;
        
        for (i=0; i < numberOfLines; i++) {
            textData[i] = textReader.readLine();
        }
        
        textReader.close();
        return textData;
    }
    
}
