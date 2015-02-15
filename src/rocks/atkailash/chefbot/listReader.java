/*
 * ChefBot by Brian B (atkailash) is licensed under the Creative Commons
 * Attribution-ShareAlike 4.0 International License. To view a
 * copy of this license, visit http://creativecommons.org/licenses/by-sa/4.0/
 */
package rocks.atkailash.chefbot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Brian
 */
public class ListReader {
    private String theFile;
    private FileReader fr;
    private BufferedReader textReader;
    private int numberOfLines;
    private String[] textData;
    
    public ListReader(String list_to_read) throws FileNotFoundException, IOException {
        theFile = list_to_read;
        fr = new FileReader(theFile);
        textReader = new BufferedReader(fr);
        numberOfLines = countLines(theFile);
        this.textData = new String[numberOfLines];
    }    
    
    int countLines(String fileName) throws IOException {
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

    public void readList() throws IOException {
        int i;
        
        for (i=0; i < numberOfLines; i++) {
            textData[i] = textReader.readLine();
        }
        
        textReader.close();
    }
    
    public String chooseWord() {
       int randomNum = new Random().nextInt(textData.length);
       List<?> wordList=Arrays.asList(textData);
       Collections.shuffle(wordList);
       return (String) wordList.get(randomNum);
    }
}
