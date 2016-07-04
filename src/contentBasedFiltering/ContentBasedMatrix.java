/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package contentBasedFiltering;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andy
 */
public class ContentBasedMatrix {

    private HashMap<String, HashMap<String, Double>> contentMatrix;
    private ArrayList<String> stopWords, punctuation;
    private String file = "additionalFiles/stopWords.txt";
    private String file2 = "additionalFiles/punctuation.txt";

    public ContentBasedMatrix() {
        contentMatrix = new HashMap<>();
        stopWords = new ArrayList<>();
        punctuation = new ArrayList<>();
        loadStopWords();
    }

    /*
     * add show to the map, with description minus stop words
     */
    public void addShow(String showName, String showDescription) {
        // create new map to hold description words
        HashMap<String, Double> description = new HashMap();
        // tempory array
        String[] tempDescription = showDescription.split(" ");
        /* add the decription words to the map, minus the stop words and 
         * incrementing when word appears twice */
        for (int i = 0; i < tempDescription.length; i++) {
            // set new word from array
            String word = tempDescription[i];
            word = removePunctuation(word);
            // do not add to map if it is a stop word
            if (!stopWords.contains(word)) {
                // assume timesAppeared = 1
                double timesAppeared = 1;
                // if word is already in map, increment its value
                if (description.containsKey(word)) {
                    timesAppeared = description.get(word) + 1;
                }
                description.put(word, timesAppeared);
            }
        }
        contentMatrix.put(showName, description);
    }

    /*
     * load the stop words from text file into arraylist
     */
    private void loadStopWords() {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
            String word = in.readLine();
            while (word != null) {
                stopWords.add(word);
                word = in.readLine();
            }
            in = new BufferedReader(new FileReader(file2));
            String symbol = in.readLine();
            while (symbol != null) {
                punctuation.add(symbol);
                symbol = in.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContentBasedMatrix.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContentBasedMatrix.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(ContentBasedMatrix.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private String removePunctuation(String word){
        for (int i = 0; i < punctuation.size(); i++) {
            String symbol = punctuation.get(i);
            if(word.contains(symbol)){
                word = word.replace(symbol, "");
            }
        }
        word = word.toLowerCase();
        return word;
    }

    /*
     * return matrix of shows and decription words
     */
    public HashMap<String, HashMap<String, Double>> getContentMatrix() {
        return contentMatrix;
    }
}
