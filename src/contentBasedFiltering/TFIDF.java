/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package contentBasedFiltering;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Andy
 */
public class TFIDF {
    // hashmap passed in through constructor which contains show names and descriptions
    private HashMap<String, HashMap<String, Double>> shows;

    public TFIDF(HashMap h) {
        shows = h;
        calculateAll();
    }

    /**
     * ************************** GETTER - MAP *********************************
     * The only public method in the class
     * @return 
     */
    public HashMap<String, HashMap<String, Double>> getShows() {
        return shows;
    }
    
    /**
     * *************** TF-IDF Calculation method *******************************
     * @param term
     * @param showName
     * @return 
     */
    private double tfidfCalculation(String term, String showName) {
        double tf = termFrequency(term, showName);
        double idf = inverseDocumentFrequency(term);

        double result = tf * idf;
        return result;
    }

    /**
     * ****************************** TF ***************************************
     * works out the term frequency of a word in a shows description
     */
    private double termFrequency(String term, String showName) {
        double result = 0;
        // get the total number of words in the shows description
        double total = getTotalWordsInShow(showName);
        // get the frequency of the term in the specific show
        double frequency = getWordFrequency(term, showName);

        result = frequency / total;

        return result;
    }

    /**
     * *************************** IDF *****************************************
     * works out the inverse document
     * frequency of a word in the entire set of words
     */
    private double inverseDocumentFrequency(String term) {
        double count = 0;
        // loop through entire map of shows
        for (String key : shows.keySet()) {
            count += getWordFrequency(term, key);
        }
        double totalNumberOfTerms = getTotalWordsInMatrix();
        // do calculation and return
        return Math.log(totalNumberOfTerms / count);
    }

    /*
     * ************* small private methods to help with the calculations *******
     ***************************************************************************
     */

    /*
     * get the total number of words in a shows description set
     */
    private double getTotalWordsInShow(String showName) {
        double result = 0;
        HashMap descWords = shows.get(showName);
        // iterate through specific shows hashmap
        Iterator iter = descWords.keySet().iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            double val = (Double) descWords.get(key);
            result = result + val;
        }
        return result;
    }

    /*
     * get the entire total number of descriptor words
     */
    private double getTotalWordsInMatrix() {
        double totalWords = 0;

        for (String key : shows.keySet()) {
            totalWords += getTotalWordsInShow(key);
        }

        return totalWords;
    }

    /*
     * get the number of times a word appears in a shows descriptor set
     */
    private double getWordFrequency(String term, String showName) {
        double result = 0;
        // get the show hashmap
        HashMap descWords = shows.get(showName);
        // if term is present in set then increment, else return 0
        if (descWords.containsKey(term)) {
            double j = (double) descWords.get(term);
            result += j;
        }
        return result;
    }
    
    /*
     * ***************** TO CALCULATE TFIDF FOR ALL WORDS IN MATRIX ************
     */

    private void calculateAll() {
        HashMap<String, HashMap<String, Double>> newMap = new HashMap<>();
        // loop through entire map of shows
        for (String key : shows.keySet()) {
            // create new map to hold description words
            HashMap<String, Double> description = new HashMap();
            // loop through independent show
            for (String desc : shows.get(key).keySet()) {
                double result = tfidfCalculation(desc, key);
                // add new calculation to the new hashmap
                description.put(desc, result);
            }
            newMap.put(key, description);
        }
        shows = newMap;
    }
}
