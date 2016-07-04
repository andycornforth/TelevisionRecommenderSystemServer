/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package contentBasedFiltering;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Andy
 */
/*
 * pass in the hashmap which has had TFIDF applied to it for best results
 */
public class CBCosine {
    
    private HashMap<String, HashMap<String, Double>> map;
    
    /*
     * constructor
     */
    public CBCosine(HashMap h){
        map = h;
    }
    
    /*
     * method to work out the cosine similarity
     */
    private double cosine_similarity(HashMap<String, Double> v1, HashMap<String, Double> v2) {
        //can be taken out once test data is no longer needed
        if(v1 == null || v2 == null){
            return -1;
        }
        // clone so we dont loose all the data
        HashMap v2Clone = (HashMap) v2.clone();

        Set<String> both = v2Clone.keySet();
        both.retainAll(v1.keySet());

        double sclar = 0, norm1 = 0, norm2 = 0, r1, r2;

        for (String k : both) {
            r1 = v1.get(k);
            r2 = v2.get(k);
            sclar += r1 * r2;
        }
        for (String k : v1.keySet()) {
            r1 = v1.get(k);
            norm1 += r1 * r1;
        }
        for (String k : v2.keySet()) {
            r2 = v2.get(k);
            norm2 += r2 * r2;
        }
        return sclar / Math.sqrt(norm1 * norm2);
    }
    
    /*
     * return string of show name that best matches the input show
     */
    public String findBestMatch(String showName){
        String currentBest = "N/A";
        double currentBestCosine = 0;
        // loop through all shows
        for (String key : map.keySet()) {
            // insure you do not compare with itself
            if(!key.equals(showName)){
                double result = cosine_similarity(map.get(showName), map.get(key));
                if(result > currentBestCosine){
                    currentBestCosine = result;
                    currentBest = key;
                }
            }
        }
        return currentBest;
    }
    
    /*
     * return the outright similarity of 2 shows - double
     */
    public double getSimilarity(String showA, String showB){
        HashMap a = map.get(showA);
        HashMap b = map.get(showB);
        
        return cosine_similarity(a,b);
    }
}
